package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import info.androidhive.materialtabs.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btnSimpleTabs, btnRank, btnOption, btnRecommend;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnSimpleTabs = (Button) findViewById(R.id.btnSimpleTabs);
        btnRecommend = (Button) findViewById(R.id.btnRecommend);
        btnRank = (Button) findViewById(R.id.btnRank);
        btnOption = (Button) findViewById(R.id.btnOption);

        btnSimpleTabs.setOnClickListener(this);
        btnRecommend.setOnClickListener(this);
        btnRank.setOnClickListener(this);
        btnOption.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSimpleTabs:
                btnSimpleTabs.setBackgroundResource(R.drawable.btn_states);
                startActivity(new Intent(MainActivity.this, SimpleTabsActivity.class));
                break;

            case R.id.btnRecommend:
                btnRecommend.setBackgroundResource(R.drawable.btn_states);
                startActivity(new Intent(MainActivity.this, RecommendActivity.class));
                break;

            case R.id.btnRank:
                btnRank.setBackgroundResource(R.drawable.btn_states);
                startActivity(new Intent(MainActivity.this, RankActivity.class));
                break;

            case R.id.btnOption:
                btnOption.setBackgroundResource(R.drawable.btn_states);
                startActivity(new Intent(MainActivity.this, OptionActivity.class));
                break;
        }
    }
}