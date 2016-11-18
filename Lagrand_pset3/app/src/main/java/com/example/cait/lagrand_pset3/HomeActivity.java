package com.example.cait.lagrand_pset3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void search(View view) {
        // Get user input
        EditText userInput = (EditText) findViewById(R.id.searchText);

        // Go to result page
        Intent goToResults = new Intent(this, ResultsActivity.class);
        goToResults.putExtra("title", userInput.getText().toString());
        startActivity(goToResults);
        finish();
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
