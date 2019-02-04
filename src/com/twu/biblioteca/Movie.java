package com.twu.biblioteca;

/**
 * Created by alexa on 4/02/2019.
 */
public class Movie {

    public final int UNRATED = 0;

    String title;
    String year;
    String director;
    int rating;


    public Movie(String title, String year, String director, int rating){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = rating;
    }

    public Movie(String title, String year, String director){
        this.title = title;
        this.year = year;
        this.director = director;
        this.rating = UNRATED;
    }

    public String getFullDetail(){
        if((title == null && year == null && director == null && rating == UNRATED) ||
                (title.isEmpty() && year.isEmpty() && director.isEmpty()))
            return "Error!!! Movie has no details";

        String rating = "Unrated";
        if(this.rating != UNRATED){
            rating = "" + this.rating;
        }
        String detail = String.format(" %-20.20s | %-4s | %-15.15s | %-7s |",
                title, year, director, rating);
        return detail;
    }



}
