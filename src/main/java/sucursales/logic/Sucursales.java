package sucursales.logic;

import sucursales.Application;

public class Sucursales {
    String codigo;
    String nombre;
    String direccion;
    String zonaje;
    int x;
    int y;


    public Sucursales(String codigo, String nombre, String direccion, String zonaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zonaje = zonaje;
    }

    public Sucursales() {
        this("","","","");
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

    public String getDireccion() {
        return direccion;
    }

    public String getZonaje() {
        return zonaje;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setZonaje(String zonaje) {
        this.zonaje = zonaje;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
