package com.baulin.alexander.newsfeed.model.posts;

public class Image {

    public String Photo;
    public String Thumb;
    public String PhotoCaption;

    public Image(String photo, String thumb, String photoCaption) {
        Photo = photo;
        Thumb = thumb;
        PhotoCaption = photoCaption;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }

    public String getThumb() {
        return Thumb;
    }

    public void setThumb(String thumb) {
        Thumb = thumb;
    }

    public String getPhotoCaption() {
        return PhotoCaption;
    }

    public void setPhotoCaption(String photoCaption) {
        PhotoCaption = photoCaption;
    }

}
