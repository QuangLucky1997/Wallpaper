package com.androiddev97.wallpaper2021.data.model.unplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicSubmissions {

    @SerializedName("business-work")
    @Expose
    private BusinessWork businessWork;
    @SerializedName("technology")
    @Expose
    private Technology technology;

    public BusinessWork getBusinessWork() {
        return businessWork;
    }

    public void setBusinessWork(BusinessWork businessWork) {
        this.businessWork = businessWork;
    }

    public Technology getTechnology() {
        return technology;
    }

    public void setTechnology(Technology technology) {
        this.technology = technology;
    }
}
