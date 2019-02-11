package com.baulin.alexander.newsfeed.myJSON;

public class Pagination {

    private String TotalPages;
    private String PageNo;
    private String PerPage;
    private String WebURL;

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
