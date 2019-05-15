package com.example.proyectov1.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectov1.R;
import com.example.proyectov1.clases.usuario;

import java.util.List;

public class  usuariosAdaptadorImagen extends RecyclerView.Adapter<usuariosAdaptadorImagen.UsuariosHolder> {

    List<usuario> listausuario;

    public usuariosAdaptadorImagen(List<usuario> listausuario) {
        this.listausuario = listausuario;
    }

    @NonNull
    @Override
    public UsuariosHolder onCreateViewHolder(@NonNull ViewGroup parent ,int viewType) {

        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_lista_ima,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new UsuariosHolder(vista);

    }

    @Override
    public void onBindViewHolder(@NonNull UsuariosHolder usuariosHolder, int position) {

        usuariosHolder.txtdocumento.setText(listausuario.get(position).getDocumento().toString());
        usuariosHolder.txtnombre.setText(listausuario.get(position).getNombre().toString());
        usuariosHolder.txtprofesion.setText(listausuario.get(position).getProfesion().toString());

        if(listausuario.get(position).getImagen()!=null) {

            usuariosHolder.imagen2.setImageBitmap(listausuario.get(position).getImagen());

        }else{

            usuariosHolder.imagen2.setImageResource(R.drawable.img_base);

        }

    }

    @Override
    public int getItemCount() {
        return listausuario.size();
    }

    public class UsuariosHolder extends RecyclerView.ViewHolder {

         TextView txtdocumento,txtnombre,txtprofesion;
         ImageView imagen2;

        public UsuariosHolder(@NonNull View itemView) {
            super(itemView);
            txtdocumento=(TextView) itemView.findViewById(R.id.idDocumento);
            txtnombre=(TextView) itemView.findViewById(R.id.idNombre);
            txtprofesion=(TextView) itemView.findViewById(R.id.idProfesion  );
            imagen2=(ImageView) itemView.findViewById(R.id.idImagen);
        }
    }
}
