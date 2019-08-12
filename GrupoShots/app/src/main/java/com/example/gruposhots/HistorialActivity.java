package com.example.gruposhots;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HistorialActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    RecyclerView nRecyclerView;
    FirebaseDatabase nfirebaseDatabase;
    DatabaseReference nRef ;
    private String message;
    FirebaseUser currentUser;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_historial);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Intent intent = getIntent();
        message = intent.getStringExtra(PromocionActivity.EXTRA_MESSAGE);

        nRecyclerView = findViewById(R.id.recyclerView2);
        nRecyclerView.setHasFixedSize(true);
        nRecyclerView.setLayoutManager(new LinearLayoutManager (this));

        nfirebaseDatabase = FirebaseDatabase.getInstance();
       // user = FirebaseAuth.getInstance ().getCurrentUser ();
        nRef= nfirebaseDatabase.getReference("Usuarios/" + currentUser.getUid() + "/reservaciones");
    }
    @Override
    protected void onStart() {
        //cargamos las clases y instanciamos un firebaseRecyclerAdapter
        super.onStart();
        FirebaseRecyclerAdapter<Model, viewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, viewHolder>(
                        Model.class,
                        R.layout.row2,
                        viewHolder.class,
                        nRef
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder nviewHolder, Model model, int position) {
                        //obtenemos las vistas del titulo, descripcion y imagen para mostrar

                            nviewHolder.obtenerVistas2(getApplicationContext(), model.getUsuario (), model.getLugar (), model.getFecha (),
                                    model.getEstado (),model.getEstablecimiento (), getRef(position).getKey());
                    }
                };

        nRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public void obtenerCodigo(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setMessage(view.getContentDescription()).setTitle("Código de reservación");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void cancelarReservacion(View view){
        final View boton = view;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mDatabase.child("reservaciones").child(boton.getContentDescription().toString()).child("estado").setValue("cancelado");
                mDatabase.child("Usuarios").child(currentUser.getUid()).child("reservaciones").child(boton.getContentDescription().toString()).
                        child("estado").setValue("cancelado");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setMessage("¿Seguro que desea cancelar la reservación?").setTitle("Cancelar reservación");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void eliminarReservacion(View view){
        final View boton = view;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mDatabase.child("reservaciones").child(boton.getContentDescription().toString()).child("estado").setValue("eliminado");
                mDatabase.child("Usuarios").child(currentUser.getUid()).child("reservaciones").child(boton.getContentDescription().toString()).
                        child("estado").setValue("eliminado");
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });

        builder.setMessage("¿Eliminar registro del historial?").setTitle("Eliminar");

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

