package wit.mytweet.model;

import java.util.Date;

public class Tweet {
    public Date date;
    public String text;
    public User author;

    public Tweet(Date date, String text) {
        setDate(date);
        setText(text);
        setAuthor(author);
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}

