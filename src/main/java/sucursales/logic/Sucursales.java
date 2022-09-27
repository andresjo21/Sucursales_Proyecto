package sucursales.logic;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
public class Sucursales {

    @XmlID
    private String codigo;

    private String nombre;

    private String direccion;

    private float zonaje;

    int x;

    int y;


    public Sucursales(String codigo, String nombre, String direccion, float zonaje) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.direccion = direccion;
        this.zonaje = zonaje;
    }

    public Sucursales() {
        this("","","",0);
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

    public float getZonaje() {
        return zonaje;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setZonaje(float zonaje) {
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
