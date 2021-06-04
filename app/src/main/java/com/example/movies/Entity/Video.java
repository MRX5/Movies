package com.example.movies.Entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Video {
    @SerializedName("results")
    private List<youTubeKeys>results;

    public List<youTubeKeys> getResults() {
        return results;
    }

    public void setResults(List<youTubeKeys> results) {
        this.results = results;
    }

    public class youTubeKeys
    {
        private String type;
        private String key;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }
    }
}
