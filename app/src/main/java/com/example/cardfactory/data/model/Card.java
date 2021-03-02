package com.example.cardfactory.data.model;

public class Card {

    private int mCode;
    private String mTitle;
    private String mDescription;
    private String mTag;
    private String mImageLink;
    private String mSoundLink;

    public Card(int code, String title, String description, String tag) {
        mCode = code;
        mTitle = title;
        mDescription = description;
        mTag = tag;
    }

    public int getCode() {
        return mCode;
    }

    public void setCode(int code) {
        mCode = code;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getTag() {
        return mTag;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getImageLink() {
        return mImageLink;
    }

    public void setImageLink(String imageLink) {
        mImageLink = imageLink;
    }

    public String getSoundLink() {
        return mSoundLink;
    }

    public void setSoundLink(String soundLink) {
        mSoundLink = soundLink;
    }
}
