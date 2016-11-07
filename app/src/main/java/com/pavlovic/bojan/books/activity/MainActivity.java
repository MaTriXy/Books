package com.pavlovic.bojan.books.activity;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.adapter.BooksAdapter;
import com.pavlovic.bojan.books.adapter.BooksAutocompleteAdapter;
import com.pavlovic.bojan.books.custom.DelayAutocompleteTextView;
import com.pavlovic.bojan.books.model.Book;
import com.pavlovic.bojan.books.model.BooksResponse;
import com.pavlovic.bojan.books.rest.ApiClient;
import com.pavlovic.bojan.books.rest.ApiInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public List<Book> books;
    private Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = getApplicationContext();
        books = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar_layout, null);
        actionBar.setCustomView(view);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[]{"Beograd","Nis", "Novi Sad", "Kragujevac", "Kraljevo"});
//        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        DelayAutocompleteTextView autoCompleteTextView = (DelayAutocompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(3);
        autoCompleteTextView.setAdapter(new BooksAutocompleteAdapter(this));
        autoCompleteTextView.setLoadingIndicator((ProgressBar) findViewById(R.id.progressBar));
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("boki", ((TextView)view.findViewById(R.id.testView1)).getText().toString());
            }
        });

//        autoCompleteTextView.setAdapter(adapter);


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.books_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);



        Call<BooksResponse> call = apiService.getHighlightedBooks();
        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                BooksResponse booksResponse = response.body();
                books = booksResponse.getBooks();
                recyclerView.setAdapter(new BooksAdapter(books, applicationContext));
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
