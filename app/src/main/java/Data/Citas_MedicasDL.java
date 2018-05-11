package Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.edwin.clinicacetu.ClinicaDB;

import java.util.LinkedList;

import Modelos.Citas_Medicas;
import Modelos.Doctores;
import Modelos.Usuarios;

/**
 * Created by Edwin on 07/05/2018.
 */

public class Citas_MedicasDL {
    private ClinicaDB openHelper;
    private SQLiteDatabase database;

    public Citas_MedicasDL(Context cont){
        openHelper = new ClinicaDB(cont);
        database = openHelper.getWritableDatabase();
    }

    public int insertCita(Citas_Medicas pCitas){
        int idCita =0;


        ContentValues objParam;
        objParam= new ContentValues();

        objParam.put("id_empleado_medico",pCitas.getid_empleado_medico());
        objParam.put("id_usuario",pCitas.getid_usuario());
        objParam.put("fecha_cita",pCitas.getFecha());
        objParam.put("comentario",pCitas.getComentario());
        objParam.put("comentario_rechazo","");
        objParam.put("id_estado_cita",pCitas.getid_estado_cita());


        idCita =(int) database.insert("citas_medicas",null,objParam);

        database.close();
        return idCita;
    }

    public LinkedList getAll(){
        Cursor lCur = database.rawQuery("SELECT id, id_empleado_medico, id_usuario, fecha_cita, comentario, id_estado_cita  FROM citas_medicas",null);

        LinkedList objListCitas = new LinkedList();
        Citas_Medicas objCita;

        while(lCur.moveToNext()){
            objCita = new Citas_Medicas();
            objCita.setId(lCur.getInt(0));
            objCita.setid_empleado_medico(lCur.getInt(1));
            objCita.setid_usuario(lCur.getInt(2));
            objCita.setFecha(lCur.getString(3));
            objCita.setComentario(lCur.getString(4));
            objCita.setid_estado_cita(lCur.getInt(5));
            objListCitas.add(objCita);
        }
        database.close();
        return objListCitas;
    }



    public LinkedList getEstadosCitasUsuario(int IdUsuario){
        database = openHelper.getWritableDatabase();

        String strSql ="Select u.Nombre Usuario, c.Fecha_cita FechaCita, m.Nombre Medico,  " +
                "c.comentario Padecimiento, " +
                "case when c.id_estado_cita=0 then 'PENDIENTE' when c.id_estado_cita=2 then 'APROBADA' else 'DENEGADA' end EstadoCita, " +
                "case when c.id_estado_cita=3 then c.comentario_rechazo else 'n/a' end ComentarioRechazo " +
                "from citas_medicas c inner join usuarios u on c.id_usuario = u.id " +
                "inner join usuarios m on c.id_empleado_medico = m.id "+
                " where c.id_usuario="+ IdUsuario +
                " order by c.id desc ";

        String lstrDatos="";




        Cursor lCur = database.rawQuery(strSql ,null);

        LinkedList objListCitas = new LinkedList();
        while(lCur.moveToNext()){

            lstrDatos ="Usuario: " + lCur.getString(0) + "\n" ;
            lstrDatos += "Fecha y Hora de Cita: "+ lCur.getString(1) + "\n" ;
            lstrDatos +="Médico: "+ lCur.getString(2) + "\n" ;
            lstrDatos +="Padecimiento: "+ lCur.getString(3) + "\n" ;
            lstrDatos +="Estado de la Cita: "+ lCur.getString(4) + "\n" ;
            lstrDatos +="Comentario Aprobación: "+ lCur.getString(5) + "\n" ;
            lstrDatos += "Culquier consulta o duda llamar al: 2235-9115";

            objListCitas.add(lstrDatos);
        }
        database.close();
        return objListCitas;
    }


}
