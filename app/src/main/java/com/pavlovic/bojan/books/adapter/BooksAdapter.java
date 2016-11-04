package com.pavlovic.bojan.books.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.activity.MainActivity;
import com.pavlovic.bojan.books.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by X on 11/4/2016.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private List<MainActivity.Book> books;
    private int rowLayout;
    private Context context;

    public BooksAdapter(List<MainActivity.Book> books, int rowLayout, Context context) {
        this.books = books;
        this.rowLayout = rowLayout;
        this.context  =context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent,false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        MainActivity.Book currentBook = books.get(position);
        holder.bookAuthor.setText("author test" + position);
        holder.bookTitle.setText("title test" + position);
        Picasso.with(holder.itemView.getContext())
                .load("https://placeholdit.imgix.net/~text?txtsize=85&bg=d0d5da&txtclr=336699%26text%3D1&txt=1&w=248&h=340")
                .into(holder.bookImage);
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder{

        LinearLayout booksLayout;
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
