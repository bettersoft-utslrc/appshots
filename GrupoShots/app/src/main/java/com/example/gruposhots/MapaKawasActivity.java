package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.r0adkll.slidr.Slidr;

public class MapaKawasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_mapa_kawas);

        Slidr.attach (this);
    }
}
