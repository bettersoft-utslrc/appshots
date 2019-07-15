package com.example.gruposhots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class seleccionLocalActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BtnBoa,BtnKawas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccion_local);

        BtnBoa = (Button) findViewById(R.id.btnBoa);
        BtnKawas = (Button) findViewById(R.id.btnLaskawas) ;

        BtnBoa.setOnClickListener((View.OnClickListener) this);
        BtnKawas.setOnClickListener((View.OnClickListener) this);




        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int whith = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(whith*.8),(int)(height*.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = -1;
        params.y = -40;

        getWindow().setAttributes(params);

    }
    public void  cambiarBoa()
    {
        Intent cambio = new Intent (seleccionLocalActivity.this,RecervaBoaActivity.class);
        seleccionLocalActivity.this.startActivity(cambio);
    }

    public void cambiarKawas()
    {
        Intent cambio = new Intent (seleccionLocalActivity.this,RecervaKawasActivity.class);
        seleccionLocalActivity.this.startActivity(cambio);
    }



    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnLaskawas:
                cambiarKawas();
                break;

            case R.id.btnBoa:
                cambiarBoa();
                break;
        }

    }



}
