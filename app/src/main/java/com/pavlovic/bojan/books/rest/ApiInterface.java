package com.pavlovic.bojan.books.rest;

import com.pavlovic.bojan.books.activity.MainActivity;
import com.pavlovic.bojan.books.model.BooksResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by X on 11/4/2016.
 */

public interface ApiInterface {
    @GET("books/highlighted")
    public Call<BooksResponse> getHighlightedBooks();

    @GET("books/{id}")
    Call<BooksResponse> getSpecificBook(@Path("id") int id);

    // Add more here !!!
}
