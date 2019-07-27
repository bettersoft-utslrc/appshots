package com.example.gruposhots;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ConfirmacionReservaBoaActivity extends AppCompatActivity {
    private TextView Fecha;
    private TextView Salon;
    private TextView Vip;
    private TextView Mesa;
    private TextView Nombre;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_confirmacion_reserva_boa);

        database = FirebaseDatabase.getInstance ();
        user = FirebaseAuth.getInstance ().getCurrentUser ();
        reference = database.getReference ("Usuarios").child (user.getUid ());

        Nombre = (TextView) findViewById (R.id.txtnombreResiver);

        reference.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists ()){
                        String  nombre = (String) dataSnapshot.child ("Nombre").getValue ();
                        Nombre.setText ("Nombre: " + nombre);
                    }else {
                        Toast.makeText (ConfirmacionReservaBoaActivity.this, "valiste verga", Toast.LENGTH_LONG).show ();
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });



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
