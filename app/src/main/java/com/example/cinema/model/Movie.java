package com.example.cinema.model;

import java.io.Serializable;

/**
 * Created by 轻薄的呵呵哒 on 2018/6/4.
 */

public class Movie implements Serializable{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;
    private String director;
    private String price;
    private String time;
    private String description;
    private String actor;
    private String type;

    public Movie(int id, String name, String director, String actor, String price, String time, String type, String description) {
        this.id = id;
        this.name = name;
        this.director = director;
        this.price = price;
        this.time = time;
        this.description = description;
        this.actor = actor;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
