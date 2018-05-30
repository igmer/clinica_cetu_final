package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edwin.clinicacetu.ClinicaDB;

import java.util.LinkedList;

import Modelos.Doctores;
import Modelos.Usuarios;

/**
 * Created by Edwin on 03/05/2018.
 */

public class UsuariosDL {
    private ClinicaDB openHelper;
    private SQLiteDatabase database;

    public UsuariosDL(Context cont){
        openHelper = new ClinicaDB(cont);
        database = openHelper.getWritableDatabase();
    }

    public int insertUsuario(Usuarios pUsuario){
        int idUsuario =0;


        ContentValues objParam;
        objParam= new ContentValues();
        //objParam.put("Id",pUsuario.getNombres());
        objParam.put("Nombre",pUsuario.getNombres().toUpperCase());
        objParam.put("Direccion",pUsuario.getDireccion());
        objParam.put("Telefono",pUsuario.getTelefono());
        objParam.put("FechaNac",pUsuario.getFechaNacimiento());
        objParam.put("email",pUsuario.getCorreoElectronico().toLowerCase());
        objParam.put("usuario",pUsuario.getUsuario().toLowerCase());
        objParam.put("password",pUsuario.getPassword().toLowerCase());
        objParam.put("activo",pUsuario.getActivo());
        objParam.put("IdTipo",1);

        idUsuario =(int) database.insert("Usuarios",null,objParam);

        database.close();
        return idUsuario;
    }

    public LinkedList getAll(){
        Cursor lCur = database.rawQuery("SELECT id, nombre, direccion, telefono, FechaNac, email, usuario, password, activo, idtipo FROM Usuarios",null);

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
            objUsuario.setIdTipo(lCur.getInt(9));
            objListUsuarios.add(objUsuario);
        }
        database.close();
        return objListUsuarios;
    }

    // llamo la lista de los usuarios catalogados como doctores
    public LinkedList getDoctoresSpinner(){
        Cursor lCur = database.rawQuery("SELECT id, nombre FROM Usuarios WHERE Activo=1 and IdTipo=3 ORDER BY nombre ASC",null);

        LinkedList objListaDoctores = new LinkedList();

        while(lCur.moveToNext()){
            objListaDoctores.add(new Doctores(lCur.getInt(0), lCur.getString(1)));
        }
        database.close();
        return objListaDoctores;
    }

    public LinkedList getByID(Usuarios pUsuario){
        Cursor lCur = database.rawQuery("SELECT id, nombre, direccion, telefono, FechaNac, email, usuario, password, activo, IdTipo FROM Usuarios where email='" + pUsuario.getCorreoElectronico() + "'" ,null);

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
            objUsuario.setIdTipo(lCur.getInt(9));
            objListUsuarios.add(objUsuario);
        }
        database.close();
        return objListUsuarios;
    }

    public LinkedList getByUsuario(Usuarios pUsuario){
        Cursor lCur = database.rawQuery("SELECT id, nombre, direccion, telefono, FechaNac, email, usuario, password, activo, IdTipo FROM Usuarios where usuario='" + pUsuario.getUsuario() + "'" ,null);

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
            objUsuario.setIdTipo(lCur.getInt(9));
            objListUsuarios.add(objUsuario);
        }
        database.close();
        return objListUsuarios;
    }

    public LinkedList getInfoUsuario(int IdUsuario){
        database = openHelper.getWritableDatabase();

        String strSql ="Select nombre, telefono, direccion, fechaNac, email from usuarios where id="+ IdUsuario
                ;

        String lstrDatos="";




        Cursor lCur = database.rawQuery(strSql ,null);

        LinkedList objListCitas = new LinkedList();
        while(lCur.moveToNext()){

            lstrDatos ="Nombre: " + lCur.getString(0) + "\n" ;
            lstrDatos += "Telefono: "+ lCur.getString(1) + "\n" ;
            lstrDatos +="Direccion: "+ lCur.getString(2) + "\n" ;
            lstrDatos +="Fecha de Nacimiento: "+ lCur.getString(3) + "\n" ;
            lstrDatos +="Email: "+ lCur.getString(4) + "\n" ;

            objListCitas.add(lstrDatos);
        }
        database.close();
        return objListCitas;
    }

}
