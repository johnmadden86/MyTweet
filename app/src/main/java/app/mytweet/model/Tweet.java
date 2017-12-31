package app.mytweet.model;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class Tweet {
    public Long date, id;
    public String text, contact;
    private User author;

    private static final String JSON_ID = "id";
    private static final String JSON_TEXT = "text";
    private static final String JSON_DATE = "date";
    private static final String JSON_AUTHOR = "author";
    private static final String JSON_CONTACT = "contact";

    public Tweet(Long date, String text) {
        setDate(date);
        setText(text);
        //setAuthor(author);
        this.id = unsignedLong();
        contact = " :None";
    }

    Tweet(JSONObject json) throws JSONException {
        id = json.getLong(JSON_ID);
        text = json.getString(JSON_TEXT);
        date = json.getLong(JSON_DATE);
        //author = json.getJSONObject(JSON_AUTHOR);
        contact = json.getString(JSON_CONTACT);
    }

    JSONObject toJSON() throws JSONException {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, id);
        json.put(JSON_TEXT, text);
        json.put(JSON_DATE, date);
        //json.put(JSON_AUTHOR, author);
        json.put(JSON_CONTACT, contact);
        return json;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    static Long unsignedLong() {
        long rndVal = 0;
        do {
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }

    public String getDateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    private void setAuthor(User author) {
        this.author = author;
    }

    public String getTweetReport(Context context) {
        return "Shared from MyTweet App";
    }
}

