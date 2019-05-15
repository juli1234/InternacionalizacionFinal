package com.example.proyectov1.clases;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

public class usuario {


    private String rutaImagen;
   private Integer documento;
   private String nombre;
   private String profesion;
   private  String dato;
   private Bitmap imagen;


    public String getRutaImagen() { return rutaImagen; }

    public void setRutaImagen(String rutaImagen) { this.rutaImagen = rutaImagen; }

    public Integer getDocumento() {
        return documento;
    }

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;


        try{
            byte[] byteCode= Base64.decode(dato,Base64.DEFAULT);
           // this.imagen= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);

            int alto=100;//pixeles imagen
            int ancho=100;//pixeles imagen


            Bitmap foto= BitmapFactory.decodeByteArray(byteCode,0,byteCode.length);
            this.imagen=Bitmap.createScaledBitmap(foto,alto,ancho,true);


        }catch (Exception e){
            e.printStackTrace();

        }


    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }

    public void setDocumento(Integer documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
}
