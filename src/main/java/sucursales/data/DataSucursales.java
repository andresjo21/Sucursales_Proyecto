package sucursales.data;

import sucursales.logic.Sucursales;

import java.util.ArrayList;
import java.util.List;

public class DataSucursales {
    private List<Sucursales> sucursales;

    public DataSucursales() {
        sucursales = new ArrayList<>();

        sucursales.add(new Sucursales("001", "Sabana", "San Jose, 100 O Teletica","1.0"));
        sucursales.add(new Sucursales("002", "Liberia","Guanacaste, Liberia, 250 S Iglesia","2.0"));
        sucursales.add(new Sucursales("003", "Golfito","Puntarenas, Golfito, barrio Huston","4.0"));
    }

    public List<Sucursales> getSucursales() {
        return sucursales;
    }

    public void setSucursales(List<Sucursales> empleados) {
        this.sucursales = empleados;
    }
}
