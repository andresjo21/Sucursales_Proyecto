package sucursales.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
public class Empleado {
    String cedula;
    String nombre;
    String telefono;
    float salario;

    @XmlIDREF
    Sucursales sucursal;
    float salarioTotal;


    public Empleado(String cedula, String nombre, String telefono, float salario, Sucursales sucursalId) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        this.salario = salario;
        this.sucursal = sucursalId;
        //setSalarioTotal(salario);
    }

    public Empleado() {
        this("","","",0,null);
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

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public void setSucursal(Sucursales sucursal) {
        this.sucursal = sucursal;
    }

    public void setSalarioTotal() {
        if (sucursal != null) {
            this.salarioTotal = salario + (salario * (sucursal.getZonaje() / 100));
        } else {
            this.salarioTotal = salario;
        }
    }
}
