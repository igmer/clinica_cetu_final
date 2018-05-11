package Modelos;

/**
 * Created by Edwin on 07/05/2018.
 */

public class Citas_Medicas {
    private String Fecha, Comentario;
    private int Id, id_empleado_medico, id_usuario,id_estado_cita;

    public String getFecha(){
        return Fecha;
    }
    public void setFecha(String Fh){
        this.Fecha= Fh;
    }

    public String getComentario(){
        return Comentario;
    }
    public void setComentario(String comentario){
        this.Comentario= comentario;
    }

    public int getId(){
        return Id;
    }
    public void setId(int id_){
        this.Id= id_;
    }

    public int getid_empleado_medico(){
        return id_empleado_medico;
    }
    public void setid_empleado_medico(int id_){
        this.id_empleado_medico= id_;
    }

    public int getid_usuario(){
        return id_usuario;
    }
    public void setid_usuario(int id_){
        this.id_usuario= id_;
    }

    public int getid_estado_cita(){
        return id_estado_cita;
    }
    public void setid_estado_cita(int id_){
        this.id_estado_cita= id_;
    }

}
