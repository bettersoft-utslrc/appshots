package com.example.gruposhots.Dashboard;

import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

        public class PerfilModel implements PerfilActivity.Model {

    private PerfilActivity.TaskListener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;

    public PerfilModel(PerfilActivity.TaskListener listener) {
        this.listener = listener;
        database = FirebaseDatabase.getInstance ();
        user = FirebaseAuth.getInstance ().getCurrentUser ();
        reference = database.getReference ("Usuarios").child (user.getUid ());
    }

    @Override
    public void chargeNombre() {
        reference.addListenerForSingleValueEvent (new ValueEventListener () {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists ()){
                    String  Nombre = (String) dataSnapshot.child ("Nombre").getValue ();
                    String  email = (String) dataSnapshot.child ("Email").getValue ();
                    String  password = (String) dataSnapshot.child ("Password").getValue ();
                    listener.onSucessCharge (Nombre, email ,password);
                }
                else {
                    listener.onError ("No hay datos");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError (databaseError.getMessage ());
            }
        });

    }

    @Override
    public void setNombre(String Nombre) {
        reference.child ("Nombre").setValue (Nombre).addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful ()){
                    listener.onSucessSave ();

                }else {
                    if(task.getException ()!=null)
                    listener.onError (task.getException ().getMessage ());
                }
            }
        });

    }

            @Override
            public void chargeEmail() {

            }

            @Override
    public void setEmail(String Email) {
        reference.child ("Email").setValue (Email).addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful ()){
                    listener.onSucessSave ();
                }else {
                    if(task.getException ()!=null)
                        listener.onError (task.getException ().getMessage ());
                }
            }
        });



    }

            @Override
            public void chargePassword() {

            }

            @Override
    public void setPassword(String Password) {
        reference.child ("Password").setValue (Password).addOnCompleteListener (new OnCompleteListener<Void> () {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful ()){
                    listener.onSucessSave ();
                }else {
                    if(task.getException ()!=null)
                        listener.onError (task.getException ().getMessage ());
                }
            }
        });

    }


}
