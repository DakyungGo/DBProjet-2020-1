package com.example.museum;

public class BookData {

    public String book_name;
    public String published_year;
    public String book_url;
    public String book_image;

    public BookData(String bn, String py, String bu, String bi)
    {
        this.book_name = bn;
        this.published_year = py;
        this.book_url = bu;
        this.book_image = bi;
    }

    public String getBook_name() {
        return this.book_name;
    }

    public String getPublished_year(){
        return this.published_year;
    }

    public String getBook_url(){
        return this.book_url;
    }

    public String getBook_image(){
        return this.book_image;
    }

}
