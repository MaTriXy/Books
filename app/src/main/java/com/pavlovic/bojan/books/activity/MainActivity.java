package com.pavlovic.bojan.books.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.adapter.BooksAdapter;
import com.pavlovic.bojan.books.adapter.BooksAutocompleteAdapter;
import com.pavlovic.bojan.books.custom.DelayAutocompleteTextView;
import com.pavlovic.bojan.books.model.BooksResponse;
import com.pavlovic.bojan.books.rest.ApiClient;
import com.pavlovic.bojan.books.rest.ApiInterface;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    public List<Book> books;
    private Context applicationContext;
    private AlertDialog alertDialog;
    private static final String TITLE = "BookTitle", AUTHOR = "BookAuthor", COVER_URL = "BookCover", DESCRIPTION = "Description", PUBLISHER = "Publisher", ISBN = "ISBN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = getApplicationContext();

        if(!checkIfConnectedToInternet()){
            showAlert();
        }

        books = new ArrayList<>();

        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowCustomEnabled(true);

        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.action_bar_layout, null);
        actionBar.setCustomView(view);

//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new String[]{"Beograd","Nis", "Novi Sad", "Kragujevac", "Kraljevo"});
//        AutoCompleteTextView autoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.autoCompleteTextView);
        final DelayAutocompleteTextView autoCompleteTextView = (DelayAutocompleteTextView) findViewById(R.id.autoCompleteTextView);
        autoCompleteTextView.setThreshold(3);
        autoCompleteTextView.setAdapter(new BooksAutocompleteAdapter(this));
        autoCompleteTextView.setLoadingIndicator((ProgressBar) findViewById(R.id.progressBar));
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String title = ((TextView)view.findViewById(R.id.title)).getText().toString();

                autoCompleteTextView.setText(title);

                String description = ((TextView)view.findViewById(R.id.description)).getText().toString();
                String author = ((TextView)view.findViewById(R.id.author)).getText().toString();
                String publisher = ((TextView)view.findViewById(R.id.publisher)).getText().toString();
                String isbn = ((TextView)view.findViewById(R.id.isbn)).getText().toString();
                String cover_url = ((TextView)view.findViewById(R.id.cover_url)).getText().toString();

                Intent intent = new Intent(view.getContext(), BookDetailActivity.class);
                intent.putExtra(TITLE, title);
                intent.putExtra(AUTHOR, author);
                intent.putExtra(COVER_URL, cover_url);
                intent.putExtra(DESCRIPTION, description);
                intent.putExtra(PUBLISHER, publisher);
                intent.putExtra(ISBN, isbn);

                startActivity(intent);


//                Log.d("boki", ((TextView)view.findViewById(R.id.testView1)).getText().toString());
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
                Toast.makeText(MainActivity.this, "Loading Highlighted Books Failed", Toast.LENGTH_LONG).show();
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
        public  String title, description, cover_url, isbn;
        public Publisher publisher;
        public Author author;

        public Book(int id, String title, String description, String cover_url, String isbn, Publisher publisher, Author author) {
            this.id = id;
            this.title = title;
            this.description = description;
            this.cover_url = cover_url;
            this.isbn = isbn;
            this.publisher = publisher;
            this.author = author;
        }
    }

    public static class Publisher{
        public int id;
        public String name;

        public Publisher(int id, String name){
            this.id = id;
            this.name = name;
        }

        public String getPublisherName(){
            return name;
        }

        public int getPublisherId(){
            return id;
        }
    }

    public static class Author{
        int id;
        String first_name, last_name;

        public Author(int id, String first_name, String last_name){
            this.id = id;
            this.first_name = first_name;
            this.last_name = last_name;
        }

        public String getAuthorFirstLastName(){
            return first_name + " " + last_name;
        }

        public int getAuthorId(){
            return  id;
        }
    }

    // Check if connected.
    private boolean checkIfConnectedToInternet(){
        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isAvailable() && connectivityManager.getActiveNetworkInfo().isConnected()){

            connected = true;

        }
        return connected;
    }

    private void showAlert(){
        alertDialog = new AlertDialog.Builder(this)
                .setTitle("Not connected to internet")
                .setMessage("Please Check Internet Connection")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Close Application.
                        MainActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

}
