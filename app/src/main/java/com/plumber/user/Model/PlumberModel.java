package com.plumber.user.Model;

public class PlumberModel {
    String id,name,rating;
    int image;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getImage() {
        return image;
    }

    public String getRating() {
        return rating;
    }

    public PlumberModel(String id, String name, String rating, int image) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.image = image;
    }
}
