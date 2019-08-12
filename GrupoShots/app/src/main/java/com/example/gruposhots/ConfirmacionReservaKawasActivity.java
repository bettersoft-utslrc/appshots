package com.example.gruposhots;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ConfirmacionReservaKawasActivity extends AppCompatActivity {
    private TextView Fecha;
    private TextView Vip;
    private TextView Mesa;
    private TextView Usuario;
    private ImageView imgVip;
    private ImageView imgMesa;
    private ProgressDialog progressDialog;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private FirebaseUser user;
    private ImageView QR;
    private Button btnCodigo;
    String text2Qr;
    String fecha;
    String lugar;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_confirmacion_reserva_kawas);

        imgMesa = (ImageView) findViewById(R.id.imgMesa);
        imgVip = (ImageView) findViewById(R.id.imgVIP);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        user = FirebaseAuth.getInstance ().getCurrentUser ();
        Usuario = (TextView) findViewById (R.id.txtnombreKawasResiver);
        Usuario.setText ("Usuario: " + user.getEmail());

        Fecha = (TextView) findViewById (R.id.txtFechaKawasResiver);
        fecha = getIntent ().getStringExtra ("fecha");
        Fecha.setText ("Fecha: " + fecha);

        String vip = getIntent ().getStringExtra ("vip");
        String mesa = getIntent ().getStringExtra ("mesa");
        Mesa = (TextView) findViewById (R.id.txtMesaKawasResiver);
        Vip = (TextView) findViewById (R.id.txtVipKawasResiver);

        if(vip.equals("- -")){
            Mesa.setText ("Numero de mesa: " +  mesa);
            Vip.setVisibility(View.GONE);
            imgVip.setVisibility(View.GONE);
            lugar = "MESA" + mesa;
        }
        else if(mesa.equals("- -")){
            Vip.setText ("Numero de VIP: " + vip);
            Mesa.setVisibility(View.GONE);
            imgMesa.setVisibility(View.GONE);
            lugar = "VIP" + vip;
        }

        progressDialog = new ProgressDialog(this);
    }

    public void confirmarReservacion(View view){
        progressDialog.setMessage("Iniciando Sesión...");
        progressDialog.show();

        Reserva reserva = new Reserva("Kawas","espera", fecha, lugar, user.getEmail());
        Random r = new Random();
        int ini = r.nextInt(20 - 1) + 1;
        int ini2 = r.nextInt(20 - 1) + 1;
        int ini3 = r.nextInt(20 - 1) + 1;
        String code = new SimpleDateFormat("yyyyMMddmmss").format(new Date());
        String reservacionId = user.getUid().toString().substring(ini, ini + 3) + code.substring(0, 6) +
                user.getUid().toString().substring(ini3, ini3 + 3) + code.substring(6, 12) + user.getUid().toString().substring(ini2, ini2 + 3);
        System.out.println(reservacionId);

        mDatabase.child("reservaciones").child(reservacionId).setValue(reserva);
        mDatabase.child("Usuarios").child(user.getUid()).child("reservaciones").child(reservacionId).setValue(reserva);
        progressDialog.dismiss();

        intent = new Intent(this, PromocionActivity.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intent);
                finish();
            }
        });

        builder.setMessage("Código de reservación: " + reservacionId + "\nEl código lo podrá encontrar en Historial de Reservaciones para mostrarlo el día de su reservación").setTitle("Reservación exitosa");

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void onBackPressed(){
        intent = new Intent(this, RecervaKawasActivity.class);
        startActivity(intent);
        finish();
    }
}
