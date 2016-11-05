package com.pavlovic.bojan.books.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.picasso.OkHttp3Downloader;
import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.activity.MainActivity;
import com.pavlovic.bojan.books.model.Book;
import com.squareup.picasso.Downloader;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by X on 11/4/2016.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private List<MainActivity.Book> books;
    private Context context;

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
        holder.bookAuthor.setText("author");
        holder.bookTitle.setText(currentBook.title);

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

        String imgurl = currentBook.cover_url;
        String a;
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{

        public TextView bookTitle, bookAuthor;
        public ImageView bookImage;

        public BookViewHolder(View itemView) {
            super(itemView);
//            booksLayout = itemView.findViewById(R.id.)
            bookImage = (ImageView) itemView.findViewById(R.id.imageView);
            bookTitle = (TextView) itemView.findViewById(R.id.bookTitleTextView);
            bookAuthor = (TextView) itemView.findViewById(R.id.bookAuthorTextView);
        }
    }
}
