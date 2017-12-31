package app.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.model.User;

public class Register extends AppCompatActivity {

    private MyTweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        app = (MyTweetApp) getApplication();
        Log.v("MyTweet", "Sign up page loaded");
    }

    public void registerUser(View view) {
        EditText firstName = (EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.Email);
        EditText password = (EditText) findViewById(R.id.Password);

        User user = new User(
                firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                password.getText().toString()
        );

        app.newUser(user);
        Log.v("MyTweet", "Loading compose tweet screen");
        Intent intent = new Intent(this, Compose.class);
        //intent.putExtra("TWEET_ID", "");
        startActivity(intent);
    }

    public void loginButtonPressed(View view) {
        Log.v("MyTweet", "Login pressed");
        startActivity(new Intent(this, Login.class));
    }
}
