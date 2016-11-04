package com.pavlovic.bojan.books.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 11/4/2016.
 */

public class Book {
    @SerializedName("books")
    private List<String> books = new ArrayList<String>();

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }

    public Book(List<String> books) {
        this.books = books;
    }
}
