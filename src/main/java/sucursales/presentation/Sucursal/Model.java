package sucursales.presentation.Sucursal;

import sucursales.logic.Sucursales;

public class Model extends java.util.Observable{
    Sucursales current;
    int modo;

    public Model(){
    }

    public int getModo() {return modo;}

    public void setModo(int modo){this.modo = modo;}

    public Sucursales getCurrent(){ return current; }
    public void setCurrent(Sucursales current){ this.current = current; }

    @Override
    public void addObserver(java.util.Observer o){
        super.addObserver(o);
        this.commit();
    }

    public void commit(){
        setChanged();
        notifyObservers(null);
    }
}
