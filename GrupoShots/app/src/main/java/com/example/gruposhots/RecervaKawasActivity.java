package com.example.gruposhots;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.r0adkll.slidr.Slidr;

import java.util.ArrayList;
import java.util.Calendar;


public class RecervaKawasActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner Spinner1;
    public Spinner Spinner2;
    public EditText Fecha;
    public Button btnFecha;
    public RadioButton rdoVIP;
    public RadioButton rdoMesa;
    public RadioGroup radioGroup;
    FirebaseDatabase nfirebaseDatabase;
    DatabaseReference nRef ;
    ArrayList VIP = new ArrayList();
    ArrayList MESA = new ArrayList();

    private int dia, mes, ano;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_kawas);
        Slidr.attach (this);

        Spinner1 = (Spinner) findViewById (R.id.spinnerVipKawas);
        Spinner2 = (Spinner) findViewById (R.id.spinnerMesaKawas);
        Fecha = (EditText) findViewById (R.id.txtFechaKawas);
        btnFecha= (Button)findViewById (R.id.btnFechaKawas);
        rdoMesa = (RadioButton) findViewById(R.id.rdoMesa);
        rdoVIP = (RadioButton) findViewById(R.id.rdoVIP);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);

        btnFecha.setOnClickListener (this);

        Spinner1.setEnabled(false);
        Spinner2.setEnabled(false);
        rdoVIP.setEnabled(false);
        rdoMesa.setEnabled(false);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                switch(checkedId){

                    case R.id.rdoMesa:
                            Spinner1.setEnabled(false);
                            Spinner2.setEnabled(true);
                            Spinner1.setSelection(0);
                        break;

                    case R.id.rdoVIP:
                            Spinner1.setEnabled(true);
                            Spinner2.setEnabled(false);
                            Spinner2.setSelection(0);
                        break;
                }
            }
        });

        llenarSpinerVip();
        llenarSpinerMesa();

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
        if(Fecha.getText().length() == 0){
            Toast.makeText(this, "Seleccione una fecha", Toast.LENGTH_LONG).show();
        }
        else if(Spinner1.getSelectedItem().toString().equals("- -") && Spinner2.getSelectedItem().toString().equals("- -")){
            Toast.makeText(this, "Seleccione un lugar para reservar", Toast.LENGTH_LONG).show();
        }
        else{
            Intent enviar = new Intent (this, ConfirmacionReservaKawasActivity.class);
            enviar.putExtra ("fecha", Fecha.getText ().toString ());
            enviar.putExtra ("vip", Spinner1.getSelectedItem ().toString ());
            enviar.putExtra ("mesa", Spinner2.getSelectedItem ().toString ());
            startActivity (enviar);
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        if(view==btnFecha){
            final Calendar c = Calendar.getInstance ();
            dia=c.get (Calendar.DAY_OF_MONTH);
            mes=c.get (Calendar.MONTH);
            ano=c.get (Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog (this, new DatePickerDialog.OnDateSetListener () {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Fecha.setText (i2+"/"+(i1+1)+"/"+i);
                    VIP.clear();
                    MESA.clear();
                    llenarSpinerVip();
                    llenarSpinerMesa();

                    rdoVIP.setEnabled(true);
                    rdoMesa.setEnabled(true);
                    FirebaseDatabase.getInstance().getReference().child("reservaciones")
                            .addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                        if(snapshot.child("establecimiento").getValue(String.class).equals("Kawas")){
                                            if(snapshot.child("fecha").getValue(String.class).equals(Fecha.getText().toString())){
                                                String lugar = snapshot.child("lugar").getValue(String.class);
                                                if(lugar.contains("VIP")){
                                                    String num = lugar.substring(3);
                                                    VIP.remove(num);
                                                }
                                                if(lugar.contains("MESA")){
                                                    String num = lugar.substring(4);
                                                    MESA.remove(num);
                                                }
                                            }
                                        }
                                    }
                                    llenarSpinerVip2();
                                    llenarSpinerMesa2();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                }
                            });

                }
            }
                    ,ano,mes,dia);

            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
            datePickerDialog.show ();

        }
    }

    public void llenarSpinerVip(){
        VIP.add("- -");

        for (int i = 1; i <= 3; i++){
            VIP.add(Integer.toString(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spinner1.setAdapter (adapter);
    }

    public void llenarSpinerVip2(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spinner1.setAdapter (adapter);
    }

    public void llenarSpinerMesa(){
        MESA.add("- -");

        for (int i = 1; i <= 20; i++){
            MESA.add(Integer.toString(i));
        }

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,MESA);
        Spinner2.setAdapter (adapter2);
    }

    public void llenarSpinerMesa2(){
        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,MESA);
        Spinner2.setAdapter (adapter2);
    }
}
