package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity6 extends AppCompatActivity {

    MyDataBase db;
    Cursor c;
    TextView adatokTV;
    Button torolBtn;
    EditText torlendoSorszamEt;
    int torlendoId = 0;
    String adatok = "";

    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        db = new MyDataBase(this);
        adatokTV = findViewById(R.id.adatokTV);
        torolBtn = findViewById(R.id.torolBtn);
        torlendoSorszamEt = findViewById(R.id.torlendoSorszamEt);

        torlendokListazasa();
        adatokTV.setTypeface(Typeface.MONOSPACE);
        adatokTV.setText(adatok);
        adatokTV.setMovementMethod(new ScrollingMovementMethod());

        torolBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                torlendoId = Integer.parseInt(torlendoSorszamEt.getText().toString());
                db.torolSort(torlendoId);
                adatok="";
                torlendokListazasa();
                adatokTV.setText(adatok);
                torlendoSorszamEt.setText("");
            }
        });
    }

    @SuppressLint("Range")
    public void torlendokListazasa(){
        c = db.listazas();
        while (!c.isAfterLast()) {
            adatok += String.format("|%-3s|%-11s|%-6s|%7s|%-10s%n", c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_ID)).trim(),
                            c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA)).trim(),
                            c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_MEGNEVEZES)).trim(),
                            c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)).trim(),
                            c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_DATUM)).trim());
            c.moveToNext();
        }
    }
}