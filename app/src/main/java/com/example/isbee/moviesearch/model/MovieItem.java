package com.example.isbee.moviesearch.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public final class MovieItem {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("image")
    @Expose
    private String imageURL;
    @SerializedName("subtitle")
    @Expose
    private String subtitle;
    @SerializedName("pubDate")
    @Expose
    private String pubDate;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("actor")
    @Expose
    private String actor;
    @SerializedName("userRating")
    @Expose
    private String userRating;

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getDirector() {
        return director;
    }

    public String getActor() {
        return actor;
    }

    public String getUserRating() {
        return userRating;
    }
}
