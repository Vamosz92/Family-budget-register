package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    Button kiadas_btn, modositas_btn, naplozas_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        kiadas_btn = findViewById(R.id.kiadas_btn);
        modositas_btn = findViewById(R.id.modositas_btn);
        naplozas_btn = findViewById(R.id.naplozas_btn);

        kiadas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, Activity3.class);
                startActivity(intent);
            }
        });

        modositas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity2.this, Activity6.class);
                startActivity(intent);
            }
        });

        naplozas_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity2.this, Activity5.class);
                startActivity(i);
            }
        });
    }
}