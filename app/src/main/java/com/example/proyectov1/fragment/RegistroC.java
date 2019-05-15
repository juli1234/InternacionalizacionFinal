package com.example.proyectov1.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.proyectov1.R;
import com.example.proyectov1.clases.Volleysingle;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RegistroC.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RegistroC#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistroC extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;




    private static final String CARPETA_PRINCIPAL = "misImagenesApp/";//directorio principal
    private static final String CARPETA_IMAGEN = "imagenes";//carpeta donde se guardan las fotos
    private static final String DIRECTORIO_IMAGEN = CARPETA_PRINCIPAL + CARPETA_IMAGEN;//ruta carpeta de directorios
    private String path;//almacena la ruta de la imagen
    File fileImagen;
    Bitmap bitmap;

    private final int MIS_PERMISOS = 100;
    private static final int CODE_SELECCIONA =10 ;
    private static final int CODE_FOTO =20 ;


    Button btnregistrar;
    EditText eddocumento,ednombre,edprofesion;
    ProgressDialog progreso;
    ImageView nowifi;


    RelativeLayout layoutRegistrar;//permisos

    //RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    StringRequest stringRequest;


    public RegistroC() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistroC.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistroC newInstance(String param1, String param2) {
        RegistroC fragment = new RegistroC();
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


        View vista=inflater.inflate(R.layout.fragment_registro_c,container,false);
        eddocumento=(EditText)vista.findViewById(R.id.documento);
        ednombre=(EditText) vista.findViewById(R.id.nombre);
        edprofesion=(EditText) vista.findViewById(R.id.profesion);
        btnregistrar=(Button) vista.findViewById(R.id.registrar);


        nowifi=(ImageView) vista.findViewById(R.id.nowifi);

        nowifi.setVisibility(View.INVISIBLE);



        //request= Volley.newRequestQueue(getContext());

        layoutRegistrar= (RelativeLayout) vista.findViewById(R.id.idLayoutRegistrar);

        ConnectivityManager con= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo=con.getActiveNetworkInfo();

        btnregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (  networkInfo!=null && networkInfo.isConnected()){

                    cargarWebService();
                }else {
                    nowifi.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(),"no se ha podido conectar a internet",Toast.LENGTH_SHORT).show();
                }
            }

            private void cargarWebService() {



                progreso=new ProgressDialog(getContext());
                progreso.setMessage("cargando....");
                progreso.show();


                String ip=getString(R.string.ip);
                String url=ip+"/convenio//wsJSONRegistro.php?";

                stringRequest=new StringRequest(Request.Method.POST,url ,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progreso.hide();

                        if(response.trim().equalsIgnoreCase("registra")){

                            eddocumento.setText("");
                            ednombre.setText("");
                            edprofesion.setText("");
                            Toast.makeText(getContext(),"se registro exitosamente",Toast.LENGTH_SHORT).show();

                        }else{

                            Toast.makeText(getContext(),"no se registro",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(),"error al registrar",Toast.LENGTH_SHORT).show();

                        progreso.hide();


                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        String documento=eddocumento.getText().toString();
                        String nombre=ednombre.getText().toString();
                        String profesion=edprofesion.getText().toString();



                        Map<String,String> parametros=new HashMap<>();
                        parametros.put("documento",documento);
                        parametros.put("nombre",nombre);
                        parametros.put("profesion",profesion);




                        return parametros;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 10, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                Volleysingle.getIntanciaVolley(getContext()).addToRequestQueue(stringRequest);



            }
        });
return vista;
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
