package sucursales.logic;

public class Empleado {
    String cedula;
    String nombre;


    public Empleado(String cedula, String nombre) {
        this.cedula = cedula;
        this.nombre = nombre;
    }

    public Empleado() {
        this("","");
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
