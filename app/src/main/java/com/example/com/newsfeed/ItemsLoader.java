package com.example.com.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal Bhati on 3/11/2017.
 */

public class ItemsLoader extends AsyncTaskLoader<List<Item>> {
    private String urlString;
    ItemsLoader(Context context, String url) {
        super(context);
        this.urlString=url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<Item> loadInBackground() {
        String jsonFormat;
        URL url=QueryUtils.createURL(urlString);
        jsonFormat=QueryUtils.makeHttpConnection(url);
        return QueryUtils.extractFromJSON(jsonFormat);
    }
}
