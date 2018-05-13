package Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edwin.clinicacetu.ClinicaDB;

import java.util.LinkedList;

import Modelos.Consultas;
import Modelos.Usuarios;

/**
 * Created by desarrollo on 05-12-18.
 */

public class ConsultaDL {
    private ClinicaDB openHelper;
    private SQLiteDatabase database;
    public ConsultaDL(Context context){
        openHelper = new ClinicaDB(context);
        database = openHelper.getWritableDatabase();


    }



    public LinkedList getByID(Consultas objConsultas) {
        Cursor lCur = database.rawQuery("SELECT id, nombre, direccion, telefono, FechaNac, email, usuario, password, activo FROM Usuarios where email='" + objConsultas.getId_cita() + "'" ,null);

        LinkedList objListUsuarios = new LinkedList();
        Usuarios objUsuario;

        while(lCur.moveToNext()){
            objUsuario = new Usuarios();
            objUsuario.setID(lCur.getInt(0));
            objUsuario.setNombres(lCur.getString(1));
            objUsuario.setDireccion(lCur.getString(2));
            objUsuario.setTelefono(lCur.getString(3));
            objUsuario.setFechaNacimiento(lCur.getString(4));
            objUsuario.setCorreoElectronico(lCur.getString(5));
            objUsuario.setUsuario(lCur.getString(6));
            objUsuario.setPassword(lCur.getString(7));
            objUsuario.setActivo(lCur.getInt(8));
            objListUsuarios.add(objUsuario);
        }
        database.close();
        return objListUsuarios;
    }
}
