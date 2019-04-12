package com.example.ams.tagmaker.Models;

public class TagListModel {

    String TagName, TagDescription, secretDescription, numbeOfTags;


    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public String getTagDescription() {
        return TagDescription;
    }

    public void setTagDescription(String tagDescription) {
        TagDescription = tagDescription;
    }

    public String getSecretDescription() {
        return secretDescription;
    }

    public void setSecretDescription(String secretDescription) {
        this.secretDescription = secretDescription;
    }

    public String getNumbeOfTags() {
        return numbeOfTags;
    }

    public void setNumbeOfTags(String numbeOfTags) {
        this.numbeOfTags = numbeOfTags;
    }
}
