package com.example.edwin.clinicacetu;

import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

import Modelos.Usuarios;
import Negocios.Citas_Medicas_BL;

public class EstadosCitas extends AppCompatActivity {
    private Usuarios objUsuario;
    private Citas_Medicas_BL objCitasBL;
    LinkedList listaSource;
    ListView listaEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estados_citas);

        listaEstado=(ListView)findViewById(R.id.lstEstados);
        objUsuario =(Usuarios)getIntent().getExtras().getSerializable("DatosUsuario");

        Citas_Medicas_BL objEstadosCitas =new Citas_Medicas_BL(getApplicationContext());
        listaSource = objEstadosCitas.getEstadosCitasUsuario(objUsuario.getID());
       // setListAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listaSource));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaSource);
        listaEstado.setAdapter(adaptador);
    }
}
