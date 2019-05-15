package com.example.proyectov1.interfaces;


import com.example.proyectov1.fragment.ActuarlizarEliminarC;
import com.example.proyectov1.fragment.ConvenioRegistro;
import com.example.proyectov1.fragment.EliminarActualizar;
import com.example.proyectov1.fragment.ListaUsuario;
import com.example.proyectov1.fragment.ListaUsuarioImagen;
import com.example.proyectov1.fragment.ConsultarUsuario;
import com.example.proyectov1.fragment.RegistroC;
import com.example.proyectov1.fragment.RegistroUsuario;



public interface IFragments extends
        RegistroUsuario.OnFragmentInteractionListener,ConsultarUsuario.OnFragmentInteractionListener,
        ListaUsuario.OnFragmentInteractionListener,ListaUsuarioImagen.OnFragmentInteractionListener, EliminarActualizar.OnFragmentInteractionListener,
        ConvenioRegistro.OnFragmentInteractionListener, RegistroC.OnFragmentInteractionListener, ActuarlizarEliminarC.OnFragmentInteractionListener
           {
}
