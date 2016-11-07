package com.pavlovic.bojan.books.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.activity.BookDetailActivity;
import com.pavlovic.bojan.books.activity.MainActivity;

import java.util.List;

/**
 * Created by X on 11/4/2016.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private List<MainActivity.Book> books;
    private Context context;
    private static final String TAG = BooksAdapter.class.getSimpleName();
    private static final String TITLE = "BookTitle", AUTHOR = "BookAuthor", COVER_URL = "BookCover", DESCRIPTION = "Description", PUBLISHER = "Publisher", ISBN = "ISBN";

    public BooksAdapter(List<MainActivity.Book> books, Context context){
        this.books = books;
        this.context = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        MainActivity.Book currentBook = books.get(position);
        holder.bookAuthor.setText(currentBook.author.getAuthorFirstLastName());
        holder.bookTitle.setText(currentBook.title);
        holder.bookDescription = currentBook.description;
        holder.bookImageUrl = currentBook.cover_url;
        holder.bookISBN = currentBook.isbn;
        holder.bookPublisher = currentBook.publisher.getPublisherName();

//        okhttp3.OkHttpClient okHttp3Client = new okhttp3.OkHttpClient();

//        okhttp3.OkHttpClient okHttp3Client = new okhttp3.OkHttpClient().newBuilder().cache(new Cache(context.getApplicationContext().getCacheDir(), 1024l)).build();
        /*
        okhttp3.OkHttpClient.Builder builder = new okhttp3.OkHttpClient.Builder();
        builder.cache(new Cache(context.getApplicationContext().getCacheDir(),1024));
        okhttp3.OkHttpClient client = builder.build();
        */

//        okhttp3.OkHttpClient.Builder b = okHttp3Client.newBuilder();
//        okHttp3Client.newBuilder().cache(new Cache(context.getCacheDir(),1024)).build();

        /*
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttp3Client);
        Picasso picasso = new Picasso.Builder(holder.itemView.getContext())
                .downloader(okHttp3Downloader)
                .build();

        picasso.setIndicatorsEnabled(true);
        picasso.setLoggingEnabled(true);

        picasso.load(currentBook.cover_url)
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(holder.bookImage);
        */

        /*
        Picasso.Builder picassoBuilder = new Picasso.Builder(context.getApplicationContext());
        picassoBuilder.downloader(new OkHttpDownloader(context.getApplicationContext()));
        picassoBuilder.build()
                .load(currentBook.cover_url)
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(holder.bookImage);
         */

/*
        Picasso.with(holder.itemView.getContext())
                .load(currentBook.cover_url)
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .into(holder.bookImage);
*/

        Glide.with(holder.itemView.getContext())
                .load(currentBook.cover_url)
                .placeholder(R.drawable.book)
                .error(R.drawable.book)
                .fitCenter()
                .into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView bookTitle, bookAuthor;
        public ImageView bookImage;
        public String bookImageUrl, bookISBN, bookPublisher, bookDescription;

        public BookViewHolder(View itemView) {
            super(itemView);
            bookImage = (ImageView) itemView.findViewById(R.id.imageView);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitleTextView);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthorTextView);
            bookDescription = ((TextView) itemView.findViewById(R.id.bookDescriptionTextView)).getText().toString();
            bookPublisher = ((TextView) itemView.findViewById(R.id.bookPublisherTextView)).getText().toString();
            bookISBN = ((TextView) itemView.findViewById(R.id.bookIsbnTextView)).getText().toString();
            bookImageUrl = ((TextView)itemView.findViewById(R.id.bookCoverImageUrlTextView)).getText().toString();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "Clicked on: " + bookTitle.getText().toString());
            Intent intent = new Intent(v.getContext(), BookDetailActivity.class);
            intent.putExtra(TITLE, bookTitle.getText().toString());
            intent.putExtra(AUTHOR, bookAuthor.getText().toString());
            intent.putExtra(DESCRIPTION, bookDescription);
            intent.putExtra(COVER_URL, bookImageUrl);
            intent.putExtra(PUBLISHER, bookPublisher);
            intent.putExtra(ISBN, bookISBN);
//            intent.putExtra(COVER, bookImage.get)
            v.getContext().startActivity(intent);
        }
    }
}
