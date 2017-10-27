package wit.mytweet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import wit.mytweet.R;
import wit.mytweet.main.MyTweetApp;

public class Home extends AppCompatActivity {

    private MyTweetApp app;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        app = (MyTweetApp) getApplication();
    }
}
