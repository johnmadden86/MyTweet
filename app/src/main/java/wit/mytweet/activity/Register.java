package wit.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import wit.mytweet.R;
import wit.mytweet.main.MyTweetApp;
import wit.mytweet.model.User;

public class Register extends AppCompatActivity {

    private MyTweetApp app;
    private EditText firstName, lastName, email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        app = (MyTweetApp) getApplication();

        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
    }

    public void registerUser(View view) {

        User user = new User(
                firstName.getText().toString(),
                lastName.getText().toString(),
                email.getText().toString(),
                password.getText().toString()
        );

        app.newUser(user);
        startActivity(new Intent(this, Compose.class));
    }
}
