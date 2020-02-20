package com.app.datajson;

import java.util.ArrayList;

public class GlobalDataModel {

    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> description = new ArrayList<String>();
    private ArrayList<String> url = new ArrayList<String>();

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.add(title);
    }

    public ArrayList<String> getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description.add(description);
    }

    public ArrayList<String> getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url.add(url);
    }
}
