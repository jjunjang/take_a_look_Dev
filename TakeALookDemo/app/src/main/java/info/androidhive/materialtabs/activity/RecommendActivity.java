package info.androidhive.materialtabs.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.util.Log;

import android.widget.ImageView;

import android.widget.Toast;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.db.DbOpenHelper;

public class RecommendActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DbOpenHelper mDbOpenHelper;
    private static String String_tconst;
    private static int Integer_tconst;
    private static int[] SetImageRecommend = new int[4];
    private static int count = 0;




    SwipeRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();

        final ImageView reimg1 = (ImageView)findViewById(R.id.reimg1);
        final ImageView reimg2 = (ImageView)findViewById(R.id.reimg2);
        final ImageView reimg3 = (ImageView)findViewById(R.id.reimg3);
        final ImageView reimg4 = (ImageView)findViewById(R.id.reimg4);

        // 중복제거
        for (int i=0; i<4; i++) {
            SetImageRecommend[i] = SELECT_TCONST(); // 일단 4번돌림
            for (int j=0; j<i; j++) {
                if (SetImageRecommend[i] == SetImageRecommend[j]) {
                    SetImageRecommend[j] = SELECT_TCONST(); // J번째에서 중복이므로 다시 돌려서 넣음
                    i--;
                }
            }
        }

        // RECOMMEND 이미지 4개 출력
        reimg1.setImageResource( SetImageRecommend[0] );
        reimg2.setImageResource( SetImageRecommend[1] );
        reimg3.setImageResource( SetImageRecommend[2] );
        reimg4.setImageResource( SetImageRecommend[3] );

        mDbOpenHelper.close();

        layout = (SwipeRefreshLayout) findViewById(R.id.layout);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // 중복제거
                for (int i=0; i<4; i++) {
                    SetImageRecommend[i] = SELECT_TCONST(); // 일단 4번돌림
                    for (int j=0; j<i; j++) {
                        if (SetImageRecommend[i] == SetImageRecommend[j]) {
                            SetImageRecommend[j] = SELECT_TCONST(); // J번째에서 중복이므로 다시 돌려서 넣음
                            i--;
                        }
                    }
                }
                //새로고침 작업 실행...
                reimg1.setImageResource( SetImageRecommend[0] );
                reimg2.setImageResource( SetImageRecommend[1] );
                reimg3.setImageResource( SetImageRecommend[2] );
                reimg4.setImageResource( SetImageRecommend[3] );

                layout.setRefreshing(false);

            }
        });


        reimg1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ClickImg1();
            }
        });
        reimg2.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ClickImg2();
            }
        });
        reimg3.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ClickImg3();
            }
        });
        reimg4.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                ClickImg4();
            }
        });
    }

    private int SELECT_TCONST() {
        mDbOpenHelper.READ();
        Cursor cursor = mDbOpenHelper.mDB.rawQuery(mDbOpenHelper.SQL_RECOMMEND_TCONST,null);

        if(cursor.moveToFirst()) {
            String_tconst = cursor.getString(0);
            Integer_tconst = getResources().getIdentifier(String_tconst, "drawable", getPackageName());
            Toast.makeText(this, String_tconst, Toast.LENGTH_LONG).show();
        }
        cursor.close();
        return Integer_tconst;
    }


        void ClickImg1() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Like", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Hate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNeutralButton("Interest", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "List에 추가 되었습니다", Toast.LENGTH_LONG).show();
                    }
                });


            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.fragment_one, null);

            dialog.setView(dialogLayout);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.show();

        }

        void ClickImg2() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Like", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Hate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNeutralButton("Interest", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "List에 추가 되었습니다", Toast.LENGTH_LONG).show();
                }
            });

            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.fragment_two, null);

            dialog.setView(dialogLayout);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.show();

        }

        void ClickImg3() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Like", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Hate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNeutralButton("Interest", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "List에 추가 되었습니다", Toast.LENGTH_LONG).show();
                }
            });

            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.fragment_three, null);

            dialog.setView(dialogLayout);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.show();

        }

        void ClickImg4() {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setPositiveButton("Like", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNegativeButton("Hate", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(),"List에 추가 되었습니다",Toast.LENGTH_LONG).show();
                }
            }).setNeutralButton("Interest", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "List에 추가 되었습니다", Toast.LENGTH_LONG).show();
                }
            });

            final AlertDialog dialog = builder.create();
            LayoutInflater inflater = getLayoutInflater();
            View dialogLayout = inflater.inflate(R.layout.fragment_four, null);

            dialog.setView(dialogLayout);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.show();

        }

}