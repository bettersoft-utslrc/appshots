package com.example.gruposhots;

/*
 *
 *
 * Esta clase es la pantalla de promociones donde se muestran y  cargan los datos desde la
 * base de datos
 *author: Raul Paolo Payan
 * version: 0.3
 */
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PromocionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener  {

    FirebaseAuth mAuth;
    public static final String EXTRA_MESSAGE = "com.example.gruposhots.MESSAGE";
    private TextView txtEmailU;
    private String message;

    //Definimos los objetos del recyclerView y la base de datos en firebase
    RecyclerView nRecyclerView;
   FirebaseDatabase nfirebaseDatabase;
   DatabaseReference nRef ;
   //Metodo onCreate cuando se incia la pantalla
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promocion);

        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cambio = new Intent (PromocionActivity.this,seleccionLocalActivity.class);
                PromocionActivity.this.startActivity(cambio);
            }});

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Action bar
        ActionBar actionBar = getSupportActionBar();

        //Set tittle
        actionBar.setTitle("Promociones y Eventos");
        // referenciamos RecicleView desde la vista
        nRecyclerView = findViewById(R.id.recyclerView);
        nRecyclerView.setHasFixedSize(true);

        //obtenemos layout como LinearLayout
        nRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtenemos la base de datos y referencia de la tabla
        nfirebaseDatabase = FirebaseDatabase.getInstance();
        nRef= nfirebaseDatabase.getReference("publicidad");
    }
    //Cargamos datos dentro del recyclerView con el metado onstart

    @Override
    protected void onStart() {
        //cargamos las clases y instanciamos un firebaseRecyclerAdapter
        super.onStart();
        FirebaseRecyclerAdapter<Model, viewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, viewHolder>(
                        Model.class,
                        R.layout.row,
                        viewHolder.class,
                        nRef
                ) {
                    @Override
                    protected void populateViewHolder(viewHolder nviewHolder, Model model, int position) {
                            //obtenemos las vistas del titulo, descripcion y imagen para mostrar
                        nviewHolder.obtenerVistas(getApplicationContext(), model.getTitulo(), model.getDescripcion(), model.getImage());
                    }
                };
        nRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.promocion, menu);

        txtEmailU = (TextView) findViewById(R.id.txtUsuarioEmailHead);
        txtEmailU.setText(message);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void Cambiar() {
        Intent cambio = new Intent (PromocionActivity.this,seleccionLocalActivity.class);
        PromocionActivity.this.startActivity(cambio);
    }
    private void Cambiar2() {
        Intent cambio = new Intent (PromocionActivity.this,PerfilActivity.class);
        PromocionActivity.this.startActivity(cambio);
    }
    private void Cambiar3() {
        Intent cambio = new Intent (PromocionActivity.this,BetterSoftActivity.class);
        PromocionActivity.this.startActivity(cambio);
    }
    private void Cambiar4() {
        Intent cambio = new Intent (PromocionActivity.this,HistorialActivity.class);
        cambio.putExtra(EXTRA_MESSAGE, message);
        PromocionActivity.this.startActivity(cambio);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.SlReservacion) {
            Cambiar();
        } else if (id == R.id.SlHistorialRes) {
            Cambiar4 ();

        } else if (id == R.id.SlPerfil) {
            Cambiar2 ();
        }  else if (id == R.id.SlCerrarSesion) {

        } else if(id == R.id.SlBetter ){
            Cambiar3 ();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }





}
