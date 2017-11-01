package wit.mytweet.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import wit.mytweet.R;
import wit.mytweet.main.MyTweetApp;
import wit.mytweet.model.Tweet;

public class Timeline extends AppCompatActivity {
    public ListView listView;
    public MyTweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        app = (MyTweetApp) getApplication();

        listView = (ListView) findViewById(R.id.tweetList);
        TweetAdapter adapter = new TweetAdapter(this, app.tweets);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timeline_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.composeTweet:
                startActivity(new Intent(this, Compose.class));
                break;
            case R.id.menuSettings:
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:
                startActivity(new Intent(this, Welcome.class));
                break;
        }
        return true;
    }

}

class TweetAdapter extends ArrayAdapter<Tweet> {
    private Context context;
    public List<Tweet> tweets;

    public TweetAdapter(Context context, List<Tweet> tweets) {
        super(context, R.layout.row_layout, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.row_layout, parent, false);
        Tweet tweet   = tweets.get(position);
        //TextView dateView = (TextView) view.findViewById(R.id.row_date);
        TextView textView = (TextView) view.findViewById(R.id.row_text);
        //TextView authorView = (TextView) view.findViewById(R.id.row_author);

        textView.setText(tweet.text);

        return view;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }
}
