package com.example.gruposhots;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;


public class RecervaKawasActivity extends AppCompatActivity {
    public Spinner Spinner1;
    public Spinner Spinner2;
    public EditText Fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_kawas);

        Spinner1 = (Spinner) findViewById (R.id.spinnerVipKawas);
        Spinner2 = (Spinner) findViewById (R.id.spinnerMesaKawas);
        Fecha = (EditText) findViewById (R.id.txtFechaKawas);

        String [] VIP= {"- -","0","1", "2","3","4"};
        String [] Mesa= {"- -","0","1", "2","3","4", "5"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spinner1.setAdapter (adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,Mesa);
        Spinner2.setAdapter (adapter2);


    }
    public void enviarDatos2(View view){
        Intent enviar = new Intent (this, ConfirmacionReservaKawasActivity.class);
        enviar.putExtra ("fecha", Fecha.getText ().toString ());
        enviar.putExtra ("vip", Spinner1.getSelectedItem ().toString ());
        enviar.putExtra ("mesa", Spinner2.getSelectedItem ().toString ());
        startActivity (enviar);
    }
}
