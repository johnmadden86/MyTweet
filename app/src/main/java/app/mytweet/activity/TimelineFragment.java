package app.mytweet.activity;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.model.Tweet;
import app.mytweet.model.TweetCollection;

import static app.android.helpers.IntentHelper.startActivityNew;
import static app.android.helpers.IntentHelper.startActivityWithData;

public class TimelineFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private ArrayList<Tweet> tweets;
    private TweetCollection tweetCollection;
    private TweetAdapter adapter;
    MyTweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = MyTweetApp.getApp();
        tweetCollection = app.tweetCollection;
        tweets = tweetCollection.tweets;

        adapter = new TweetAdapter(getActivity(), tweets);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        return super.onCreateView(inflater, parent, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Tweet tweet = ((TweetAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), Compose.class);
        i.putExtra(TweetFragment.EXTRA_TWEET_ID, tweet.id);
        startActivityForResult(i, 0);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((TweetAdapter) getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.timeline_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.composeTweet:
                startActivityNew(getActivity(), Compose.class);
                break;
            case R.id.menuSettings:
                Toast.makeText(getActivity(), "Settings Selected", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menuLogout:
                startActivity(new Intent(getActivity(), Login.class));
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = adapter.getItem(position);
        startActivityWithData(getActivity(), Compose.class, "TWEET_ID", tweet.id);
    }


    class TweetAdapter extends ArrayAdapter<Tweet> {
        private Context context;

        public TweetAdapter(Context context, ArrayList<Tweet> tweets) {
            super(context, 0, tweets);
            this.context = context;
        }

        @SuppressLint("InflateParams")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.row_layout, null);
            }
            Tweet tweet = getItem(position);
            TextView dateView = (TextView) convertView.findViewById(R.id.row_date);
            TextView textView = (TextView) convertView.findViewById(R.id.row_text);
            TextView authorView = (TextView) convertView.findViewById(R.id.row_author);

            dateView.setText(tweet.getDateString());
            textView.setText(tweet.text);
            //authorView.setText(tweet.author.getFullName());

            return convertView;
        }
    }
}