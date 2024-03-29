package com.example.gruposhots;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gruposhots.Dashboard.PerfilPresenter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.r0adkll.slidr.Slidr;

public class PerfilActivity extends AppCompatActivity implements com.example.gruposhots.Dashboard.PerfilActivity.View {

    private EditText edt_Nombre, edt_Email, edt_pass;
    private Button btn_GuardarPerfil;
    private com.example.gruposhots.Dashboard.PerfilActivity.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        presenter = new PerfilPresenter(this);
        setContentView (R.layout.activity_perfil);
        setViews();
        Slidr.attach (this);


    }

    private void setViews() {

        edt_Nombre = findViewById (R.id.txtNombrePerfil);
        edt_Email =findViewById (R.id.txtEmailPerfil);
        edt_pass = findViewById (R.id.txtContraPerfil);
        btn_GuardarPerfil = findViewById (R.id.btnPerfil);
        presenter.onCharge ();
        btn_GuardarPerfil.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                presenter.onSave (edt_Nombre.getText ().toString ().trim (), edt_Email.getText ().toString ().trim (), edt_pass.getText ().toString ().trim ());

            }
        });
    }
    private void setInputs(boolean enable){
        edt_Nombre.setEnabled (enable);
        edt_Email.setEnabled (enable);
        edt_pass.setEnabled (enable);
        btn_GuardarPerfil.setEnabled (enable);

    }


    @Override
    public void enableInpuds() {
    setInputs (true);
    }

    @Override
    public void disableInputs() {
    setInputs (false);
    }

    @Override
    public void fillEditText(String Nombre, String Email, String Password) {
        edt_Nombre.setText (Nombre);
        edt_Email.setText (Email);
        edt_pass.setText (Password);

    }

    @Override
    public void onError(String error) {

        Toast.makeText (this, "Error", Toast.LENGTH_SHORT).show ();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        presenter.onDestroy ();
    }
}