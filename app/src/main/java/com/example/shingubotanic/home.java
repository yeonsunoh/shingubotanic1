package com.example.shingubotanic;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

public class home extends MainActivity {
    private String TAG = "VideoActivity";

    ImageButton b1,home;
    View.OnClickListener cl;
    Intent i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        home= (ImageButton) findViewById(R.id.home);

        b1 = (ImageButton) findViewById(R.id.button1);

        cl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch ( v.getId() ) {
                    case R.id.button1 :
                        i = new Intent(getApplicationContext(), smart.class);
                        startActivity(i);
                        break;
                    case R.id.home :
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        break;
                }
            }
        };
        b1.setOnClickListener(cl);
    }
}