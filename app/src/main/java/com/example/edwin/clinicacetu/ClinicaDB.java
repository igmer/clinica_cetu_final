package com.example.edwin.clinicacetu;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Edwin on 29/04/2018.
 */

public class ClinicaDB extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "DBClinicaCETU";
    public static final int DATABASE_VERSION = 8;


    String sqlCreateTableUsuarios = "Create Table Usuarios (id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "nombre TEXT, direccion TEXT, telefono TEXT,FechaNac DATETIME DEFAULT CURRENT_TIMESTAM, email TEXT, usuario TEXT, password TEXT, activo INTEGER, IdTipo integer)";

    String sqlCreateCitas="create table citas_medicas (id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + "id_empleado_medico INTEGER, id_usuario INTEGER, fecha_cita DATETIME DEFAULT CURRENT_TIMESTAM, comentario TEXT, comentario_rechazo TEXT,id_estado_cita INTEGER, "
            + " FOREIGN KEY(id_usuario) REFERENCES Usuarios(id))";

    String sqlCretaeConsultaMedica="create table consultaMedica (id INTEGER PRIMARY KEY AUTOINCREMENT, idCita integer NOT NULL, peso DOUBLE, talla DOUBLE, diagnostico TEXT," +
            " tratamiento TEXT, fecha TEXT, FOREIGN KEY(idCita)REFERENCES citas_medicas(id))";

    public ClinicaDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Método ejecutado automáticamente si la base de datos no existe
        db.execSQL(sqlCreateTableUsuarios);
        db.execSQL(sqlCreateCitas);
        db.execSQL(sqlCretaeConsultaMedica);
        poblarDB(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Método ejecutado si el numero de newVersion es SUPERIOR a oldVersion
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("DROP TABLE IF EXISTS citas_medicas");
        db.execSQL("DROP TABLE IF EXISTS consultaMedica");

        this.onCreate(db);

    }
    public void poblarDB(SQLiteDatabase db)
    {
        // Procedimiento para inicializar con algunos registros la DB
        db.execSQL("INSERT INTO Usuarios VALUES (1,'Edwin Lemus','','','1998-01-01 10:00:00','albertolemus87@gmai.com','elemus','123',1,1)");
        db.execSQL("INSERT INTO Usuarios VALUES (2,'Juan Jose Torres','','','1998-01-01 10:00:00','arcana_87@hotmail.com','juan','1234x',1,1)");
        db.execSQL("INSERT INTO Usuarios VALUES (4,'Igmer Alirio','','','1998-01-01 10:00:00','aguardado@nexuserp.coom','carlos','456',1,1)");

        db.execSQL("INSERT INTO Usuarios VALUES (5,'Juan Jose Tovar','','','1968-01-01 10:00:00','medico@hospital.com','','456',1,3)");
        db.execSQL("INSERT INTO Usuarios VALUES (6,'Satigo Lopez','','','1958-01-01 10:00:00','medido2@hospital.com','','456',1,3)");


        db.execSQL("INSERT INTO citas_medicas VALUES (1,5,1,'2018-04-01 10:00:00','DOLOR DE ESTOMAGO FUERTE','',0)");
        db.execSQL("INSERT INTO citas_medicas VALUES (2,6,2,'2018-04-30 10:00:00','sintomas de chick','',1)");
        db.execSQL("INSERT INTO citas_medicas VALUES (3,5,1,'2018-04-30 10:00:00','sintomas FIEBRE','',2)");
        db.execSQL("INSERT INTO consultaMedica VALUES (1,1,53.1,170.3,'Diarrea','Simeticona 1 tab c/12 hrs','2018-05-01 09:00:00')");
        db.execSQL("INSERT INTO consultaMedica VALUES (2,2,53.5,170.3,'Gripe','Paracetamol 1 tab. c/8 hrs','2018-05-01 09:00:00')");
        db.execSQL("INSERT INTO consultaMedica VALUES (3,3,53.9,170.3,'Desnutricion','Dieta, Carbohidrato, Proteinas y Hierro','2018-05-01 09:00:00')");
        db.execSQL("INSERT INTO consultaMedica VALUES (4,3,52.1,170.3,'Sospecha de Chik','LOA + Paracetamon','2018-05-01 09:00:00')");
    }

    public Integer validaIngresousuario(String elUsuario, String laClave){
        //Se obtiene referencia a base de datos para relizar consultas
        SQLiteDatabase db = getReadableDatabase();
        //Se realiza consulta y el resultado es almacenado en un objeto cursor
       String sqlQUERY="select usuario,password from Usuarios where activo=1 and  usuario='"+elUsuario+"'";
        sqlQUERY+=" and password ='"+laClave +"'";


        Cursor cursor = db.rawQuery(sqlQUERY,null);

        int i=0,i2=0;
        i2= cursor.getCount();
        if (cursor.moveToFirst())
        {
            do
            {
                //Recorremos el cursor para llenar los arreglos que contienen
                //el codigo y el nombre de los continentes
                cursor.getInt(0);
                i++;
            } while (cursor.moveToNext());
        }
        db.close();
        return i;
    }
}
