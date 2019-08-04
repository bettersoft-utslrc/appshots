package com.example.gruposhots;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;


public class RecervaKawasActivity extends AppCompatActivity {
    public Spinner Spinner1;
    public Spinner Spinner2;
    public EditText Fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_kawas);
        Slidr.attach (this);

        Spinner1 = (Spinner) findViewById (R.id.spinnerVipKawas);
        Spinner2 = (Spinner) findViewById (R.id.spinnerMesaKawas);
        Fecha = (EditText) findViewById (R.id.txtFechaKawas);





        String [] VIP= {"- -","1", "2","3"};
        String [] Mesa= {"- -","0","1", "2","3","4", "5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spinner1.setAdapter (adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,Mesa);
        Spinner2.setAdapter (adapter2);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_kawas);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambio = new Intent (RecervaKawasActivity.this,MapaKawasActivity.class);
                RecervaKawasActivity.this.startActivity(cambio);


            }
        });



    }

    public void enviarDatos2(View view){
        Intent enviar = new Intent (this, ConfirmacionReservaKawasActivity.class);
        enviar.putExtra ("fecha", Fecha.getText ().toString ());
        enviar.putExtra ("vip", Spinner1.getSelectedItem ().toString ());
        enviar.putExtra ("mesa", Spinner2.getSelectedItem ().toString ());
        startActivity (enviar);
    }

}
