package sucursales.presentation.sucursales;

import sucursales.logic.Sucursales;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observer;

public class Model extends java.util.Observable{
    List<Sucursales> sucursales;

    public Model() {
        this.setSucursales(new ArrayList<Sucursales>());
    }

    public void setSucursales(List<Sucursales> sucursales){
        this.sucursales = sucursales;
    }

    public List<Sucursales> getSucursales() {
        return sucursales;
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
        commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
