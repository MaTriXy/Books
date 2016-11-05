package com.pavlovic.bojan.books.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pavlovic.bojan.books.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by X on 11/4/2016.
 */

public class BooksResponse {
    List<MainActivity.Book> books;

    public BooksResponse(){
        books = new ArrayList<MainActivity.Book>();
    }

    public List<MainActivity.Book> getBooks() {
        return books;
    }

    public static BooksResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        BooksResponse movieResponse = gson.fromJson(response, BooksResponse.class);
        return movieResponse;
    }
}
