package Negocios;

import android.content.Context;
import android.database.Cursor;

import java.util.LinkedList;

import Data.Citas_MedicasDL;
import Data.UsuariosDL;
import Modelos.Citas_Medicas;
import Modelos.Usuarios;

/**
 * Created by Edwin on 07/05/2018.
 */


public class Citas_Medicas_BL {
    private Citas_Medicas objCitas;
    private Citas_MedicasDL objCitasDL;


    public  Citas_Medicas_BL(Context cont){

        objCitasDL = new Citas_MedicasDL(cont);
    };

    public Citas_Medicas_BL(Context cont,Citas_Medicas pCitas){
        objCitas = pCitas;
        objCitasDL = new Citas_MedicasDL(cont);
    }

    public int insertCita(Citas_Medicas pCitaPaciente){

        return  objCitasDL.insertCita(pCitaPaciente);
    }

    public LinkedList getEstadosCitasUsuario(int IdUsuario){
        return objCitasDL.getEstadosCitasUsuario(IdUsuario);
    }


}