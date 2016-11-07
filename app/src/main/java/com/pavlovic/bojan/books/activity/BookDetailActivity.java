package com.pavlovic.bojan.books.activity;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pavlovic.bojan.books.R;

public class BookDetailActivity extends AppCompatActivity {
    private static final String TITLE = "BookTitle", AUTHOR = "BookAuthor", COVER_URL = "BookCover", DESCRIPTION = "Description", PUBLISHER = "Publisher", ISBN = "ISBN";
    private String bookTitle, bookAuthor, bookCoverUrl, bookDescription, bookPublisher, bookISBN;
    private TextView titleTextView, authorTextView, descriptionTextView, publisherTextView, isbnTextView;
    private ImageView coverImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);


        Intent intent = getIntent();
        bookTitle = intent.getStringExtra(TITLE);
        bookAuthor = intent.getStringExtra(AUTHOR);
        bookCoverUrl = intent.getStringExtra(COVER_URL);
        bookDescription = intent.getStringExtra(DESCRIPTION);
        bookPublisher = intent.getStringExtra(PUBLISHER);
        bookISBN = intent.getStringExtra(ISBN);

        titleTextView = (TextView) findViewById(R.id.bookTitleTextView);
        authorTextView = (TextView) findViewById(R.id.bookAuthorTextView);
        descriptionTextView = (TextView) findViewById(R.id.bookDescriptionDetailTextView);
        publisherTextView = (TextView) findViewById(R.id.bookPublisherTextView);
        isbnTextView = (TextView) findViewById(R.id.bookISBNTextView);
        coverImageView = (ImageView) findViewById(R.id.bookCoverImageView);

        titleTextView.setText(bookTitle);
        authorTextView.setText(bookAuthor);
        descriptionTextView.setText(bookDescription);
        publisherTextView.setText(bookPublisher);
        isbnTextView.setText(bookISBN);

        Glide.with(this)
                .load(bookCoverUrl)
                .fitCenter()
                .crossFade()
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(coverImageView);
    }
}
