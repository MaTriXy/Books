package com.pavlovic.bojan.books.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.adapter.BooksAdapter;
import com.pavlovic.bojan.books.model.Book;
import com.pavlovic.bojan.books.model.BooksResponse;
import com.pavlovic.bojan.books.rest.ApiClient;
import com.pavlovic.bojan.books.rest.ApiInterface;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.books_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



        Call<BooksResponse> call = apiService.getHighlightedBooks();
        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                BooksResponse booksResponse = response.body();
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });



/*
        try{
            List<Book> books = call.execute().body();
        }catch(Exception e){
            Log.e(TAG, e.toString());
        }

*/
    }


    public static class Book {
        public  int id;
        public  String title, decription, cover_url, isbn;

        public Book(int id, String title, String decription, String cover_url, String isbn) {
            this.id = id;
            this.title = title;
            this.decription = decription;
            this.cover_url = cover_url;
            this.isbn = isbn;
        }
    }

}
