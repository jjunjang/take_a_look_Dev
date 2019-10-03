package info.androidhive.materialtabs.webcontext;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.adapter.ListViewAdapter;

public class WebConnectActivity extends Activity implements OnClickListener{

    Button btn;
    MyAsyncTask task;
    ListView listView;
    ListViewAdapter adapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webconnect);

        listView = (ListView)findViewById(R.id.listView);
        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

        btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(this);

    }
    //웹에서 데이터를 가져오기 전에 먼저 네트워크 상태부터 확인
    public void conntectCheck(){
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
}
