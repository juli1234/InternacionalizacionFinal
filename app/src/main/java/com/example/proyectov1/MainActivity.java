package com.example.proyectov1;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.proyectov1.fragment.ActuarlizarEliminarC;
import com.example.proyectov1.fragment.ConsultarUsuario;
import com.example.proyectov1.fragment.ConvenioRegistro;
import com.example.proyectov1.fragment.EliminarActualizar;
import com.example.proyectov1.fragment.ListaUsuario;
import com.example.proyectov1.fragment.ListaUsuarioImagen;
import com.example.proyectov1.fragment.RegistroC;
import com.example.proyectov1.fragment.RegistroUsuario;
import com.example.proyectov1.interfaces.IFragments;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, IFragments
        {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            miFragment=new ConvenioRegistro();
            fragmentSeleccionado=true;

            getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,miFragment).commit();
        }


        return super.onOptionsItemSelected(item);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment miFragment=null;
        boolean fragmentSeleccionado=false;

        if (id == R.id.nav_camera) {

            miFragment=new RegistroUsuario();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_gallery) {
            miFragment=new ConsultarUsuario();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_slideshow) {
            miFragment=new ListaUsuario();
            fragmentSeleccionado=true;

        } else if (id == R.id.nav_manage) {
            miFragment=new EliminarActualizar();
            fragmentSeleccionado=true;


        } else if (id == R.id.nav_share) {
           miFragment=new RegistroC();
           fragmentSeleccionado=true;

        } else if (id == R.id.nav_send) {
            miFragment=new ActuarlizarEliminarC();
            fragmentSeleccionado=true;

        }

        if (fragmentSeleccionado==true){
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor,miFragment).commit();
    }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
