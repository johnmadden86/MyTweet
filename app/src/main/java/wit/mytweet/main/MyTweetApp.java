package wit.mytweet.main;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import wit.mytweet.model.User;

public class MyTweetApp extends Application {

    public final List<User> users = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
    }

    public void newUser(User user) {
        users.add(user);
    }
}
