package com.example.movies.Entity;

import com.google.gson.annotations.SerializedName;

public class Cast {
    public static final String TMDB_CAST_IMAGE_PATH = "http://image.tmdb.org/t/p/w500";
    private String name;
    @SerializedName("profile_path")
    private String profilePicture;
    private String character;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return TMDB_CAST_IMAGE_PATH + profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }


}
