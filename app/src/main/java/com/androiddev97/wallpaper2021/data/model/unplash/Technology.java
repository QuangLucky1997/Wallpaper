package com.androiddev97.wallpaper2021.data.model.unplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Technology {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("approved_on")
    @Expose
    private String approvedOn;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprovedOn() {
        return approvedOn;
    }

    public void setApprovedOn(String approvedOn) {
        this.approvedOn = approvedOn;
    }
}
