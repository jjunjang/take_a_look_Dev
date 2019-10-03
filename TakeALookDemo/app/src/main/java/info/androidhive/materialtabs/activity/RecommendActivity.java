package info.androidhive.materialtabs.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import info.androidhive.materialtabs.R;

public class RecommendActivity extends AppCompatActivity {
    private Toolbar toolbar;
    SwipeRefreshLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        final ImageView reimg1 = (ImageView)findViewById(R.id.reimg1);
        final ImageView reimg2 = (ImageView)findViewById(R.id.reimg2);
        final ImageView reimg3 = (ImageView)findViewById(R.id.reimg3);
        final ImageView reimg4 = (ImageView)findViewById(R.id.reimg4);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        layout = (SwipeRefreshLayout) findViewById(R.id.layout);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                //새로고침 작업 실행...
                reimg1.setImageResource(R.drawable.tt0107290);
                reimg2.setImageResource(R.drawable.tt2660888);
                reimg3.setImageResource(R.drawable.tt0404254);
                reimg4.setImageResource(R.drawable.tt6105098);

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