package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class RecervaBoaActivity extends AppCompatActivity {

    public Spinner Spiner1;
    public Spinner Spiner2;
    public Spinner Spiner3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva_boa);

        Spiner1 = (Spinner) findViewById (R.id.spinnerSalon);
        Spiner2 = (Spinner) findViewById (R.id.spinnerVip);
        Spiner3 = (Spinner) findViewById (R.id.SpinnerMesa);

        String [] salon = {"0","1", "2", "3"};
        String [] VIP= {"0","1", "2","3","4"};
        String [] Mesa= {"0","1", "2","3","4", "5"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,salon);
        Spiner1.setAdapter (adapter);

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,VIP);
        Spiner2.setAdapter (adapter2);

        ArrayAdapter <String> adapter3 = new ArrayAdapter<String> (this,R.layout.spinner_item_reservas,Mesa);
        Spiner3.setAdapter (adapter3);
    }
}
