package com.training.proj.seldemo;

/**
 * Created by anmolchandni on 11/29/17.
 */

public class NewsData {

    private String newsHeadline;

    public String getNewsHeadline() {
        return newsHeadline;
    }

    public void setNewsHeadline(String newsHeadline) {
        this.newsHeadline = newsHeadline;
    }

    public String getNewsSourceURL() {
        return newsSourceURL;
    }

    public void setNewsSourceURL(String newsSourceURL) {
        this.newsSourceURL = newsSourceURL;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    private String newsSourceURL;
    private String newsTime;
}
