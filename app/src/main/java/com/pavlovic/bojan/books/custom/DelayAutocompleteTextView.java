package com.pavlovic.bojan.books.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

/**
 * Created by X on 11/7/2016.
 */

public class DelayAutocompleteTextView extends AutoCompleteTextView {

    private static final int MESSAGE_TEXT_CHANGED = 100;
    private static final int DEFAULT_AUTOCOMPLETE_DELAY = 750;

    private int autocompleteDelay = DEFAULT_AUTOCOMPLETE_DELAY;
    private ProgressBar loadingIndicator;

    private final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
            DelayAutocompleteTextView.super.performFiltering((CharSequence) msg.obj, msg.arg1);
        }
    };

    public DelayAutocompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setLoadingIndicator(ProgressBar progressBar){
        loadingIndicator = progressBar;
    }

    public void setAutocompleteDelay(int autocompleteDelay){
        this.autocompleteDelay = autocompleteDelay;
    }

    @Override
    protected void performFiltering(CharSequence text, int keyCode){
        if(loadingIndicator != null){
            loadingIndicator.setVisibility(View.VISIBLE);
        }
        handler.removeMessages(MESSAGE_TEXT_CHANGED);
        handler.sendMessageDelayed(handler.obtainMessage(MESSAGE_TEXT_CHANGED, text), autocompleteDelay);
    }

    @Override
    public void onFilterComplete(int count) {
        if(loadingIndicator != null){
            loadingIndicator.setVisibility(View.GONE);
        }
        super.onFilterComplete(count);
    }
}
