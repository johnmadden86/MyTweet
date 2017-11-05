package wit.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import wit.mytweet.R;
import wit.mytweet.app.MyTweetApp;

public class Login extends AppCompatActivity {

    private MyTweetApp app;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        app = (MyTweetApp) getApplication();

        email = (EditText) findViewById(R.id.Email);
        password = (EditText) findViewById(R.id.Password);
    }

    public void loginUser(View view) {

        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();

        if (app.validUser(emailInput, passwordInput)) {
            startActivity(new Intent(this, Compose.class));
            Log.v("MyTweet", "Login successful " + emailInput);
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }
}
