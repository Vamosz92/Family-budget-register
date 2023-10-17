package com.example.beadando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Activity4 extends AppCompatActivity {

    Button rogzites;
    Button fomenuBtn;
    EditText text_kiadas, text_osszeg;
    static EditText text_datum;
    MyDataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        rogzites = findViewById(R.id.btn_rogzites);
        text_kiadas = findViewById(R.id.text_kiadas);
        text_osszeg = findViewById(R.id.text_osszeg);
        text_datum = findViewById(R.id.text_datum);
        fomenuBtn = findViewById(R.id.fomenuBtn);
        db = new MyDataBase(this);

        text_datum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

        rogzites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(text_kiadas.getText().toString().equals("") || text_osszeg.getText().toString().equals("") ||
                        text_datum.getText().toString().equals("")){
                    Toast.makeText(Activity4.this, "Kérem minden adatot töltsön ki!", Toast.LENGTH_SHORT).show();
                }
                else {

                    Intent i = getIntent();
                    int kategoria_int = i.getIntExtra("kat", 1);
                    String kategoria = "";

                    switch (kategoria_int) {
                        case 2131230824:
                            kategoria = "Lakhatás";
                            break;
                        case 2131230821:
                            kategoria = "Élelmiszer";
                            break;
                        case 2131230822:
                            kategoria = "Gépjármű";
                            break;
                        case 2131230827:
                            kategoria = "Ruházat";
                            break;
                        case 2131230828:
                            kategoria = "Szórakozás";
                            break;
                        case 2131230825:
                            kategoria = "Oktatás";
                            break;
                        case 2131230829:
                            kategoria = "Utazás";
                            break;
                        case 2131230819:
                            kategoria = "Ajándékozás";
                            break;
                        case 2131230820:
                            kategoria = "Egyéb";
                            break;
                    }

                    db.hozzaad(kategoria, text_kiadas.getText().toString(), Integer.parseInt(text_osszeg.getText().toString()), text_datum.getText().toString());
                    Toast.makeText(Activity4.this, "Pénzmozgás sikeresen rögzítve!", Toast.LENGTH_SHORT).show();
                    text_kiadas.setText("");
                    text_osszeg.setText("");
                    text_datum.setText("");
                    Intent intent = new Intent(Activity4.this, Activity2.class);
                    startActivity(intent);
                }
            }
        });

        fomenuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity4.this, Activity2.class);
                startActivity(i);
            }
        });
    }
}