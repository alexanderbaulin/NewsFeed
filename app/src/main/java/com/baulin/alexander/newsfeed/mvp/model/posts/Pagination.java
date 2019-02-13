package com.baulin.alexander.newsfeed.mvp.model.posts;

public class Pagination {

    public String TotalPages;
    public String PageNo;
    public String PerPage;
    public String WebURL;


    public Pagination(String totalPages, String pageNo, String perPage, String webURL) {
        TotalPages = totalPages;
        PageNo = pageNo;
        PerPage = perPage;
        WebURL = webURL;
    }

    public String getTotalPages() {
        return TotalPages;
    }

    public void setTotalPages(String totalPages) {
        TotalPages = totalPages;
    }

    public String getPageNo() {
        return PageNo;
    }

    public void setPageNo(String pageNo) {
        PageNo = pageNo;
    }

    public String getPerPage() {
        return PerPage;
    }

    public void setPerPage(String perPage) {
        PerPage = perPage;
    }

    public String getWebURL() {
        return WebURL;
    }

    public void setWebURL(String webURL) {
        WebURL = webURL;
    }

}
