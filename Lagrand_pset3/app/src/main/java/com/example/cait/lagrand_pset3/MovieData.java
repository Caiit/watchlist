package com.example.cait.lagrand_pset3;

import java.io.Serializable;

class MovieData implements Serializable {

    String title;
    String imgURL;
    String released;
    String rated;
    String time;
    String genre;
    String director;
    String plot;

    MovieData(String title, String imgURL, String released, String rated, String time,
              String genre, String director, String plot) {
        this.title = title;
        this.imgURL = imgURL;
        this.released = released;
        this.rated = rated;
        this.time = time;
        this.genre = genre;
        this.director = director;
        this.plot = plot;
    }
}
