package com.example.cait.lagrand_pset3;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

class MovieAsyncTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private ResultsActivity activity;

    // Constructor
    MovieAsyncTask(ResultsActivity activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        // I left this part out, because the data was obtained really quick and thus the toast
        // was still shown while the data was already on the screen
//        Toast.makeText(context, "Getting data from server", Toast.LENGTH_SHORT).show();
    }


    protected String doInBackground(String... params) {
        return HttpRequestHelper.downloadFromServer(params);
    }

    protected void onProgressUpdate(String... params) {
        // This method is never used
        Toast.makeText(context, "Still going strong", Toast.LENGTH_SHORT).show();
    }

    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.length() == 0) {
            Toast.makeText(context, "No data was found", Toast.LENGTH_SHORT).show();
        }
        else {
            String title = "";
            String imgURL = null;
            String released = "";
            String rated = "";
            String time = "";
            String genre = "";
            String director = "";
            String plot = "...";
            try {
                JSONObject respObj = new JSONObject(result);
                title = respObj.getString("Title");
                imgURL = respObj.getString("Poster");
                released = respObj.getString("Released");
                rated = respObj.getString("Rated");
                time = respObj.getString("Runtime");
                genre = respObj.getString("Genre");
                director = respObj.getString("Director");
                plot = respObj.getString("Plot");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            MovieData data = new MovieData(title, imgURL, released, rated, time, genre, director, plot);
            this.activity.showData(data);

            ImageAsyncTask task = new ImageAsyncTask(activity);
            task.execute(data.imgURL);

        }
    }
}