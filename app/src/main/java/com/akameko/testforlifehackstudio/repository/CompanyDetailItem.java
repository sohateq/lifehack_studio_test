package com.akameko.testforlifehackstudio.repository;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyDetailItem {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("img")
    @Expose
    private String img;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("www")
    @Expose
    private String www;
    @SerializedName("phone")
    @Expose
    private String phone;

    public CompanyDetailItem(Integer id, String name, String img, String description, String lat, String lon, String www, String phone) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.www = www;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public String getDescription() {
        return description;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getWww() {
        return www;
    }

    public String getPhone() {
        return phone;
    }
}
