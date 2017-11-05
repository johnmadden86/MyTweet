package wit.mytweet.activity;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import wit.mytweet.R;
import wit.mytweet.app.MyTweetApp;
import wit.mytweet.model.Tweet;
import wit.mytweet.model.TweetCollection;

import static wit.android.helpers.ContactHelper.getContact;
import static wit.android.helpers.ContactHelper.getEmail;
import static wit.android.helpers.ContactHelper.sendEmail;
import static wit.android.helpers.IntentHelper.selectContact;

public class TweetFragment extends Fragment
        implements View.OnClickListener, TextWatcher, DatePickerDialog.OnDateSetListener {

    private EditText tweetInput;
    private Tweet tweet;
    private Button sendTweetButton, editDateButton, contactButton, emailButton;
    private TextView charCount;
    private TweetCollection tweetCollection;
    private String emailAddress = "";
    private static final int REQUEST_CONTACT = 1;
    private Intent data;
    MyTweetApp app;
    public static final String EXTRA_TWEET_ID = "mytweet.TWEET_ID";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        Long tweetId = (Long) getActivity().getIntent().getSerializableExtra("TWEET_ID");

        app = MyTweetApp.getApp();
        tweetCollection = app.tweetCollection;
        if (tweetId != null) {
            tweet = tweetCollection.getTweet(tweetId);
        } else {
            tweet = new Tweet(new Date().getTime(), "");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        super.onCreateView(inflater,  parent, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_compose, parent, false);

        Compose compose = (Compose) getActivity();
        Compose.actionBar.setDisplayHomeAsUpEnabled(true);

        addListeners(v);
        updateControls(tweet);

        return v;
    }

    private void addListeners(View v) {
        tweetInput = (EditText) v.findViewById(R.id.tweet_input);
        tweetInput.addTextChangedListener(this);

        charCount = (TextView) v.findViewById(R.id.char_count);

        sendTweetButton = (Button) v.findViewById(R.id.send_tweet_button);
        editDateButton = (Button) v.findViewById(R.id.edit_date_button);
        contactButton = (Button) v.findViewById(R.id.contact);
        emailButton = (Button) v.findViewById(R.id.emailButton);
        sendTweetButton.setOnClickListener(this);
        editDateButton.setOnClickListener(this);
        contactButton.setOnClickListener(this);
        emailButton.setOnClickListener(this);
    }

    public void updateControls(Tweet tweet) {
        tweetInput.setText(tweet.text);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            //case android.R.id.home: navigateUp(getActivity());break;
            case R.id.menuTimeline:
                startActivity(new Intent(getActivity(), Timeline.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(getActivity(), "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:
                startActivity(new Intent(getActivity(), Welcome.class));
                break;
        }
        return true;
    }

    @Override
    public void onPause() {
        super.onPause();
        tweetCollection.saveTweets();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        switch (requestCode) {
            case REQUEST_CONTACT:
                this.data = data;
                checkContactsReadPermission();
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {}

    @Override
    public void afterTextChanged(Editable editable) {
        int charLimit = 140;
        int tweetLength = tweetInput.length();
        int charCount = charLimit - tweetLength;
        this.charCount.setText(String.valueOf(charCount));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_tweet_button:
                sendTweet(v);
                break;
            case R.id.edit_date_button:
                Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog
                        (getActivity(), this,
                                c.get(Calendar.YEAR),
                                c.get(Calendar.MONTH),
                                c.get(Calendar.DAY_OF_MONTH)
                        );
                dpd.show();
                break;
            case R.id.contact:
                selectContact(getActivity(), REQUEST_CONTACT);
                break;
            case R.id.emailButton:
                sendEmail(getActivity(), emailAddress, getString(
                        R.string.tweetReportSubject), tweet.getTweetReport(getActivity()));
                break;

        }

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date date = new GregorianCalendar(year, month, dayOfMonth).getTime();
        tweet.setDate(date.getTime());
        editDateButton.setText(tweet.getDateString());
    }

    //https://developer.android.com/training/permissions/requesting.html
    private void checkContactsReadPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            //We can request the permission.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_CONTACT);
        }
        else {
            //We already have permission, so go head and read the contact
            readContact();
        }
    }

    private void readContact() {
        String name = getContact(getActivity(), data);
        emailAddress = getEmail(getActivity(), data);
        String buttonDisplay = name + " : " + emailAddress;
        contactButton.setText(buttonDisplay);
        tweet.contact = name;
    }

    //https://developer.android.com/training/permissions/requesting.html
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    readContact();
                }
            }
        }
    }


    public void sendTweet(View view) {
        Tweet tweet = new Tweet(new Date().getTime(), tweetInput.getText().toString());
        app.tweetCollection.newTweet(tweet);
        Log.v("MyTweet", tweet.text + "length " + tweetInput.length());
        Toast.makeText(getActivity(), "Tweet Sent!", Toast.LENGTH_SHORT).show();
        tweetInput.setText("");
        startActivity(new Intent(getActivity(), Timeline.class));
    }
}
