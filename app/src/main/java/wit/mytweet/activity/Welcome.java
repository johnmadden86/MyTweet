package wit.mytweet.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import wit.mytweet.R;

public class Welcome extends AppCompatActivity {

    private Button signUpButton, loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        signUpButton = (Button) findViewById(R.id.sign_up_button);
        loginButton = (Button) findViewById(R.id.login_welcome_button);
        if (signUpButton != null && loginButton != null) {
            Log.v("MyTweet", "Got buttons");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tweet_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void signUpButtonPressed(View view) {
        Log.v("MyTweet", "Sign up pressed");
        startActivity(new Intent(this, Register.class));
    }

    public void loginButtonPressed(View view) {
        Log.v("MyTweet", "Login pressed");
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuReport:
                Toast.makeText(this, "Report Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
