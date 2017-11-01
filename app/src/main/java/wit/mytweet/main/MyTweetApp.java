package wit.mytweet.main;


import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import wit.mytweet.model.Tweet;
import wit.mytweet.model.User;

public class MyTweetApp extends Application {
    public List<User> users = new ArrayList<>();
    public List<Tweet> tweets = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v("MyTweet", "MyTweet App Started");
    }

    public void newTweet(Tweet tweet) {
        tweets.add(tweet);
        Log.v("MyTweet", tweet.text + " " + tweets.size());
    }

    public void newUser(User user) {
        Log.v("MyTweet", user.getFullName());
        users.add(user);
    }

    public boolean validUser (String email, String password) {
        for (User user : users) {
            if (user.email.equals(email) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }
}
