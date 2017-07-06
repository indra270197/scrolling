package com.example.brown.proto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by Brown on 6/13/2017.
 */

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);


        ImageButton button2=(ImageButton)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                Intent i = null;
                i =new Intent(getApplicationContext(),MapsActivity.class);

                startActivity(i);
            }

        });

        ImageButton button1=(ImageButton)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub
                Intent i = null;
                i =new Intent(getApplicationContext(),HitechActivity.class);

                startActivity(i);
            }

        });


    }
}
