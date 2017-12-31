package app.mytweet.app;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.model.TweetCollection;
import app.mytweet.model.TweetSerialiser;
import app.mytweet.model.User;

public class MyTweetApp extends Application {
    protected  static MyTweetApp app;
    public List<User> users = new ArrayList<>();
    public TweetCollection tweetCollection;
    private static final String TWEET_FILE = "tweet-collection.json";
    private static final String USER_FILE = "user-collection.json";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
        TweetSerialiser serialiser = new TweetSerialiser(this, TWEET_FILE, USER_FILE);
        tweetCollection = new TweetCollection(serialiser);
        app = this;
    }

    @org.jetbrains.annotations.Contract(pure = true)
    public static MyTweetApp getApp() {
        return app;
    }

    public void newUser(User user) {
        Log.v("MyTweet", user.getFullName() + " added");
        users.add(user);
        Log.v("MyTweet", users.size() + " users");
    }

    public boolean validUser (String email, String password) {
        Log.v("MyTweet", "finding user");
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                Log.v("MyTweet", "user found");
                return true;
            }
        }
        Log.v("MyTweet", "user not found");
        return false;
    }
}
