package Negocios;

import android.content.Context;

import java.util.LinkedList;

import Data.UsuariosDL;
import Modelos.Usuarios;

/**
 * Created by Edwin on 03/05/2018.
 */

public class UsuariosBL {
    private Usuarios objUsuario;
    private UsuariosDL objUsuarioDL;


    public  UsuariosBL(Context cont){

        objUsuarioDL = new UsuariosDL(cont);
    };

    public UsuariosBL(Context cont,Usuarios pUsuario){
        objUsuario = pUsuario;
        objUsuarioDL = new UsuariosDL(cont);
    }

    public int insertUsuario(){

        return  objUsuarioDL.insertUsuario(objUsuario);
    }

    public LinkedList getAll(){
        return objUsuarioDL.getAll();
    }

    public LinkedList getDoctoresSpinner(){
        return objUsuarioDL.getDoctoresSpinner();
    }

    public LinkedList getByID(){ return objUsuarioDL.getByID(objUsuario); }
    public LinkedList getByUSUARIO(){ return objUsuarioDL.getByUsuario(objUsuario); }
 /*   public int actualizarPassword(byte[] password){
        return objUsuarioDL.actualizarPassword(password,objUsuario);
    }  */
}
