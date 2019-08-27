package com.example.djqjd.takealookdemo.project;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btnRcmd, btnList, btnRank, btnOption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRcmd = (Button)findViewById(R.id.btnRcmd);
        btnRcmd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class); // ListActivity 임시 설정
                startActivity(intent);
            }
        });

        Button btnList = (Button)findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view){
                Intent intent = new Intent (
                        getApplicationContext(),ListActivity.class);
                startActivity(intent);
            }
        });

        Button btnRank = (Button)findViewById(R.id.btnRank);
        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view){
                Intent intent = new Intent (
                getApplicationContext(),ListActivity.class); // ListActivity 임시 설정
                startActivity(intent);
            }
        });

        Button btnOption = (Button)findViewById(R.id.btnOption);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public  void onClick(View view){
                Intent intent = new Intent (
                        getApplicationContext(),ListActivity.class); // ListActivity 임시 설정
                startActivity(intent);
            }
        });


    }
}
