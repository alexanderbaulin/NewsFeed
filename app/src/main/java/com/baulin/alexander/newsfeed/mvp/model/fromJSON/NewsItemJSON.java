package com.baulin.alexander.newsfeed.mvp.model.fromJSON;

public class NewsItemJSON {

    public String NewsItemId;
    public String HeadLine;
    public String ByLine;
    public String Agency;
    public String DateLine;
    public String WebURL;
    public String Caption;
    public Image Image;
    public String Keywords;
    public String Story;
    public String CommentCountUrl;
    public String CommentFeedUrl;
    public String Related;


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

    public com.baulin.alexander.newsfeed.mvp.model.fromJSON.Image getImage() {
        return Image;
    }

    public void setImage(com.baulin.alexander.newsfeed.mvp.model.fromJSON.Image image) {
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
