package com.example.edwin.clinicacetu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.LinkedList;

import Modelos.Usuarios;
import Negocios.ConsultaBL;

public class historialMedico extends AppCompatActivity {
    ListView lwHistoral;
    LinkedList listado;
    ConsultaBL objConsulta;
    Usuarios objUsuario;
    LinkedList listaSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial_medico);
        lwHistoral = (ListView)findViewById(R.id.lwHistorial);
        cargarListado();

    }
    private void cargarListado(){
        listado = listaPersonas();
        MyAdapter myAdapter = new MyAdapter(getApplicationContext(),R.layout.list_item,listado);
        lwHistoral.setAdapter(myAdapter);

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,listado);
        //lwHistoral.setAdapter(adapter);

    }
    public LinkedList listaPersonas(){
        /*
        ArrayList<String> datos = new ArrayList<>();
        ClinicaDB helper = new ClinicaDB(getApplicationContext());
        SQLiteDatabase db = helper.getReadableDatabase();
        String historial="SELECT * FROM consultaMedica";
        Cursor c = db.rawQuery(historial, null);
        if (c.moveToFirst()){
            do {
                String linea  = "Diagnostico: "+c.getString(4)+" \nTratamiento: "+c.getString(5);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();

        return datos;*/
        ConsultaBL objConsulta =new ConsultaBL(getApplicationContext());
        listaSource = objConsulta.getConsultaUsuario();
        return  listaSource;



    }
}
