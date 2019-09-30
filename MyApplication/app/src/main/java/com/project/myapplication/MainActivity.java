package com.project.myapplication;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

//import info.androidhive.materialtabs.R;
//import info.androidhive.materialtabs.activity.MainActivity;


public class MainActivity extends Activity {

    String myJSON;

    private static final String TAG_RESULTS = "RatingsRst";

    private static final String TAG_tconst = "tconst";
    private static final String TAG_titleKor = "titleKor";
    private static final String TAG_averageRating = "averageRating";
    private static final String TAG_numVotes = "numVotes";

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> mArrayList;

    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listView);
        mArrayList = new ArrayList<HashMap<String, String>>();
        getData("http://54.180.103.40/getjson.php"); //수정 필요
    }


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            JSONArray jsonArray = jsonObj.getJSONArray(TAG_RESULTS);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c = jsonArray.getJSONObject(i);

                    String tconst = c.getString(TAG_tconst);
                    String titleKor = c.getString(TAG_titleKor);
                    String averageRating = c.getString(TAG_averageRating);
                    String numVotes = c.getString(TAG_numVotes);

                    HashMap<String, String> hashMap = new HashMap<String, String>();

                    hashMap.put(TAG_tconst, tconst);
                    hashMap.put(TAG_titleKor, titleKor);
                    hashMap.put(TAG_averageRating, averageRating);
                    hashMap.put(TAG_numVotes, numVotes);

                    mArrayList.add(hashMap);
            }

            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, mArrayList, R.layout.list_item,
                    new String[]{TAG_tconst, TAG_titleKor, TAG_averageRating, TAG_numVotes},
                    new int[]{R.id.tconst, R.id.titleKor, R.id.averageRating, R.id.numVotes}
            );

            list.setAdapter(adapter);

        } catch (JSONException e) {

            Log.d(TAG_RESULTS, "showResult : ", e);
        }

    }

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }

}
