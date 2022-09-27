package sucursales.data;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import sucursales.logic.Empleado;
import sucursales.logic.Sucursales;

import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    private List<Empleado> empleados;
    private List<Sucursales> sucursales;

    public Data() {
        empleados = new ArrayList<>();
        sucursales = new ArrayList<>();
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public List<Sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursales> sucursales) {
        this.sucursales = sucursales;
    }


}
