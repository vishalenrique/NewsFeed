package com.example.com.newsfeed;

/**
 * Created by Vishal Bhati on 3/10/2017.
 */

public class Item {
    private String title;
    private String section;
    private String date;
    private String webURL;

    public Item(String title, String section, String date,String webURL) {
        this.title = title;
        this.section = section;
        this.date = date;
        this.webURL=webURL;
    }

    public String getDate() {
        return date;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getSection() {
        return section;
    }

    public String getTitle() {
        return title;
    }
}
