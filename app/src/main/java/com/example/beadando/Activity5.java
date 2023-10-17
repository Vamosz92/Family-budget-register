package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.ScrollingMovementMethod;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class Activity5 extends AppCompatActivity {

    TextView kategoriaTV, datumTV, naplozottTv, osszesen;
    Button listazBtn;
    MyDataBase db;
    Cursor c = null;
    int osszesKiadas = 0;
    String kat = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        kategoriaTV = findViewById(R.id.kategoriaTV);
        datumTV = findViewById(R.id.datumTV);
        naplozottTv = findViewById(R.id.naplozottTv);
        osszesen = findViewById(R.id.osszesen);
        db = new MyDataBase(this);
        listazBtn = findViewById(R.id.listazBtn);

        SpannableString content = new SpannableString("Kategória");
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        kategoriaTV.setText(content);
        SpannableString content2 = new SpannableString("Dátum");
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        datumTV.setText(content2);

        listazBtn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View view) {
                c = db.listazas();
                String adatok = "";

                if (c!=null || c.moveToFirst() || c.getCount()!=0){
                    while (!c.isAfterLast()) {
                        adatok += String.format("|%-11s|%-9s|%7s|%-10s%n", c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_MEGNEVEZES)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_DATUM)).trim());
                        osszesKiadas += Integer.parseInt(c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)));
                        c.moveToNext();
                    }
                    naplozottTv.setTypeface(Typeface.MONOSPACE);
                    naplozottTv.setText(adatok);
                    naplozottTv.setMovementMethod(new ScrollingMovementMethod());
                }
                else {
                    Toast.makeText(Activity5.this, "Nem található listázandó elem!", Toast.LENGTH_SHORT).show();
                }
                osszesen.setText(osszesKiadas + " Ft");
                osszesKiadas = 0;
            }
        });

        kategoriaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kategoriaValasztas();
            }
        });

        datumTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datumRendezes();
            }
        });
    }

    public void datumRendezes(){
        PopupMenu popupMenu = new PopupMenu(this, datumTV);
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Növekvő sorrend");
        popupMenu.getMenu().add(Menu.NONE, 2, 2, "Csökkenő sorrend");

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("Range")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                String adatok = "";

                switch(id){
                    case 1: c = db.datumSzerintRendezNovekvo();break;
                    case 2: c = db.datumSzerintRendezCsokkeno();break;
                }

                if(kat.equals("")){
                    while (!c.isAfterLast()) {
                        adatok += String.format("|%-11s|%-9s|%7s|%-10s|%n", c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_MEGNEVEZES)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_DATUM)).trim());
                        osszesKiadas += Integer.parseInt(c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)));
                        c.moveToNext();
                    }
                }

                else{
                    while (!c.isAfterLast()) {
                        if(c.getString((c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA))).equals(kat)) {
                            adatok += String.format("|%-11s|%-9s|%7s|%-10s|%n", c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA)).trim(),
                                    c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_MEGNEVEZES)).trim(),
                                    c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)).trim(),
                                    c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_DATUM)).trim());
                            osszesKiadas += Integer.parseInt(c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)));
                        }
                        c.moveToNext();
                    }
                }

                naplozottTv.setText(adatok);
                osszesen.setText(osszesKiadas + " Ft");
                osszesKiadas = 0;
                return true;
            }
        });
    }

    public void kategoriaValasztas() {
        PopupMenu popupMenu = new PopupMenu(this, kategoriaTV);
        popupMenu.getMenu().add(Menu.NONE, 1, 1, "Ajándékozás");
        popupMenu.getMenu().add(Menu.NONE, 2, 2, "Egyéb");
        popupMenu.getMenu().add(Menu.NONE, 3, 3, "Élelmiszer");
        popupMenu.getMenu().add(Menu.NONE, 4, 4, "Gépjármű");
        popupMenu.getMenu().add(Menu.NONE, 5, 5, "Lakhatás");
        popupMenu.getMenu().add(Menu.NONE, 6, 6, "Oktatás");
        popupMenu.getMenu().add(Menu.NONE, 7, 7, "Ruházat");
        popupMenu.getMenu().add(Menu.NONE, 8, 8, "Szórakozás");
        popupMenu.getMenu().add(Menu.NONE, 9, 9, "Utazás");
        popupMenu.getMenu().add(Menu.NONE, 10, 10, "Összes");

        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @SuppressLint("Range")
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int id = menuItem.getItemId();
                String adatok = "";

                switch(id){
                    case 1: c = db.listaSzures("Ajándékozás");
                        kat="Ajándékozés";break;
                    case 2: c = db.listaSzures("Egyéb");
                        kat="Egyéb";break;
                    case 3: c = db.listaSzures("Élelmiszer");
                        kat="Élelmiszer";break;
                    case 4: c = db.listaSzures("Gépjármű");
                        kat="Gépjármű";break;
                    case 5: c = db.listaSzures("Lakhatás");
                        kat="Lakhatás";break;
                    case 6: c = db.listaSzures("Oktatás");
                        kat="Oktatás";break;
                    case 7: c = db.listaSzures("Ruházat");
                        kat="Ruházat";break;
                    case 8: c = db.listaSzures("Szórakozás");
                        kat="Szórakozás";break;
                    case 9: c = db.listaSzures("Utazás");
                        kat="Utazás";break;
                    case 10: c = db.listazas();
                        kat="";break;
                }

                if(c==null){
                    Toast.makeText(Activity5.this, "Nincs lekérdezhető adat!", Toast.LENGTH_SHORT).show();
                }
                else {
                    while (!c.isAfterLast()) {
                        adatok += String.format("|%-11s|%-9s|%7s|%-10s|%n", c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_KATEGORIA)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_MEGNEVEZES)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)).trim(),
                                c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_DATUM)).trim());
                        osszesKiadas += Integer.parseInt(c.getString(c.getColumnIndex(DataBaseHelper.COLUMN_NAME_OSSZEG)));
                        c.moveToNext();
                    }

                    naplozottTv.setText(adatok);
                    osszesen.setText(osszesKiadas + " Ft");
                    osszesKiadas = 0;
                }

                return true;
            }
        });
    }
}