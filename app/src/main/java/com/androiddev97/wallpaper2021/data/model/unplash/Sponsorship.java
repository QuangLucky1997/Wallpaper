package com.androiddev97.wallpaper2021.data.model.unplash;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sponsorship {
    @SerializedName("impression_urls")
    @Expose
    private List<String> impressionUrls = null;
    @SerializedName("tagline")
    @Expose
    private String tagline;
    @SerializedName("tagline_url")
    @Expose
    private String taglineUrl;
    @SerializedName("sponsor")
    @Expose
    private Sponsor sponsor;

    public List<String> getImpressionUrls() {
        return impressionUrls;
    }

    public void setImpressionUrls(List<String> impressionUrls) {
        this.impressionUrls = impressionUrls;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getTaglineUrl() {
        return taglineUrl;
    }

    public void setTaglineUrl(String taglineUrl) {
        this.taglineUrl = taglineUrl;
    }

    public Sponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(Sponsor sponsor) {
        this.sponsor = sponsor;
    }
}
