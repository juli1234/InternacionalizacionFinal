package com.example.proyectov1.clases;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Volleysingle {  private static  Volleysingle intanciaVolley;
    private RequestQueue request;
    private static Context contexto;

    private Volleysingle(Context context) {
        contexto = context;
        request = getRequestQueue();
    }


    public static synchronized  Volleysingle getIntanciaVolley(Context context) {
        if (intanciaVolley == null) {
            intanciaVolley = new  Volleysingle(context);
        }

        return intanciaVolley;
    }

    public RequestQueue getRequestQueue() {
        if (request == null) {
            request = Volley.newRequestQueue(contexto.getApplicationContext());
        }

        return request;
    }

    public <T> void addToRequestQueue(Request<T> request) {
        getRequestQueue().add(request);
    }

}
