package sucursales.logic;

import sucursales.Application;

public class Sucursales {
    String codigo;
    String nombre;


    public Sucursales(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Sucursales() {
        this("","");
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
