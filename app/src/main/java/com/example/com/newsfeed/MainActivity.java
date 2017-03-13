package com.example.com.newsfeed;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Item>> {

//     private String url="http://content.guardianapis.com/search?q=cricket&section=sport&order-by=newest&api-key=3a02257a-9deb-4c87-b247-4b3dc7ad0304";
private String url="http://content.guardianapis.com/search?order-by=newest&api-key=3a02257a-9deb-4c87-b247-4b3dc7ad0304";
    CustomArrayAdapter customArrayAdapter;
    TextView textView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar= (ProgressBar) findViewById(R.id.progress);
        textView= (TextView) findViewById(R.id.empty_view);
        ListView listView= (ListView) findViewById(R.id.activity_main);
        listView.setEmptyView(textView);
         customArrayAdapter=new CustomArrayAdapter(this,new ArrayList<Item>());
        listView.setAdapter(customArrayAdapter);
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            getLoaderManager().initLoader(0, null, this);
        } else {
            progressBar.setVisibility(View.GONE);
            textView.setText(getString(R.string.internet));
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Item item= (Item) parent.getItemAtPosition(position);
                String url=item.getWebURL();
//                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                if(intent.resolveActivity(getPackageManager())!=null){
//                    startActivity(intent);
//                }
                Intent i=new Intent(MainActivity.this,ItemPage.class);
                i.putExtra("id",url);
                i.putExtra("title",item.getTitle());
                startActivity(i);
            }
        });
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int id, Bundle args) {
        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
        String category=sharedPreferences.getString(getString(R.string.settings_order_by_key),getString(R.string.settings_order_by_default));
        Uri uri=Uri.parse(url);
        Uri.Builder builder=uri.buildUpon();
        if(category.equalsIgnoreCase("sport")){
            builder.appendQueryParameter("q","cricket");
        }else if(category.equalsIgnoreCase("culture")){
            builder.appendQueryParameter("q","music OR film");
        }
        builder.appendQueryParameter("section",category);
        Log.v("check",builder.toString());
        return new ItemsLoader(MainActivity.this,builder.toString());

//        SharedPreferences sharedPreferences=PreferenceManager.getDefaultSharedPreferences(this);
//        String minMagnitude=sharedPreferences.getString(getString(R.string.settings_min_magnitude_key),getString(R.string.settings_min_magnitude_default));
//        String orderBy = sharedPreferences.getString(getString(R.string.settings_order_by_key), getString(R.string.settings_order_by_default));
//        Uri uri=Uri.parse(url);
//        Uri.Builder builder=uri.buildUpon();
//        builder.appendQueryParameter("format","geojson");
//        builder.appendQueryParameter("limit","10");
//        builder.appendQueryParameter("minmag",minMagnitude);
//        builder.appendQueryParameter("orderby",orderBy);
//
//        return new EarthquakeLoader(EarthquakeActivity.this,builder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> data) {
        customArrayAdapter.clear();
        progressBar.setVisibility(View.GONE);
        textView.setText("No items to display");
        if (data!=null && !data.isEmpty()){
            customArrayAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        customArrayAdapter.clear();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this, Setting_Activity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
