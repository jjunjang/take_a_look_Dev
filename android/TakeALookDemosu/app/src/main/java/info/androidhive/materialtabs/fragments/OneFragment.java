package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.adapter.ListViewAdapter;
import info.androidhive.materialtabs.webcontext.MyAsyncTask;
import info.androidhive.materialtabs.item.ListViewItem;


public class OneFragment extends ListFragment {
    String myJSON;

    private static final String TAG = "Getphp";

    private static final String TAG_RESULTS = "RatingsRes";
    private static final String TAG_tconst = "tconst";
    private static final String TAG_titleKor = "titleKor";
    private static final String TAG_averageRating = "averageRating";
    private static final String TAG_numVotes = "numVotes";
    private static final String TAG_imgUrl = "imgUrl";

    MyAsyncTask task;
    ListViewAdapter adapter;
    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        ListViewItem item = (ListViewItem) l.getItemAtPosition(position) ;

        Drawable movieImg = item.getMovieImg() ;
        String tconst = item.getTconst() ;
        String titleKor = item.getTitleKor() ;
        String averageRating = item.getAverageRating() ;
        String numVotes = item.getNumVotes() ;

        // TODO : use item data.
    }

    //웹에서 데이터를 가져오기 전에 먼저 네트워크 상태부터 확인
    public void conntectCheck() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // fetch data
            //Toast.makeText(this,"네트워크 연결중입니다.", Toast.LENGTH_SHORT).show();
            task = new MyAsyncTask(this);
            task.execute("");

        } else {
            // display error
            Toast.makeText(this,"네트워크 상태를 확인하십시오", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View v) {
        conntectCheck();
    }

    private void getDbData(String string) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
                protected String doInBackground(String... params) {
                    String uri = params[0];
                    BufferedReader bufferedReader = null;

                try {
                    URL url = new URL(uri);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 연결 객체 생성
                    StringBuilder sb = new StringBuilder();

                    if(conn != null){ // 연결되었으면
                        conn.setConnectTimeout(10000);
                        conn.setReadTimeout(10000);
                        conn.setUseCaches(false);
                        conn.setDefaultUseCaches(false);

                        int responseCode = conn.getResponseCode();
                        System.out.println("[ONE] GET Response Code : " + responseCode + " ==> 200 : oK" );
                        if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){ // 연결 코드가 리턴되면
                            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                            String json;
                            while((json = bufferedReader.readLine())!= null){
                                sb.append(json + "\n");
                            }
                        }
                        bufferedReader.close();
                    }
                    return sb.toString().trim();

                } catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }
            }
            protected void onPostExecute(String result){
                myJSON=result;
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(string);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Adapter 생성 및 Adapter 지정.
        adapter = new ListViewAdapter();

        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
//                String tconst = c.getString(TAG_tconst);
                String titleKor = c.getString(TAG_titleKor);
                String averageRating = c.getString(TAG_averageRating);
//                String numVotes = c.getString(TAG_numVotes);
                String imgUrl = c.getString(TAG_imgUrl);

                //임시 이미지
                Drawable movieImg = getResources().getDrawable(R.drawable.ic_launcher_background);

                System.out.println("[ONE] imgUrl : " + imgUrl);

                setListAdapter(adapter) ;

                // 아이템 추가
                adapter.addItem(movieImg, titleKor, "평점 : " + averageRating);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new ListViewAdapter();
        personList = new ArrayList<HashMap<String, String>>();
        setListAdapter(adapter);

        getDbData("http://54.180.103.40/Like.php");
    }
}
