package com.example.cinema.backup.model;

import java.io.Serializable;

/**
 * Created by 轻薄的呵呵哒 on 2018/6/5.
 */

public class Screen implements Serializable{
    private static final long serialVersionUID = 1L;

    private int movieId;
    private int screenId;
    private String screenDate;
    private String screenTime;
    private String screenPlace;

    public Screen(int screenId, int movieId, String screenDate, String screenTime, String screenPlace) {
        this.movieId = movieId;
        this.screenId = screenId;
        this.screenDate = screenDate;
        this.screenTime = screenTime;
        this.screenPlace = screenPlace;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenDate() {
        return screenDate;
    }

    public void setScreenDate(String screenDate) {
        this.screenDate = screenDate;
    }

    public String getScreenTime() {
        return screenTime;
    }

    public void setScreenTime(String screenTime) {
        this.screenTime = screenTime;
    }

    public String getScreenPlace() {
        return screenPlace;
    }

    public void setScreenPlace(String screenPlace) {
        this.screenPlace = screenPlace;
    }
}
