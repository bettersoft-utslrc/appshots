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
import android.content.Intent;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RecervaActivity extends AppCompatActivity  {
    private EditText nombre;
    private EditText email;
    private EditText pass;
    //private EditText passConfirm;
    private Button buttonRegistrar;
    private ProgressDialog progressDialog;

   private String Nombre;
   private String Email;
    private String Password;
  //  private String PasswordConfirm;

    FirebaseAuth nAuth;
    DatabaseReference nData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recerva);

        nAuth = FirebaseAuth.getInstance();
        nData = FirebaseDatabase.getInstance().getReference();

    nombre = (EditText) findViewById(R.id.txtNombre);
    email = (EditText ) findViewById(R.id.txtEmail);
    pass = (EditText)findViewById(R.id.txtPassword);
   // passConfirm = (EditText)findViewById(R.id.txtPasswordConfirm);
    buttonRegistrar = (Button)findViewById(R.id.btnRegistro);


        progressDialog = new ProgressDialog(this);
    buttonRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Nombre = nombre.getText().toString();
            Email = email.getText().toString();
            Password = pass.getText().toString();
            //PasswordConfirm = passConfirm.getText().toString();

            if(!Nombre.isEmpty() && !Email.isEmpty() && !Password.isEmpty() )
            {
                if (Password.length() >=6)
                {
                    RegistrarUsuario();
                }else
                    {
                        Toast.makeText(RecervaActivity.this, "El password debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    }


            }
            else
                {
                    Toast.makeText(RecervaActivity.this, "Llenar los campos", Toast.LENGTH_SHORT).show();
                }

        }
    });



    }
        private void RegistrarUsuario()
        {
            nAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Map<String, Object> map = new HashMap<>();
                        map.put("Nombre", Nombre);
                        map.put("Email", Email);
                        map.put("Password", Password);


                        String id = nAuth.getCurrentUser().getUid();

                        nData.child("Usuarios").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task2) {
                                if(task2.isSuccessful()){
                                    startActivity(new Intent(RecervaActivity.this, PromocionActivity.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(RecervaActivity.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else {
                        Toast.makeText(RecervaActivity.this, "No se pudo registrar", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            progressDialog.setMessage("Realizando Registro...");
            progressDialog.show();
        }

}

