package com.example.gruposhots;
/*
 * author: Raul Paolo Payan
 * version: 0.3
 *
 * esta clase es la pantalla de Registro donde le usuario puede hacer
 * su Registro por medio de su correo y su contraseña y confirmacion de contraseña.
 *
 */

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RecervaActivity extends AppCompatActivity implements View.OnClickListener {

    //aqui Definimos los objetos de la vista
    private EditText TextEmail;
    private EditText TextPassword;
    private EditText TextPasswordconfirm;
    private EditText TextUsuario;
    private Button btonRegistrar;
    private ProgressDialog progressDialog;


    //Declaramos un objeto firebaseAuth para nuestra base de datos firebase
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicializamos el objeto firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance();

        //Referenciamos las vistas del archivo xml
        TextEmail = (EditText) findViewById(R.id.txtEmail);
        TextPassword = (EditText) findViewById(R.id.txtPassword);
        btonRegistrar = (Button) findViewById(R.id.btnRegistro);
        TextUsuario = (EditText) findViewById(R.id.txtUsuario);
        TextPasswordconfirm = (EditText) findViewById(R.id.txtPasswordConfirm);


        progressDialog = new ProgressDialog(this);

        //agregamos el listener para el boton del registro
        btonRegistrar.setOnClickListener(this);
    }


    // metodo creado para registrar a los usuarios de la aplicacion
    private void registrarUsuario(){

        //Obtenemos el email, nombre de usuario y la contraseña desde las cajas de texto
        String email = TextEmail.getText().toString().trim();
        String password  = TextPassword.getText().toString().trim();
        String usuario = TextUsuario.getText().toString().trim();
        String ConfirmPassword = TextPasswordconfirm.getText().toString().trim();

        //Verificamos que las cajas de texto no esten vacías
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta ingresar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(usuario)){
            Toast.makeText(this,"Se debe ingresar un nombre de usuario",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(ConfirmPassword)){
            Toast.makeText(this,"Debe confirmar la contraseña",Toast.LENGTH_LONG).show();
            return;
        }

            //mostramos un mensaje de progreso cuando se esta haciendo el registro
        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        //creamos un nuevo usuario con el metodo createUserWithEmailAndPassword
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //verificamos que entre correctamente
                        if(task.isSuccessful()){

                            Toast.makeText(RecervaActivity.this,"Se ha registrado el usuario con el email: "+ TextEmail.getText(),Toast.LENGTH_LONG).show();
                        }else{
                            //si no entra se miestra el siguiente mensaje
                            Toast.makeText(RecervaActivity.this,"No se pudo registrar el usuario ",Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                .setDisplayName("Jane Q. User")
                .build();

        user.updateProfile(profileUpdates)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                           // Log.d(TAG, "User profile updated.");
                        }
                    }
                });


    }

    @Override
    public void onClick(View view) {
        //Invocamos al método:

        registrarUsuario();

    }
}

