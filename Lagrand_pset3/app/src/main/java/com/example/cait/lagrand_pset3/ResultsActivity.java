package com.example.cait.lagrand_pset3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class ResultsActivity extends AppCompatActivity {

    MovieData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Get the movie title
        Bundle extras = getIntent().getExtras();
        String title = (String) extras.get("title");

        // Search server
        MovieAsyncTask task = new MovieAsyncTask(this);
        task.execute(title);
    }

    public void showData(MovieData data) {
        this.data = data;
        // Show extra info
        TextView released = (TextView) findViewById(R.id.releaseText);
        released.setText(data.released);
        TextView rated = (TextView) findViewById(R.id.ratedText);
        rated.setText(data.rated);
        TextView time = (TextView) findViewById(R.id.timeText);
        time.setText(data.time);
        TextView genre = (TextView) findViewById(R.id.genreText);
        genre.setText(data.genre);
        TextView director = (TextView) findViewById(R.id.directorText);
        director.setText(data.director);

        // Show the title
        TextView movieTitle = (TextView) findViewById(R.id.titleText);
        movieTitle.setText(data.title);

        // Show the description
        TextView plot = (TextView) findViewById(R.id.descriptionText);
        plot.setText(data.plot);

        // Make add to watchlist button invisible if no result
        if (data.title.isEmpty()) {
            movieTitle.setText("No results found");
        }
        else {
            Button watchListButton = (Button) findViewById(R.id.addWatchlistButton);
            watchListButton.setVisibility(Button.VISIBLE);
        }

    }

    public void showImage(Bitmap poster) {
        // Show the poster
        if (poster != null) {
            ImageView image = (ImageView) findViewById(R.id.posterImage);
            image.setImageBitmap(poster);
        }
    }

    public void addToWatchList(View view) {
        // Get current watchlist
        SharedPreferences prefs = this.getSharedPreferences("watchlist", MODE_PRIVATE);
        Set<String> watchList = prefs.getStringSet("movies", new HashSet<String>());

        // Update watchlist
        watchList.add(data.title);

        // Put new watchlist back to prefs
        SharedPreferences.Editor editor = prefs.edit();
        editor.putStringSet("movies", watchList);
        editor.apply();

        // Show user that movie is added to watchlist
        Toast.makeText(this, "Movie added to your watchlist", Toast.LENGTH_SHORT).show();
    }

    public void toWatchList(View view) {
        Intent goToWatchList = new Intent(this, WatchlistActivity.class);
        startActivity(goToWatchList);
        finish();
    }

    public void goHome(View view) {
        Intent goHome = new Intent(this, HomeActivity.class);
        startActivity(goHome);
        finish();
    }
}
