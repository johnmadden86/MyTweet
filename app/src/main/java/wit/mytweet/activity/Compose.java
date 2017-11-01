package wit.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import wit.mytweet.R;
import wit.mytweet.main.MyTweetApp;
import wit.mytweet.model.Tweet;

public class Compose extends AppCompatActivity {

    private MyTweetApp app;
    private EditText tweetInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
        app = (MyTweetApp) getApplication();
        tweetInput = (EditText) findViewById(R.id.tweet_input);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tweet_compose_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuTimeline:
                startActivity(new Intent(this, Timeline.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:
                startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }



    public void charCounter() {

    }

    public void sendTweet(View view) {
        Date date = new Date();
        String text = tweetInput.getText().toString();
        app.newTweet(new Tweet(date, text));
        Log.v("MyTweet", date.toString() + " " + text);
        Toast.makeText(this, "Tweet Sent!", Toast.LENGTH_SHORT).show();
        tweetInput.setText("");
        //startActivity(new Intent(this, Timeline.class));
    }
}
