package com.example.movies.Entity;

import java.util.List;

public class Genres {
    private String name;
    private int id;

    public Genres(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
