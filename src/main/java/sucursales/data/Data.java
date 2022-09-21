package sucursales.data;

import sucursales.logic.Empleado;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Empleado> empleados;

    public Data() {
        empleados = new ArrayList<>();

        empleados.add(new Empleado("111", "Franklin Chang"));
        empleados.add(new Empleado("222", "Sandra Cauffman"));
        empleados.add(new Empleado("333", "Ivan Vargas"));
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }
}
