package com.baulin.alexander.newsfeed.myJSON;

import com.baulin.alexander.newsfeed.myJSON.Image;

public class NewsItem {

    private String NewsItemId;
    private String HeadLine;
    private String ByLine;
    private String Agency;
    private String DateLine;
    private String WebURL;
    private String Caption;
    private Image Image;
    private String Keywords;
    private String Story;
    private String CommentCountUrl;
    private String CommentFeedUrl;
    private String Related;

    public String getNewsItemId() {
        return NewsItemId;
    }

    public void setNewsItemId(String newsItemId) {
        NewsItemId = newsItemId;
    }

    public String getHeadLine() {
        return HeadLine;
    }

    public void setHeadLine(String headLine) {
        HeadLine = headLine;
    }

    public String getByLine() {
        return ByLine;
    }

    public void setByLine(String byLine) {
        ByLine = byLine;
    }

    public String getAgency() {
        return Agency;
    }

    public void setAgency(String agency) {
        Agency = agency;
    }

    public String getDateLine() {
        return DateLine;
    }

    public void setDateLine(String dateLine) {
        DateLine = dateLine;
    }

    public String getWebURL() {
        return WebURL;
    }

    public void setWebURL(String webURL) {
        WebURL = webURL;
    }

    public String getCaption() {
        return Caption;
    }

    public void setCaption(String caption) {
        Caption = caption;
    }

    public com.baulin.alexander.newsfeed.myJSON.Image getImage() {
        return Image;
    }

    public void setImage(com.baulin.alexander.newsfeed.myJSON.Image image) {
        Image = image;
    }

    public String getKeywords() {
        return Keywords;
    }

    public void setKeywords(String keywords) {
        Keywords = keywords;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getCommentCountUrl() {
        return CommentCountUrl;
    }

    public void setCommentCountUrl(String commentCountUrl) {
        CommentCountUrl = commentCountUrl;
    }

    public String getCommentFeedUrl() {
        return CommentFeedUrl;
    }

    public void setCommentFeedUrl(String commentFeedUrl) {
        CommentFeedUrl = commentFeedUrl;
    }

    public String getRelated() {
        return Related;
    }

    public void setRelated(String related) {
        Related = related;
    }

}
