package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ConfirmacionReservaBoaActivity extends AppCompatActivity {
    private TextView Fecha;
    private TextView Salon;
    private TextView Vip;
    private TextView Mesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_confirmacion_reserva_boa);

        Fecha = (TextView) findViewById (R.id.txtFechaResiver);
        String fecha = getIntent ().getStringExtra ("fecha");
        Fecha.setText ("Fecha: " + fecha);

        Salon = (TextView) findViewById (R.id.txtSalonResiver);
        String salon = getIntent ().getStringExtra ("salon");
        Salon.setText ("Numero de salon : " + salon);

        Vip = (TextView) findViewById (R.id.txtVipResiver);
        String vip = getIntent ().getStringExtra ("vip");
        Vip.setText ("Numero de Vip :" + vip);

        Mesa = (TextView) findViewById (R.id.txtMesaResiver);
        String mesa = getIntent ().getStringExtra ("mesa");
        Mesa.setText ("Numero de mesa : " +  mesa);



    }
}
