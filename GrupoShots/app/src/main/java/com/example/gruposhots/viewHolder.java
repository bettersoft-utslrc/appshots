package com.example.gruposhots;
/*
 * author: Raul Paolo Payan
 * version: 0.3
 *
 * esta clase se utiliza el metodo recycler view y se cargan las vistas
 *
 */
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

        } public void obtenerVistas2(Context ctx, String usuario, String lugar, String fecha, String estado, String establecimiento)
    {
        TextView Husuario = nview.findViewById(R.id.txtUsuarioHistorial);
        TextView Hlugar = nview.findViewById(R.id.txtLugarHistorial);
        TextView Hfecha = nview.findViewById(R.id.txtFechaHistorial);
        TextView Hestado = nview.findViewById(R.id.txtEstadoHistorial);
        TextView Hestablecimiento = nview.findViewById(R.id.txtEstaHist);

        Husuario.setText (usuario);
        Hlugar.setText (lugar);
        Hfecha.setText (fecha);
        Hestado.setText (estado);
        Hestablecimiento.setText (establecimiento);




    }





}
