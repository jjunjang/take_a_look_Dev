package info.androidhive.materialtabs.getphp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import info.androidhive.materialtabs.R;

public class ListItem extends Activity {
    private ArrayList ListViewItem = null;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        ListViewItem = new ArrayList();

//        Profile p1 = new Profile(R.drawable.boa, "보아", "1986년 11월 5일");
//        Profile p2 = new Profile(R.drawable.kaj, "김아중", "1982년 10월 16일");
//        Profile p3 = new Profile(R.drawable.nrs, "나르샤", "1981년 12월 28일");
//        Profile p4 = new Profile(R.drawable.lsy, "이수영", "1979년 4월 12일");
//        Profile p5 = new Profile(R.drawable.jyj, "장윤정", "1980년 2월 16");
//        Profile p6 = new Profile(R.drawable.nhh, "노현희", "1972년 8월 23일");
//
//        _profiles.add(p1);
//        _profiles.add(p2);
//        _profiles.add(p3);
//        _profiles.add(p4);
//        _profiles.add(p5);
//        _profiles.add(p6);
//
//        ProfileListAdapter adapter =
//                new ProfileListAdapter(this, R.layout.profileview, _profiles);
//
//        ListView listView = (ListView)findViewById(R.id.list);
//        listView.setAdapter(adapter);
    }
}