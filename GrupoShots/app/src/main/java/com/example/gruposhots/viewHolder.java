package com.example.gruposhots;
/*
 * author: Raul Paolo Payan
 * version: 0.3
 *
 * esta clase se utiliza el metodo recycler view y se cargan las vistas
 *
 */
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


public class viewHolder extends RecyclerView.ViewHolder {

    View nview, nview2;
        //se crea el metodo viewHolder al implementar el extends a la clase
    public viewHolder(@NonNull View itemView) {
        super(itemView);

        nview = itemView;
        nview2 = itemView;
    }

        //en este metodo se obtienen las vistas desde el archivo xml
        public void obtenerVistas(Context ctx, String titulo, String descripcion, String image)
        {
            TextView nTituloView = nview.findViewById(R.id.rTitleTv);
            TextView nDescripcion = nview.findViewById(R.id.rDescriptionTV);
            ImageView nImage = nview.findViewById(R.id.rImageView);

            nTituloView.setText(titulo);
            nDescripcion.setText(descripcion);
            Picasso.get().load(image).into(nImage);

        } public void obtenerVistas2(Context ctx, String usuario, String lugar, String fecha, String estado, String establecimiento, String key)
    {
        TextView Husuario = nview.findViewById(R.id.txtUsuarioHistorial);
        TextView Hlugar = nview.findViewById(R.id.txtLugarHistorial);
        TextView Hfecha = nview.findViewById(R.id.txtFechaHistorial);
        TextView Hestado = nview.findViewById(R.id.txtEstadoHistorial);
        TextView Hestablecimiento = nview.findViewById(R.id.txtEstaHist);
        LinearLayoutCompat cardHistorial = (LinearLayoutCompat) nview.findViewById(R.id.layoutHistorial);
        CardView cardV = (CardView) nview.findViewById(R.id.cardV1);

        Husuario.setText (usuario);
        Hlugar.setText (lugar);
        Hfecha.setText (fecha);
        Hestablecimiento.setText (establecimiento);

        Button btnCodigo = (Button) nview.findViewById(R.id.btnCode);
        btnCodigo.setContentDescription(new StringBuffer(key));
        Button btnCancel = (Button) nview.findViewById(R.id.btnCancel);
        btnCancel.setContentDescription(new StringBuffer(key));
        Button btnEliminar = (Button) nview.findViewById(R.id.btnEliminar);
        btnEliminar.setContentDescription(new StringBuffer(key));


        TextView Hcodigo = nview.findViewById(R.id.txtCodigo);
        Hcodigo.setText(key);
        Husuario.setVisibility(View.GONE);

        if(estado.equals("completado")){
            btnCodigo.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnEliminar.setVisibility(View.VISIBLE);
            Hestado.setText("Completado");
            Hestado.setTextColor(Color.GREEN);
        }
        else if(estado.equals("cancelado")){
            btnCodigo.setVisibility(View.GONE);
            btnCancel.setVisibility(View.GONE);
            btnEliminar.setVisibility(View.VISIBLE);
            Hestado.setText("Cancelado");
            Hestado.setTextColor(Color.RED);
        }
        else if(estado.equals("espera")){
            Hestado.setText("Por asistir");
        }
        else if(estado.equals("eliminado")){
            cardHistorial.setVisibility(View.GONE);
            cardV.setVisibility(View.GONE);
        }
    }





}
