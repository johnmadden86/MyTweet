package wit.mytweet.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import wit.mytweet.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void signUpButtonPressed(View view) {
        Log.v("MyTweet", "Sign up pressed");
        startActivity(new Intent(this, Register.class));
    }

    public void loginButtonPressed(View view) {
        Log.v("MyTweet", "Login pressed");
        startActivity(new Intent(this, Login.class));
    }
}
