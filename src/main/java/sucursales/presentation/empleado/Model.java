package sucursales.presentation.empleado;

import sucursales.logic.Empleado;
import sucursales.logic.Sucursales;

import java.util.List;

public class Model extends java.util.Observable{
    Empleado current;
    int modo;
    List<Sucursales> sucursalesLista;

    public Model() {
    }

    public int getModo() {
        return modo;
    }

    public void setModo(int modo) {
        this.modo = modo;
    }

    public Empleado getCurrent() {
        return current;
    }

    public void setCurrent(Empleado current) {
        this.current = current;
    }

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }


    public List<Sucursales> getSucursalesLista() {
        return sucursalesLista;
    }

    public void setSucursalesLista(List<Sucursales> sucursalesLista) {
        this.sucursalesLista = sucursalesLista;
    }
}
