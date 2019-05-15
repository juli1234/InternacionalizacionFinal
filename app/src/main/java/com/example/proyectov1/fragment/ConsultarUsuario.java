package com.example.proyectov1.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectov1.R;
import com.example.proyectov1.clases.Volleysingle;
import com.example.proyectov1.clases.usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ConsultarUsuario.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ConsultarUsuario#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarUsuario extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    EditText eddocumento;
    TextView txtnombre,txtprofesion;
    Button consultar;
    ImageView imagen1;

    ProgressDialog progreso;

   // RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public ConsultarUsuario() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConsultarUsuario.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarUsuario newInstance(String param1, String param2) {
        ConsultarUsuario fragment = new ConsultarUsuario();
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

        View vista=inflater.inflate(R.layout.fragment_consultar_usuario,container,false);

        eddocumento=(EditText) vista.findViewById(R.id.condocumento);
        txtnombre=(TextView) vista.findViewById(R.id.txtNombre);
        txtprofesion=(TextView) vista.findViewById(R.id.txtProfesion);
        consultar=(Button) vista.findViewById(R.id.consultar);
        imagen1=(ImageView) vista.findViewById(R.id.imagen1);
       // request= Volley.newRequestQueue(getContext());

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });


        return vista;
    }

    private void cargarWebService() {

        progreso=new ProgressDialog(getContext());
        progreso.setMessage("cargando....");
        progreso.show();

        String ip=getString(R.string.ip);
        String url=ip+"/ejemploBDRemota/wsJSONConsultarUsuarioImagen.php?documento="
                +eddocumento.getText().toString();

        jsonObjectRequest =new JsonObjectRequest(Request.Method.GET,url,null,this,this);
        //request.add(jsonObjectRequest);

        Volleysingle.getIntanciaVolley(getContext()).addToRequestQueue(jsonObjectRequest);


    }


    @Override
    public void onErrorResponse(VolleyError error) {

        progreso.hide();
        Toast.makeText(getContext(),"Error al consultar los datos",Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {

        progreso.hide();

        Toast.makeText(getContext(),"Consulta exitosa ",Toast.LENGTH_SHORT).show();

        usuario miusuaio=new usuario();
        JSONArray json =response.optJSONArray("usuario");
        JSONObject jsonObject=null;

        try {
            jsonObject=json.getJSONObject(0);
            miusuaio.setNombre(jsonObject.optString("nombre"));
            miusuaio.setProfesion(jsonObject.optString("profesion"));
            miusuaio.setDato(jsonObject.optString("imagen"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txtnombre.setText("Nombre: "+miusuaio.getNombre());
        txtprofesion.setText("Profesion: "+miusuaio.getProfesion());


        if (miusuaio.getImagen()!=null){

            imagen1.setImageBitmap(miusuaio.getImagen());

        }else {

            imagen1.setImageResource(R.drawable.img_base);
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
