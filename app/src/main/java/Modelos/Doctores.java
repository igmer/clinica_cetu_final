package Modelos;

/**
 * Created by Edwin on 07/05/2018.
 */

public class Doctores {

    private String nombre;
    private  int Id;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public void setId(int idDoctor) {
        this.Id = idDoctor;
    }

    public Doctores(int id_docto,String Nombre){
        nombre=Nombre;
        Id=id_docto;
    }

    @Override
    public String toString() {
        return nombre;
    }

   /* public int getId() {
        return especialidad;
    } */
}
