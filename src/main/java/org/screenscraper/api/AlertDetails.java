package org.screenscraper.api;

import org.json.JSONObject;

public class AlertDetails {
    private int alertType;
    private String title;
    private String desc;
    private String url;
    private String imageSrc;
    private String postedBy;
    private int price;

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public int getAlertType() {
        return alertType;
    }

    public void setAlertType(int alertType) {
        this.alertType = alertType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String toJson(){
        JSONObject details = new JSONObject();

        details.put("alertType", this.getAlertType());
        details.put("heading", this.getTitle());
        details.put("description", this.getDesc());
        details.put("url", this.getUrl());
        details.put("imageUrl", this.getImageSrc());
        details.put("postedBy", this.getPostedBy());
        details.put("priceInCents", this.getPrice());

        return details.toString();
    }
}
