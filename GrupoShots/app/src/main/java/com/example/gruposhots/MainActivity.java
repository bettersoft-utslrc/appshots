package com.example.gruposhots;
/*

*
* esta clase es la pantalla de login donde le usuario puede hacer
* su incio de sesion por medio de su correo y su contraseña.
* author: Raul Paolo Payan
 * version: 0.3
*/
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth; //



public class MainActivity extends AppCompatActivity implements View.OnClickListener{


    //aqui Definimos los objetos de la vista
    private EditText TextEmail;
    private EditText TextPassword;
    private Button BtnEntrar,btnRegitro;
    private ProgressDialog progressDialog;



    //Declaramos un objeto firebaseAuth para nuestra base de datos
    private FirebaseAuth firebaseAuth;


    //este es el metodo onCreate de cuando nuestra activity se crea
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


                  //inicializamos el objeto firebaseAuth que se habia declarado anteriormente
                firebaseAuth = FirebaseAuth.getInstance();


                //Referenciamos los vistas del archivo xml
        TextEmail = (EditText) findViewById(R.id.txtEmail);
        TextPassword = (EditText) findViewById(R.id.txtPassword);
        BtnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnRegitro = (Button) findViewById(R.id.btnRegistrarse) ;



            //aqui se instancio el objeto progessDialog
        progressDialog = new ProgressDialog(this);



        //agregamos el listener para nuestros botones
        BtnEntrar.setOnClickListener( this);
        btnRegitro.setOnClickListener( this);
    }


        /*este metodo se creo para cambiar de la pantalla actual hacia la pantalla llamada
        *RecervaActivity
         */
    private void Cambiar() {
        Intent cambio = new Intent (MainActivity.this,RecervaActivity.class);
        MainActivity.this.startActivity(cambio);
        finish();
    }


        //este es el metodo que se utiliza para que el usuario pueda iniciar sesion
    private void loginUsuario() {

        /*
            *Obtenemos el email y la contraseña desde las cajas de texto
            *utilizamos el metodo getText para obtener el texto de la caja de texto del archivo xml, el
            * metodo to string convertir a string y el metodo trim para eliminar espacios que
            * haya en la caja de texto
        */
        final String email = TextEmail.getText().toString().trim();
        String password = TextPassword.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar la contraseña", Toast.LENGTH_LONG).show();
            return;
        }


        progressDialog.setMessage("Iniciando Sesión...");
        progressDialog.show();

        /*
        Logear Usuario mediante el objeto de nuesta base de datos con el metodo singWithEmailAndPassword

         */
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //comprobamos que se pueda entrar correctamente
                        if (task.isSuccessful()) {

                            Toast.makeText(MainActivity.this, "Bienvenido " + TextEmail.getText(), Toast.LENGTH_LONG).show();
                            Intent intencion = new Intent (getApplication(),PromocionActivity.class);

                            //intencion.putExtra(PromocionActivity.user, email);
                            startActivity(intencion);
                            finish();
                        } else {
                            //si no se pudo acceder se muestra este mensaje
                            Toast.makeText(MainActivity.this, "No se pudo ingresar ", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }



    @Override
    /*
    *aqui esta un ciclo switch en el metodo onClick para que cumpla la funcion espesificada
    *  dependiendo el boton que el usuario precione
    */
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.btnRegistrarse:
                Cambiar();
                break;

            case R.id.btnEntrar:
                loginUsuario();
                break;
        }

    }


}