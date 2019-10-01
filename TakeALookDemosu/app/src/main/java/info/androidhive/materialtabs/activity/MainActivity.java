package info.androidhive.materialtabs.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import info.androidhive.materialtabs.R;
import info.androidhive.materialtabs.getphp.Getphp;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btnphp, btnSimpleTabs, btnRecommend, btnRank, btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnphp = (Button) findViewById(R.id.btnphp);
        btnSimpleTabs = (Button) findViewById(R.id.btnSimpleTabs);
        btnRecommend = (Button) findViewById(R.id.btnRecommend);
        btnRank = (Button) findViewById(R.id.btnRank);
        btnOption = (Button) findViewById(R.id.btnOption);

        btnphp.setOnClickListener(this);
        btnSimpleTabs.setOnClickListener(this);
        btnRecommend.setOnClickListener(this);
        btnRank.setOnClickListener(this);
        btnOption.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        try {
            switch (view.getId()) {
                case R.id.btnphp:
                    startActivity(new Intent(MainActivity.this, Getphp.class));
                    break;

                case R.id.btnSimpleTabs:
                    startActivity(new Intent(MainActivity.this, SimpleTabsActivity.class));
                    break;

                case R.id.btnRecommend:
                    startActivity(new Intent(MainActivity.this, RecommendActivity.class));
                    break;

                case R.id.btnRank:
                    startActivity(new Intent(MainActivity.this, RankActivity.class));
                    break;

                case R.id.btnOption:
                    startActivity(new Intent(MainActivity.this, OptionActivity.class));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
