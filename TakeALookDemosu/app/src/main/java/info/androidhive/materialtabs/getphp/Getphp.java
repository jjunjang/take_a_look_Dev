package info.androidhive.materialtabs.getphp;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
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
//import info.androidhive.materialtabs.adapter.ListViewAdapter;
import info.androidhive.materialtabs.item.ListViewItem;

public class Getphp extends Activity {

    String myJSON;

    private static final String TAG = "Getphp";

    private static final String TAG_RESULTS = "RatingsRes";
    private static final String TAG_tconst = "tconst";
    private static final String TAG_titleKor = "titleKor";
    private static final String TAG_averageRating = "averageRating";
    private static final String TAG_numVotes = "numVotes";

    private ListView mlistView = null;
    private ListViewAdaptertest mAdapter = null;

    JSONArray peoples = null;

    ArrayList<HashMap<String, String>> personList;

    ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        imageView1 = (ImageView) findViewById(R.id.imageView1);
        mlistView = (ListView) findViewById(R.id.listView);
        mAdapter = new ListViewAdaptertest();

        personList = new ArrayList<HashMap<String, String>>();
        getDbData("http://54.180.103.40/getjson.php");

        mlistView.setAdapter(mAdapter);
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
                        System.out.println("GET Response Code : " + responseCode);
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
                showList();
            }
        }

        GetDataJSON g = new GetDataJSON();
        g.execute(string);
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
//                Log.d(TAG, peoples.toString());
                JSONObject c = peoples.getJSONObject(i);
                String tconst = c.getString(TAG_tconst);
                String titleKor = c.getString(TAG_titleKor);
                String averageRating = c.getString(TAG_averageRating);
                String numVotes = c.getString(TAG_numVotes);
                Drawable movieImg = getResources().getDrawable(R.drawable.ic_launcher_background);
                Log.d(TAG, tconst);

                mAdapter.addItem(movieImg, tconst, titleKor, averageRating, numVotes);
//                adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.tt0804540),
//                        "tt0804540", "택시4", "평점: 5.6", "123432") ;

//                Toast.makeText(Getphp.this, tconst, Toast.LENGTH_LONG).show();
            }

//            Toast.makeText(Getphp.this, "asd", Toast.LENGTH_LONG).show();

            ListAdapter mAdapter = new SimpleAdapter(
                    Getphp.this, personList, R.layout.list_item,
                    new String[]{TAG_tconst, TAG_titleKor, TAG_averageRating, TAG_numVotes},
                    new int[]{R.id.tconst, R.id.titleKor, R.id.averageRating, R.id.numVotes}
            );

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public class ListViewAdaptertest extends BaseAdapter {
        // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
        private ArrayList<ListViewItem> Arraylist = new ArrayList<ListViewItem>() ;

        // ListViewAdapter의 생성자
        public ListViewAdaptertest() {
            super();
            this.mContext = mContext;
        }

        // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
        @Override
        public int getCount() {
            return Arraylist.size() ;
        }

        // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
        @Override
        public Object getItem(int position) {
            return Arraylist.get(position) ;
        }

        // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
        @Override
        public long getItemId(int position) {
            return position ;
        }

        // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            // "listview_item" Layout을 inflate하여 convertView 참조 획득.
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.listview_item, parent, false);
            }

            // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
            ImageView iconImageView = (ImageView) convertView.findViewById(R.id.imageView1) ;
            TextView tconstView = (TextView) convertView.findViewById(R.id.tconst) ;
            TextView titleKorView = (TextView) convertView.findViewById(R.id.titleKor) ;
            TextView averageRatingView = (TextView) convertView.findViewById(R.id.averageRating) ;
            TextView numVotesView = (TextView) convertView.findViewById(R.id.numVotes) ;

            // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
            ListViewItem listViewItem = Arraylist.get(position);

            // 아이템 내 각 위젯에 데이터 반영0
            iconImageView.setImageDrawable(listViewItem.getMovieImg());
            tconstView.setText(listViewItem.getTconst());
            titleKorView.setText(listViewItem.getTitleKor());
            averageRatingView.setText(listViewItem.getAverageRating());
            numVotesView.setText(listViewItem.getNumVotes());

            return convertView;
        }

        // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
        public void addItem(Drawable movieImg, String tconst, String titleKor, String averageRating, String numVotes) {
            ListViewItem item = new ListViewItem();

            item.setMovieImg(movieImg);
            item.setTconst(tconst);
            item.setTitleKor(titleKor);
            item.setAverageRating(averageRating);
            item.setNumVotes(numVotes);

            Arraylist.add(item);

//            Log.d(TAG, Arraylist.toString());
//            Toast.makeText(Getphp.this, "ASD", Toast.LENGTH_SHORT).show();
            Toast.makeText(Getphp.this, tconst.toString(), Toast.LENGTH_SHORT).show();
        }

    }

}