package com.example.edwin.clinicacetu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.LinkedList;

import Modelos.Usuarios;
import Negocios.UsuariosBL;

public class InformacionUsuario extends AppCompatActivity {
    private Usuarios objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_usuario);
        objUsuario = (Usuarios) getIntent().getExtras().getSerializable("DatosUsuario");

    }
}
