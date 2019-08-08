package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistorialActivity extends AppCompatActivity {

    RecyclerView nRecyclerView;
    FirebaseDatabase nfirebaseDatabase;
    DatabaseReference nRef ;
    private FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_historial);

        nRecyclerView = findViewById(R.id.recyclerView2);
        nRecyclerView.setHasFixedSize(true);
        nRecyclerView.setLayoutManager(new LinearLayoutManager (this));

        nfirebaseDatabase = FirebaseDatabase.getInstance();
       // user = FirebaseAuth.getInstance ().getCurrentUser ();
        nRef= nfirebaseDatabase.getReference("reservaciones");



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
                                model.getEstado (),model.getEstablecimiento ());
                    }
                };
        nRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
}
