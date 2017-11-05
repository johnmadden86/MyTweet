package wit.mytweet.model;

import java.util.ArrayList;

import static wit.android.helpers.LogHelpers.info;

public class TweetCollection {

    public ArrayList<Tweet> tweets;
    public ArrayList<User> users;
    private TweetSerialiser tweetSerialiser;

    public TweetCollection(TweetSerialiser tweetSerialiser) {
        this.tweetSerialiser = tweetSerialiser;
        try {
            tweets = tweetSerialiser.loadTweets();
            users = tweetSerialiser.loadUsers();
        }
        catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
        }
    }

    public void newTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public Tweet getTweet(Long id) {
        for (Tweet tweet : tweets) {
            if (id.equals(tweet.id)) {
                return tweet;
            }
        }
        return null;
    }

    public boolean saveTweets() {
        try {
            tweetSerialiser.saveTweets(tweets, users);
            info(this, "Tweets saved to file");
            return true;
        } catch (Exception e) {
            info(this, "Error saving tweets: " + e.getMessage());
            return false;
        }
    }
}