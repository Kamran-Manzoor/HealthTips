package com.example.salman.healthtips;

/**
 * Created by Salman on 7/15/2019.
 */
public class Data {

    private String title;
    private String content;
    private String url;
public Data (){}
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
       return this.content = content.replace("_b","\n");
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Data(String title, String content, String url) {
        this.title = title;
        this.content = content;
        this.url = url;
    }
}