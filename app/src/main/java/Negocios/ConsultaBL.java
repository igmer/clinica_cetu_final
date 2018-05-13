package Negocios;

import android.content.Context;

import java.util.LinkedList;

import Data.ConsultaDL;
import Modelos.Consultas;

/**
 * Created by desarrollo on 05-12-18.
 */

public class ConsultaBL {
    private Consultas objConsultas;
    private ConsultaDL objConsultasDL;

    public  ConsultaBL(Context cont){

        objConsultasDL = new ConsultaDL(cont);
    }
    public LinkedList getByID(){ return objConsultasDL.getByID(objConsultas); }
}
