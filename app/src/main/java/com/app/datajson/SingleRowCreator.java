package com.app.datajson;

public class SingleRowCreator {

    private String title;
    private String description;
    private String url;

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    SingleRowCreator(String title, String desc, String url) {
        this.title = title;
        this.description = desc;
        this.url = url;
    }
}
