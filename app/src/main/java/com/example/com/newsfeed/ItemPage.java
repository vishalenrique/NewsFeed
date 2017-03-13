package com.example.com.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

public class ItemPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<String>> {
    StringBuilder builder=new StringBuilder("https://content.guardianapis.com/");
    String postFix="?api-key=3a02257a-9deb-4c87-b247-4b3dc7ad0304&show-blocks=body";
    String finalURL;
    TextView textView;
    TextView webView;
    StringBuilder show=new StringBuilder();
    ProgressBar progressBar;
    TextView emptyTextView;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_page);
        progressBar= (ProgressBar) findViewById(R.id.item_bar);
        emptyTextView= (TextView) findViewById(R.id.empty_item);
         title=getIntent().getStringExtra("title");
        String id=(getIntent().getStringExtra("id"));
        textView= (TextView) findViewById(R.id.item_title);
        webView= (TextView) findViewById(R.id.item_content_show);
//        builder.append("technology/2014/feb/18/doge-such-questions-very-answered");
          builder.append(id);
        builder.append(postFix);
        finalURL=builder.toString();
        show.append("\t");
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(1,null,this);
        } else {
            progressBar.setVisibility(View.GONE);
            emptyTextView.setText(getString(R.string.internet));
        }
    }

    @Override
    public Loader<List<String>> onCreateLoader(int id, Bundle args) {
        return new ItemLoader(this,finalURL);
    }

    @Override
    public void onLoadFinished(Loader<List<String>> loader, List<String> data) {
        if(data==null)
            emptyTextView.setText("No items to display");
        else {
//        if (TextUtils.isEmpty(show.toString())) {
            for (String s : data) {
                show.append(s);
                show.append("\n");
//            }
            }
            progressBar.setVisibility(View.GONE);
            textView.setText(title);
            webView.setText(show.toString());
        }
    }

    @Override
    public void onLoaderReset(Loader<List<String>> loader) {
        show=null;
    }
}
