package sucursales.data;

import sucursales.logic.Empleado;

import java.security.Provider;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Empleado> empleados;

    public Data() {
        empleados = new ArrayList<>();

        empleados.add(new Empleado("111", "Franklin Chang","78872356",7500, "001", 7600));
        empleados.add(new Empleado("222", "Sandra Cauffman", "54647656",8500,"002",8600));
        empleados.add(new Empleado("333", "Ivan Vargas","68794003",5000,"003",8600));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
