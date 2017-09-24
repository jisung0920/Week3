package com.example.jisung.myapplication;

/**
 * Created by jisung on 2017-09-19.
 */

public class Webdata {
    private String name,urldata;

    public Webdata(String name, String urldata) {
        this.name = name;
        this.urldata = urldata;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrldata() {
        return urldata;
    }

    public void setUrldata(String urldata) {
        this.urldata = urldata;
    }
}
