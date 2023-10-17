package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Activity3 extends AppCompatActivity {

    Button btn_katValaszto;
    RadioGroup radioGr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        btn_katValaszto = findViewById(R.id.btn_katValaszto);
        radioGr = findViewById(R.id.radioGr);

        btn_katValaszto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioGr.getCheckedRadioButtonId()==-1){
                    Toast.makeText(Activity3.this, "Kérem válasszon kategóriát!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(Activity3.this, Activity4.class);
                    i.putExtra("kat", radioGr.getCheckedRadioButtonId());
                    startActivity(i);
                }
            }
        });
    }
}