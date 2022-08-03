package com.example.digitalvideostoreapi.models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("movies")
public class Movie {

    @Id
    private String id;
    private String title;
    private String details;
    private String type;
    private Boolean featuredMovie;
    private Boolean featuredShow;
    private Boolean mostDemanded;
    private String small_poster;
    private String large_poster;
    private int rent_amount;
    private int purchase_amount;
    private String image;

    public Movie()
    {

    }

    public Movie(String id, String title, String details, String type, Boolean featuredMovie, Boolean featuredShow, Boolean mostDemanded, String small_poster, String large_poster, int rent_amount, int purchase_amount, String image) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.type = type;
        this.featuredMovie = featuredMovie;
        this.featuredShow = featuredShow;
        this.mostDemanded = mostDemanded;
        this.small_poster = small_poster;
        this.large_poster = large_poster;
        this.rent_amount = rent_amount;
        this.purchase_amount = purchase_amount;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Boolean getFeaturedMovie() {
        return featuredMovie;
    }

    public void setFeaturedMovie(Boolean featuredMovie) {
        this.featuredMovie = featuredMovie;
    }

    public Boolean getFeaturedShow() {
        return featuredShow;
    }

    public void setFeaturedShow(Boolean featuredShow) {
        this.featuredShow = featuredShow;
    }

    public Boolean getMostDemanded() {
        return mostDemanded;
    }

    public void setMostDemanded(Boolean mostDemanded) {
        this.mostDemanded = mostDemanded;
    }

    public String getSmall_poster() {
        return small_poster;
    }

    public void setSmall_poster(String small_poster) {
        this.small_poster = small_poster;
    }

    public String getLarge_poster() {
        return large_poster;
    }

    public void setLarge_poster(String large_poster) {
        this.large_poster = large_poster;
    }

    public int getRent_amount() {
        return rent_amount;
    }

    public void setRent_amount(int rent_amount) {
        this.rent_amount = rent_amount;
    }

    public int getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(int purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", details='" + details + '\'' +
                ", type='" + type + '\'' +
                ", featuredMovie=" + featuredMovie +
                ", featuredShow=" + featuredShow +
                ", mostDemanded=" + mostDemanded +
                ", small_poster='" + small_poster + '\'' +
                ", large_poster='" + large_poster + '\'' +
                ", rent_amount=" + rent_amount +
                ", purchase_amount=" + purchase_amount +
                ", image='" + image + '\'' +
                '}';
    }
}
