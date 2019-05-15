package com.example.proyectov1.adaptadores;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.proyectov1.R;
import com.example.proyectov1.clases.usuario;

import java.util.ArrayList;
import java.util.List;

public class usuariosAdaptador extends RecyclerView.Adapter<usuariosAdaptador.usuariosholder> {

    List<usuario> listausuario;

    public usuariosAdaptador(ArrayList<usuario> listausuario)  {
        this.listausuario = listausuario;
    }

    @NonNull
    @Override
    public usuariosholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista= LayoutInflater.from(parent.getContext()).inflate(R.layout.usuario_lista,parent,false);
        RecyclerView.LayoutParams layoutParams=new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new usuariosholder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull usuariosholder holder, int position) {
        holder.txtDocumento.setText(listausuario.get(position).getDocumento().toString());
        holder.txtNombre.setText(listausuario.get(position).getNombre().toString());
        holder.txtProfesion.setText(listausuario.get(position).getProfesion().toString());
    }

    @Override
    public int getItemCount() {

        return listausuario.size();
    }

    public static class usuariosholder extends RecyclerView.ViewHolder{


        TextView txtDocumento,txtNombre,txtProfesion;

        public usuariosholder(View itemView) {
            super(itemView);
            txtDocumento= (TextView) itemView.findViewById(R.id.txtDocumento);
            txtNombre= (TextView) itemView.findViewById(R.id.txtNombre);
            txtProfesion= (TextView) itemView.findViewById(R.id.txtProfesion);

        }
    }
}
