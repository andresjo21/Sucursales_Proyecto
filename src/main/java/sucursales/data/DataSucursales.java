package sucursales.data;

import sucursales.logic.Sucursales;

import java.util.ArrayList;
import java.util.List;

public class DataSucursales {
    private List<Sucursales> sucursales;

    public DataSucursales() {
        sucursales = new ArrayList<>();

        sucursales.add(new Sucursales("11", "San Jose"));
        sucursales.add(new Sucursales("22", "Heredia"));
        sucursales.add(new Sucursales("33", "Alajuela"));
    }

    public List<Sucursales> getSucursales() {
        return sucursales;
    }

    public void setEmpleados(List<Sucursales> empleados) {
        this.sucursales = empleados;
    }
}
