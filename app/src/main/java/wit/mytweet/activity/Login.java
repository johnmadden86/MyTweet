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

public class Login extends AppCompatActivity {

    private MyTweetApp app;
    private Button loginButton;
    private EditText email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        app = (MyTweetApp) getApplication();
        loginButton = (Button) findViewById(R.id.login_button);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        if (loginButton != null) {
            Log.v("MyTweet", "Got login button");
        }
    }

    public void loginUser(View view) {
        String emailInput = email.getText().toString();
        String passwordInput = password.getText().toString();

        for (User user : app.users) {
            if (emailInput.equals(user.getEmail()) && passwordInput.equals(user.getPassword())) {
                startActivity(new Intent(this, Home.class));
                Log.v("MyTweet", "Login successful " + emailInput);
            } else {
                Log.v("MyTweet", "Login failed");
                startActivity(new Intent(this, Welcome.class));
            }
        }
    }
}
