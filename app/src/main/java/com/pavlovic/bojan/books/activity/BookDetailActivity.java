package com.pavlovic.bojan.books.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.pavlovic.bojan.books.R;

public class BookDetailActivity extends AppCompatActivity {
    private static final String TITLE = "BookTitle", AUTHOR = "BookAuthor", COVER = "BookCover";
    private String bookTitle, bookAuthor;
    private TextView titleTextView, authorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Intent intent = getIntent();
        bookTitle = intent.getStringExtra(TITLE);
        bookAuthor = intent.getStringExtra(AUTHOR);

        titleTextView = (TextView) findViewById(R.id.bookTitleTextView);
        authorTextView = (TextView) findViewById(R.id.bookAuthorTextView);

        titleTextView.setText(bookTitle);
        authorTextView.setText(bookAuthor);
    }
}
