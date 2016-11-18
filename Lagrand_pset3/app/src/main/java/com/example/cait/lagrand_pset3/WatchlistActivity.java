package com.example.cait.lagrand_pset3;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class WatchlistActivity extends Activity {

    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watchlist);

        // Get textview and listview
        TextView emptyText = (TextView) findViewById(R.id.emptyListText);
        ListView movieListView = (ListView) findViewById(R.id.watchlistView);

        // Get current watchlist
        prefs = this.getSharedPreferences("watchlist", MODE_PRIVATE);
        Set<String> watchList = prefs.getStringSet("movies", new HashSet<String>());

        // If empty watchlist show to user that it is empty, otherwise show watchlist
        if (watchList.isEmpty()) {
            movieListView.setVisibility(View.INVISIBLE);
            emptyText.setVisibility(View.VISIBLE);
        }
        else {
            ArrayList<String> movieList = new ArrayList<>(watchList);
            ArrayAdapter adapter = new ArrayAdapter<>(this,
                    R.layout.activity_listview, R.id.listText, movieList);

            movieListView.setAdapter(adapter);
            movieListView.setVisibility(View.VISIBLE);
            emptyText.setVisibility(View.INVISIBLE);
        }

    }

    public void deleteItem(View view) {
        // Get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout) view.getParent();

        TextView title = (TextView) vwParentRow.getChildAt(0);

        // Get the current watch list
        prefs = this.getSharedPreferences("watchlist", MODE_PRIVATE);
        Set<String> watchList = prefs.getStringSet("movies", new HashSet<String>());

        watchList.remove(title.getText().toString());

        // Refresh page to show current watchlist
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void toMovie(View view) {
        // Get the row the clicked button is in
        LinearLayout vwParentRow = (LinearLayout) view.getParent();

        TextView title = (TextView)vwParentRow.getChildAt(0);

        Intent goToResults = new Intent(this, ResultsActivity.class);
        goToResults.putExtra("title", title.getText().toString());
        startActivity(goToResults);
        finish();
    }

    public void goHome(View view) {
        Intent goHome = new Intent(this, HomeActivity.class);
        startActivity(goHome);
        finish();
    }

    public void toWatchList(View view) {
        Intent goToWatchList = new Intent(this, WatchlistActivity.class);
        startActivity(goToWatchList);
        finish();
    }
}
