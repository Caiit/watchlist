package com.example.cait.lagrand_pset3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

class ImageAsyncTask extends AsyncTask<String, Integer, Bitmap> {
    private Context context;
    private ResultsActivity activity;

    // Constructor
    ImageAsyncTask(ResultsActivity activity) {
        this.activity = activity;
        this.context = this.activity.getApplicationContext();
    }

    protected void onPreExecute() {
        // I left this part out, because the data was obtained really quick and thus the toast
        // was still shown while the data was already on the screen
//        Toast.makeText(context, "Getting image", Toast.LENGTH_SHORT).show();
    }

    protected Bitmap doInBackground(String... params) {
        if (params[0].equals("N/A")) return null;
        URL url;
        try {
            url = new URL(params[0]);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    protected void onProgressUpdate(String... params) {
        // This method is never used
        Toast.makeText(context, "Still going strong", Toast.LENGTH_SHORT).show();
    }

    protected void onPostExecute(Bitmap bmp) {
        super.onPostExecute(bmp);

        if (bmp == null) {
            Toast.makeText(context, "No image was found", Toast.LENGTH_SHORT).show();
        }
        this.activity.showImage(bmp);
    }
}
