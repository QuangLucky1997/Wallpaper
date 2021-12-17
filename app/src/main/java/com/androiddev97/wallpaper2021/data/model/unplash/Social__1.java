package com.androiddev97.wallpaper2021.data.model.unplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Social__1 {

    @SerializedName("instagram_username")
    @Expose
    private String instagramUsername;
    @SerializedName("portfolio_url")
    @Expose
    private String portfolioUrl;
    @SerializedName("twitter_username")
    @Expose
    private String twitterUsername;
    @SerializedName("paypal_email")
    @Expose
    private Object paypalEmail;

    public String getInstagramUsername() {
        return instagramUsername;
    }

    public void setInstagramUsername(String instagramUsername) {
        this.instagramUsername = instagramUsername;
    }

    public String getPortfolioUrl() {
        return portfolioUrl;
    }

    public void setPortfolioUrl(String portfolioUrl) {
        this.portfolioUrl = portfolioUrl;
    }

    public String getTwitterUsername() {
        return twitterUsername;
    }

    public void setTwitterUsername(String twitterUsername) {
        this.twitterUsername = twitterUsername;
    }

    public Object getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(Object paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

}
