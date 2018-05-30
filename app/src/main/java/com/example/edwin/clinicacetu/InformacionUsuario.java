package com.example.edwin.clinicacetu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.LinkedList;

import Modelos.Usuarios;
import Negocios.Citas_Medicas_BL;
import Negocios.UsuariosBL;

public class InformacionUsuario extends AppCompatActivity {

    private Usuarios objUsuario;
    private UsuariosBL objUsuariosBL;
    LinkedList listaSource;
    ListView listaEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_usuario);
        objUsuario = (Usuarios) getIntent().getExtras().getSerializable("DatosUsuario");

        listaEstado=(ListView)findViewById(R.id.lstinfoUser);
        objUsuario =(Usuarios)getIntent().getExtras().getSerializable("DatosUsuario");

        UsuariosBL objInfoUsuario =new UsuariosBL(getApplicationContext());
        listaSource = objInfoUsuario.getInfoUsuario(objUsuario.getID());
        // setListAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listaSource));
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaSource);
        listaEstado.setAdapter(adaptador);
    }

    }

