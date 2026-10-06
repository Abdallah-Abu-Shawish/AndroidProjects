package com.example.storedatarealtime;

public class DataClass {

    private String title;
    private String desc;
    private String lang;
    private String image;

    public DataClass(String title, String desc, String lang, String image) {
        this.title = title;
        this.desc = desc;
        this.lang = lang;
        this.image = image;

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

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
