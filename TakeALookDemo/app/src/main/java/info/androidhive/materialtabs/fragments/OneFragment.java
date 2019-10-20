package info.androidhive.materialtabs.fragments;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import info.androidhive.materialtabs.adapter.ListViewAdapter;
import info.androidhive.materialtabs.db.DbOpenHelper;
import info.androidhive.materialtabs.item.ListViewItem;


public class OneFragment extends ListFragment {

    private DbOpenHelper mDbOpenHelper;
    private static String[] mylist = new String[20];
    private static int[] SetImageList = new int[30];
    private static String[] SetTitleList = new String[30];
    private static String String_tconst;
    private static String String_titleKor;
    private static int Integer_tconst;
    private static int count;
    private static int count1;
    ListViewAdapter adapter ;


    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDbOpenHelper = new DbOpenHelper(getContext());
        mDbOpenHelper.open();
        mDbOpenHelper.create();


 /*       for (int i=0; i<4; i++) {
            SetImageList[i] = SELECT_TCONST(); // 일단 4번돌림
            for (int j=0; j<i; j++) {

                if (SetImageList[i] == SetImageList[j]) {
                    i--; // J번째에서 중복이므로 다시 돌려서 넣음
                }
            }
        }*/

        for (int i=0; i<30; i++) {
            SetImageList[i] = SELECT_MYLIST_TCONST(i); // 일단 30번돌림
            SetTitleList[i] = SELECT_MYLIST_TITLE(i);


        }
//        Toast.makeText(getContext(), SetImageList[2] + SetTitleList[2], Toast.LENGTH_LONG).show();


    }

    private int SELECT_MYLIST_TCONST(int i) {
            mDbOpenHelper.open();
            Cursor cursor = mDbOpenHelper.mDB.rawQuery(mDbOpenHelper.SQL_LIST_USERTCONST,null);

            if(cursor.moveToPosition(i)) {
                    String_tconst = cursor.getString(0);
                    Integer_tconst = getResources().getIdentifier(String_tconst, "drawable", getActivity().getPackageName());
            }
            cursor.close();
            return Integer_tconst;
        }



        private String SELECT_MYLIST_TITLE(int i) {
            mDbOpenHelper.open();
            Cursor cursor = mDbOpenHelper.mDB.rawQuery(mDbOpenHelper.SQL_LIST_USERTITLEKOR,null);

            if(cursor.moveToPosition(i)) {
                String_titleKor = cursor.getString(0);
            }
            cursor.close();
            return String_titleKor;
    }


    @Override
    public void onListItemClick (ListView l, View v, int position, long id) {
        // get TextView's Text.
        ListViewItem item = (ListViewItem) l.getItemAtPosition(position) ;

        String titleStr = item.getTitle() ;
        String descStr = item.getDesc() ;
        Drawable iconDrawable = item.getIcon() ;


        // TODO : use item data.

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Adapter 생성 및 Adapter 지정.
        adapter = new ListViewAdapter() ;
        setListAdapter(adapter) ;

/*        //아이템 추가.
        mDbOpenHelper.open();
        Cursor cursor = mDbOpenHelper.mDB.rawQuery(mDbOpenHelper.SQL_LIST_USERTCONST,null);
        int ABC = 0;

        while(cursor.moveToNext()) {
            adapter.addItem(ContextCompat.getDrawable(getActivity(), SetImageList[ABC]),
                    SetTitleList[ABC], "평점: 7.6") ;
                    ABC = ABC + 1;
        }
        cursor.close();*/

 //너무 잘됨

        // for(int i=0; i<10; i++) { }
        mDbOpenHelper.open();
        Cursor cursor = mDbOpenHelper.mDB.rawQuery(mDbOpenHelper.SQL_LIST_USERTCONST,null);
        int j=0;
        while(cursor.moveToNext()) {

            adapter.addItem(ContextCompat.getDrawable(getActivity(), SetImageList[j]),
                    SetTitleList[j], "평점 : 5.5");
            j++;
        }


        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
