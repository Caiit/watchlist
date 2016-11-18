package com.example.cait.lagrand_pset3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cait on 11/15/16.
 */

public class HttpRequestHelper {

    private static final String urlString = "http://www.omdbapi.com/?t=";

    protected static synchronized String downloadFromServer(String... params) {

        // Declare return string result
        String result = "";

        // Get chosen tag from argument
        String chosenTag = params[0];

        // Turn string into url
        URL url;
        try {
            url = new URL(urlString + chosenTag);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return result;
        }

        // Make the connection
        HttpURLConnection connection;
        try {
            // open connection, set request method
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // get response code
            Integer responseCode = connection.getResponseCode();

            // if 200-300, read inputstream
            if (200 >= responseCode && responseCode <= 299) {
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {
                    result += line;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }
}