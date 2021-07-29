package com.example.a2uitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.a2uitesting.SimpleNavDrawer.EasyNavActivity;
import com.example.a2uitesting.navDrawer.NavActivity;

public class MainActivity extends AppCompatActivity {

    private Button navDrawerBtn,simpleNavBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navDrawerBtn=findViewById(R.id.navBtnId);
        navDrawerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NavActivity.class);
                startActivity(intent);
            }
        });
        simpleNavBtn=findViewById(R.id.simpleNavBtn);
        simpleNavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, EasyNavActivity.class);
                startActivity(intent);
            }
        });

    }
}