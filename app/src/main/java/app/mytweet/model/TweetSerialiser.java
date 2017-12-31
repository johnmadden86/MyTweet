package app.mytweet.model;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

public class TweetSerialiser {
    private Context mContext;
    private String mFilename, nFilename;

    public TweetSerialiser(Context c, String f1, String f2) {
        mContext = c;
        mFilename = f1;
        nFilename = f2;
    }

    void saveTweets(ArrayList<Tweet> tweets, ArrayList<User> users) throws JSONException, IOException {
        // build an array in JSON
        JSONArray tweetArray = new JSONArray();
        JSONArray userArray = new JSONArray();
        for (Tweet tweet : tweets) {
            tweetArray.put(tweet.toJSON());
        }
        for (User user : users) {
            userArray.put(user.toJSON());
        }

        // write the file to disk
        write(mFilename, tweetArray);
        write(nFilename, userArray);
    }

    private void write(String fileName, JSONArray array) throws JSONException, IOException {
        Writer writer = null;
        try {
            OutputStream out = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if (writer != null)
                writer.close();
        }
    }

    ArrayList<Tweet> loadTweets() throws IOException, JSONException {
        ArrayList<Tweet> tweets = new ArrayList<>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of tweets from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                tweets.add(new Tweet(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return tweets;
    }

    ArrayList<User> loadUsers() throws IOException, JSONException {
        ArrayList<User> users = new ArrayList<>();
        BufferedReader reader = null;
        try {
            // open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(nFilename);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                // line breaks are omitted and irrelevant
                jsonString.append(line);
            }
            // parse the JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            // build the array of tweets from JSONObjects
            for (int i = 0; i < array.length(); i++) {
                users.add(new User(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one, since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return users;
    }
}