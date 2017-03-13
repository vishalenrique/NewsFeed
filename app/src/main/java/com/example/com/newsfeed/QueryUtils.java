package com.example.com.newsfeed;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vishal Bhati on 3/11/2017.
 */

public class QueryUtils {
    private static String sampleJSON="{\"response\":{\"status\":\"ok\",\"userTier\":\"developer\",\"total\":82645,\"startIndex\":1,\"pageSize\":10,\"currentPage\":1,\"pages\":8265,\"orderBy\":\"newest\",\"results\":[{\"id\":\"culture/2017/mar/11/justin-bieber-review-searching-for-a-sense-of-purpose\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-11T00:42:38Z\",\"webTitle\":\"Justin Bieber review – searching for a sense of purpose\",\"webUrl\":\"https://www.theguardian.com/culture/2017/mar/11/justin-bieber-review-searching-for-a-sense-of-purpose\",\"apiUrl\":\"https://content.guardianapis.com/culture/2017/mar/11/justin-bieber-review-searching-for-a-sense-of-purpose\",\"isHosted\":false},{\"id\":\"music/musicblog/2017/mar/10/ed-sheeran-has-16-songs-in-the-top-20-and-its-a-sign-of-how-sick-the-charts-are\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T18:00:12Z\",\"webTitle\":\"Ed Sheeran's dominance of the Top 20 is a only a symptom of how sick the charts are\",\"webUrl\":\"https://www.theguardian.com/music/musicblog/2017/mar/10/ed-sheeran-has-16-songs-in-the-top-20-and-its-a-sign-of-how-sick-the-charts-are\",\"apiUrl\":\"https://content.guardianapis.com/music/musicblog/2017/mar/10/ed-sheeran-has-16-songs-in-the-top-20-and-its-a-sign-of-how-sick-the-charts-are\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/elliott-smith-either-or-reissue-guitar-kill-rock-stars\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T16:10:13Z\",\"webTitle\":\"Needle in the hay: Elliott Smith's incomparable brilliance lives on\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/elliott-smith-either-or-reissue-guitar-kill-rock-stars\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/elliott-smith-either-or-reissue-guitar-kill-rock-stars\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/soulwax-2manydjs-duo-on-their-new-concept-album-from-deewee\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T13:30:06Z\",\"webTitle\":\"Drums, free jazz and Neil from The Young Ones: what makes Soulwax tick?\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/soulwax-2manydjs-duo-on-their-new-concept-album-from-deewee\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/soulwax-2manydjs-duo-on-their-new-concept-album-from-deewee\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/tinariwen-review-desert-blues-electric-brixton-london-tuareg\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T12:32:30Z\",\"webTitle\":\"Tiniwaren review – desert blues tuareg rockers electric brixton london\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/tinariwen-review-desert-blues-electric-brixton-london-tuareg\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/tinariwen-review-desert-blues-electric-brixton-london-tuareg\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/halleelder-review-elgars-first-mesmerises-from-the-very-start\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T12:29:03Z\",\"webTitle\":\"Hallé/Elder review – Elgar's first mesmerises from the very start\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/halleelder-review-elgars-first-mesmerises-from-the-very-start\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/halleelder-review-elgars-first-mesmerises-from-the-very-start\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/die-meistersinger-von-nurnburg-damnation-faust\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T11:30:04Z\",\"webTitle\":\"Wagner comedy, damnation and dreams: this week’s best classical shows\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/die-meistersinger-von-nurnburg-damnation-faust\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/die-meistersinger-von-nurnburg-damnation-faust\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/cat-power-craig-david-superfood\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T11:00:03Z\",\"webTitle\":\"Cat Power and Craig David: this week’s best UK rock and pop gigs\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/cat-power-craig-david-superfood\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/cat-power-craig-david-superfood\",\"isHosted\":false},{\"id\":\"music/2017/mar/10/bowie-cracked-actor-bowpromo1-record-store-day\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-10T09:36:02Z\",\"webTitle\":\"Bowie vaults open as two unreleased albums hit shops for Record Store Day\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/10/bowie-cracked-actor-bowpromo1-record-store-day\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/10/bowie-cracked-actor-bowpromo1-record-store-day\",\"isHosted\":false},{\"id\":\"music/2017/mar/09/holly-macve-golden-eagle-review-delta-blues-bella-union\",\"type\":\"article\",\"sectionId\":\"music\",\"sectionName\":\"Music\",\"webPublicationDate\":\"2017-03-09T22:15:08Z\",\"webTitle\":\"Holly Macve: Golden Eagle review – hard-luck songs make like the delta blues\",\"webUrl\":\"https://www.theguardian.com/music/2017/mar/09/holly-macve-golden-eagle-review-delta-blues-bella-union\",\"apiUrl\":\"https://content.guardianapis.com/music/2017/mar/09/holly-macve-golden-eagle-review-delta-blues-bella-union\",\"isHosted\":false}]}}";

    public static ArrayList<Item>extractFromJSON(String jsonFormat){
        if(TextUtils.isEmpty(jsonFormat))
        {
            return null;
        }
        ArrayList<Item> items=new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(jsonFormat);
            JSONObject response=jsonObject.getJSONObject("response");
            JSONArray results=response.getJSONArray("results");
            for(int i=0;i<results.length();i++) {
                JSONObject eachItem = results.getJSONObject(i);
                String title = eachItem.getString("webTitle");
                String section = eachItem.getString("sectionName");
                String date = eachItem.getString("webPublicationDate");
                date=date.substring(0,10);
                date=date+" ";
                String webURL=eachItem.getString("id");
                items.add(new Item(title,section,date,webURL));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return items;
    }

    public static URL createURL(String urlString) {
        URL url = null;
        if(TextUtils.isEmpty(urlString))
            return null;
        try {
             url=new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public static String makeHttpConnection(URL url) {
        if(url==null)
            return null;
        HttpURLConnection httpURLConnection=null;
        InputStream inputStream;
        String jsonFormat=null;
        try {
             httpURLConnection= (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if (httpURLConnection.getResponseCode()==200) {
                inputStream = httpURLConnection.getInputStream();
                jsonFormat = readFromStream(inputStream);
            }else
            {
                Log.e("","error response code"+httpURLConnection.getResponseCode());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if(httpURLConnection!=null)
                httpURLConnection.disconnect();
        }
        if (TextUtils.isEmpty(jsonFormat)){
            Log.e("","empty JSON Format");
            return null;
        }
        return jsonFormat;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        if (inputStream == null){
            return null;
        }
        BufferedReader bufferedReader;
        StringBuilder stringBuilder=new StringBuilder();
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            String line;
            while((line=bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            bufferedReader.close();
        }
        return stringBuilder.toString();
    }

    public static List<String> extractBodyFromJSON(String jsonFormat) {
        if(TextUtils.isEmpty(jsonFormat)){
            return null;
        }
        String response=null;
        List<String> strings=new ArrayList<>();

        try {
            JSONObject jsonObject=new JSONObject(jsonFormat);
            JSONObject jsonObjectResponse=jsonObject.getJSONObject("response");
            JSONObject jsonObjectContent=jsonObjectResponse.getJSONObject("content");
            JSONObject jsonObjectBlocks=jsonObjectContent.getJSONObject("blocks");
            JSONArray jsonArrayBody=jsonObjectBlocks.getJSONArray("body");
            for(int i=0;i<jsonArrayBody.length();i++) {
                JSONObject jsonObjectEachItem = jsonArrayBody.getJSONObject(i);
                strings.add(jsonObjectEachItem.getString("bodyTextSummary"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
