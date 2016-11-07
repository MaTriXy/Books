package com.pavlovic.bojan.books.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.pavlovic.bojan.books.R;
import com.pavlovic.bojan.books.activity.MainActivity;
import com.pavlovic.bojan.books.model.BooksResponse;
import com.pavlovic.bojan.books.rest.ApiClient;
import com.pavlovic.bojan.books.rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by X on 11/6/2016.
 */

public class BooksAutocompleteAdapter extends BaseAdapter implements Filterable {

    private List<String> testResults = new ArrayList<String>();
    private List<MainActivity.Book> searchedBooks;
    private Context context;
    private static final String TAG = BooksAutocompleteAdapter.class.getSimpleName();

    public BooksAutocompleteAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return testResults.size();
    }

    @Override
    public String getItem(int position) {
        return testResults.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_test1, parent, false);
        }
        ((TextView)(convertView.findViewById(R.id.testView1))).setText(getItem(position));

        return convertView;
    }

    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if(constraint != null){
                    // Fetch data here !!!
                    getSearchedBooks(constraint.toString());
                    List<String> filteredResultsss = new ArrayList<String>();

                    for(MainActivity.Book bookItem : searchedBooks){
                        filteredResultsss.add(bookItem.title);
                    }



//                    List<String> fetchedRes = new ArrayList<String>();

//                    fetchedRes.add("Paracin");
//                    fetchedRes.add("Beograd");
//                    fetchedRes.add("Budva");
//                    fetchedRes.add("Bar");
//                    fetchedRes.add("Pirot");
//                    fetchedRes.add("Negotin");

//                    for(String item : fetchedRes){
//                        if (item.contains(constraint))
//                            filteredResultsss.add(item);
//                    }
                    // TODO Implement checking for results whether letters are capitalized or not
                    filterResults.values = filteredResultsss;
                    filterResults.count = filteredResultsss.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if(results != null && results.count > 0){
                    testResults = (List<String>) results.values;
                    notifyDataSetChanged();
                }else{
                    notifyDataSetInvalidated();
                }
            }

        };
        return filter;
    }

    private void getSearchedBooks(String keyword){
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<BooksResponse> call = apiService.getBooksBySearchKeyword(keyword);
        call.enqueue(new Callback<BooksResponse>() {
            @Override
            public void onResponse(Call<BooksResponse> call, Response<BooksResponse> response) {
                BooksResponse booksResponse = response.body();
                if(booksResponse != null){
                    searchedBooks = booksResponse.getBooks();
                }
            }

            @Override
            public void onFailure(Call<BooksResponse> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
    }
}
