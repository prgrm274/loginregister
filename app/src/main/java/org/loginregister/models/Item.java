package org.loginregister.models;

public class Item {
    private int coverImageResource;
    private int circleImageResource;
    private String title1;
    private String title2;
    private String content;

    public Item() {}

    public Item(int coverImageResource, int circleImageResource, String title1, String title2, String content) {
        this.coverImageResource = coverImageResource;
        this.circleImageResource = circleImageResource;
        this.title1 = title1;
        this.title2 = title2;
        this.content = content;
    }

    public int getCoverImageResource() {
        return coverImageResource;
    }

    public void setCoverImageResource(int coverImageResource) {
        this.coverImageResource = coverImageResource;
    }

    public int getCircleImageResource() {
        return circleImageResource;
    }

    public void setCircleImageResource(int circleImageResource) {
        this.circleImageResource = circleImageResource;
    }

    public String getTitle1() {
        return title1;
    }

    public void setTitle1(String title1) {
        this.title1 = title1;
    }

    public String getTitle2() {
        return title2;
    }

    public void setTitle2(String title2) {
        this.title2 = title2;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
