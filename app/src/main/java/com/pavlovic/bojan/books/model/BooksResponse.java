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
    int a = 0;

    public BooksResponse(){
        books = new ArrayList<MainActivity.Book>();
    }

    public static BooksResponse parseJSON(String response) {
        Gson gson = new GsonBuilder().create();
        BooksResponse movieResponse = gson.fromJson(response, BooksResponse.class);
        return movieResponse;
    }
}
