package com.softcustomer.perfectfit.models;

public class Plan extends Model {
    private String[] items;
    private String title;

    public Plan(String[] sports, String title) {
        this.items = sports;
        this.title = title;
    }

    public String[] getItems() {
        return items;
    }

    public void setItems(String[] items) {
        this.items = items;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
