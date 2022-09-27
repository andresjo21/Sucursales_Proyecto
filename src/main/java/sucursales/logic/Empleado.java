package sucursales.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
    String cedula;
    String nombre;
    String telefono;
    double salario;

    @XmlIDREF
    Sucursales sucursal;
    double salarioTotal;


    public Empleado(String cedula, String nombre, String telefono, double salario, Sucursales sucursalId ,double salarioTotal) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario = salario;
        this.sucursal = sucursalId;
        this.salarioTotal = salarioTotal;
    }

    public Empleado() {
        this("","","",0.0,null,0.0);
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

    public String getTelefono() {
        return telefono;
    }

    public double getSalario() {
        return salario;
    }
    public Sucursales getSucursal() {
        return sucursal;
    }

    public double getSalarioTotal() {
        return salarioTotal;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public void setSalarioTotal(double salarioTotal) {
        this.salarioTotal = salarioTotal;
    }
}
