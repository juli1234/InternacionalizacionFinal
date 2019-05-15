package com.example.proyectov1.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectov1.R;
import com.example.proyectov1.adaptadores.usuariosAdaptador;
import com.example.proyectov1.clases.Volleysingle;
import com.example.proyectov1.clases.usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListaUsuario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListaUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaUsuario extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    RecyclerView recyclerViewUsuarios;
    ArrayList<usuario> listausuario;

    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;


    public ListaUsuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static ListaUsuario newInstance(String param1, String param2) {
        ListaUsuario fragment = new ListaUsuario();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_lista_usuario,container,false);
        listausuario=new ArrayList<>();

        recyclerViewUsuarios=(RecyclerView) vista.findViewById(R.id.reciclerview);
        recyclerViewUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewUsuarios.setHasFixedSize(true);

       // request= Volley.newRequestQueue(getContext());

        cargarWebService();

        return vista;
    }

    private void cargarWebService() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Consultando...");
        progreso.show();

        String ip=getString(R.string.ip);

        String url=ip+"/ejemploBDRemota/wsJSONConsultarLista.php";

        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        // request.add(jsonObjectRequest);
        Volleysingle.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {


        Toast.makeText(getContext(),"error al ingresar los datos",Toast.LENGTH_SHORT).show();
        System.out.println();
        Log.d("Error", error.toString());
        progreso.hide();
    }

    @Override
    public void onResponse(JSONObject response) {

        usuario usuario=null;

        JSONArray json=response.optJSONArray("usuario");

        try {

            for (int i=0;i<json.length();i++){
                usuario=new usuario();
                JSONObject jsonObject=null;
                jsonObject=json.getJSONObject(i);

                usuario.setDocumento(jsonObject.optInt("documento"));
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setProfesion(jsonObject.optString("profesion"));
                listausuario.add(usuario);
            }
            progreso.hide();
            usuariosAdaptador adapter=new usuariosAdaptador(listausuario);
            recyclerViewUsuarios.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), "No se ha podido establecer conexión con el servidor" +
                    " "+response, Toast.LENGTH_LONG).show();
            progreso.hide();
        }


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
