package com.example.gruposhots;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gruposhots.Dashboard.PerfilActivity;

public class RecervaBoaActivity extends AppCompatActivity {

    public Spinner Spiner1;
    public Spinner Spiner2;
    public Spinner Spiner3;
    public EditText Fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_boa);

        Spiner1 = (Spinner) findViewById (R.id.spinnerSalon);
        Spiner2 = (Spinner) findViewById (R.id.spinnerVip);
        Spiner3 = (Spinner) findViewById (R.id.SpinnerMesa);
        Fecha = (EditText) findViewById (R.id.txtFecha);

        String [] salon = {"- -","1", "2", "3","4","5","6"};
        String [] VIP= {"- -","1", "2","3","4"};
        String [] Mesa= {"- -","20","21", "22","23","24","25","26","27","28","29","30","31","32","33","34","35"
                ,"36","37","38","39","40","41","42","43","44","45","46","47","48","49","50","51","52","53",
                "54","55","56","57","58","59","60","61","62","63" };

        ArrayAdapter <String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,salon);
        Spiner1.setAdapter (adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spiner2.setAdapter (adapter2);

        ArrayAdapter <String> adapter3 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,Mesa);
        Spiner3.setAdapter (adapter3);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_boa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambio = new Intent (RecervaBoaActivity.this,MapaBoaActivity.class);
                RecervaBoaActivity.this.startActivity(cambio);


            }
        });



    }




    public void enviarDatos(View view){
        Intent enviar = new Intent (this, ConfirmacionReservaBoaActivity.class);
        enviar.putExtra ("fecha", Fecha.getText ().toString ());
        enviar.putExtra ("salon", Spiner1.getSelectedItem ().toString ());
        enviar.putExtra ("vip", Spiner2.getSelectedItem ().toString ());
        enviar.putExtra ("mesa", Spiner3.getSelectedItem ().toString ());
        startActivity (enviar);




    }
}
