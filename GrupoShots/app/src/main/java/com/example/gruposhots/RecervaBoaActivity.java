package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RecervaBoaActivity extends AppCompatActivity {

    public Spinner Spiner1;
    public Spinner Spiner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_boa);

        Spiner1 = (Spinner) findViewById (R.id.spinnerLugar);
        Spiner2 = (Spinner) findViewById (R.id.SpinnerNumero);

        String [] Lugares = {"Mesa", "Sala", "VIP"};
        String [] Numeros= {"1", "2","3"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,Lugares);
        Spiner1.setAdapter (adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,Numeros);
        Spiner2.setAdapter (adapter2);
    }
}
