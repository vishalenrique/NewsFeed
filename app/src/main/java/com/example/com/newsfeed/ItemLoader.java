package com.example.com.newsfeed;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal Bhati on 3/13/2017.
 */

public class ItemLoader extends AsyncTaskLoader<List<String>> {
    private String urlString;
    ItemLoader(Context context, String url) {
        super(context);
        this.urlString=url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Override
    public List<String> loadInBackground() {
        String jsonFormat;
        URL url=QueryUtils.createURL(urlString);
        jsonFormat=QueryUtils.makeHttpConnection(url);
        List<String> strings=QueryUtils.extractBodyFromJSON(jsonFormat);
        StringBuilder show=new StringBuilder();
        List<String> strings1=new ArrayList<>();
        if(strings!= null) {
            for (String s : strings) {
                show.append(s);
                show.append("\n");
            }
            strings1.add(show.toString());
        }

        return strings;
    }
}
