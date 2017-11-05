package wit.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import wit.mytweet.R;
import wit.mytweet.app.MyTweetApp;
import wit.mytweet.model.Tweet;
import wit.mytweet.model.TweetCollection;

public class Compose extends AppCompatActivity {
    static ActionBar actionBar;
    private MyTweetApp app;
    private EditText tweetInput;
    private Tweet tweet;
    private Button sendTweetButton, editDateButton, contactButton, emailButton;
    private TextView charCount;
    private TweetCollection tweetCollection;
    private String emailAddress = "";
    private static final int REQUEST_CONTACT = 1;
    private Intent data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        actionBar = getSupportActionBar();

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null) {
            fragment = new TweetFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }
}
