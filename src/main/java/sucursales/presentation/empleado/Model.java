package sucursales.presentation.empleado;

import sucursales.logic.Empleado;

public class Model extends java.util.Observable{
    Empleado current;
    int modo;

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
}
