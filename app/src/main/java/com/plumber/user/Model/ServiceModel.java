package com.plumber.user.Model;

public class ServiceModel {
    String id,name;
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

    public ServiceModel(String id, String name, int image) {
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
