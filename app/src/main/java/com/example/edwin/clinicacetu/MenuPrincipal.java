package com.example.edwin.clinicacetu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import Modelos.Usuarios;

public class MenuPrincipal extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout nDrawable_layout;
    private ActionBarDrawerToggle nToggle;
    private Usuarios objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        objUsuario = (Usuarios) getIntent().getExtras().getSerializable("DatosUsuario");

        nDrawable_layout = (DrawerLayout) findViewById(R.id.drawerLayout);
        nToggle = new ActionBarDrawerToggle(this, nDrawable_layout, R.string.abierto, R.string.cerrado);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        nDrawable_layout.addDrawerListener(nToggle);
        nToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(this);
    }

    //metodo que maneja los click de la barra principal, para mostrar y ocultar el menu principal
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (nToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    // metodo que maneja los clicks o seleccion de una opcion del menu que se desplega
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.opc_nueva_cita) {
            Intent iNuevaCita = new Intent(getApplicationContext(), NuevaCita.class);
            iNuevaCita.putExtra("DatosUsuario", objUsuario);
            startActivity(iNuevaCita);

        }
        if (id == R.id.opc_citas_aprobadas) {
            Intent iEstadoCita = new Intent(getApplicationContext(), EstadosCitas.class);
            iEstadoCita.putExtra("DatosUsuario", objUsuario);
            startActivity(iEstadoCita);

        }
        if(id == R.id.opc_historial){
            Intent objHistorialClinico = new Intent(getApplicationContext(), historialMedico.class);
            startActivity(objHistorialClinico);
            Toast.makeText(getApplicationContext(),"Bienvenido a su Historial clinico",Toast.LENGTH_LONG).show();

        }
        if(id == R.id.opc_info_paciente){
            Intent objInfoUsuario = new Intent(getApplicationContext(), InformacionUsuario.class);
            startActivity(objInfoUsuario);
            Toast.makeText(getApplicationContext(),"Bienvenido a su Cuenta",Toast.LENGTH_LONG).show();

        }
        return true;
    }

}