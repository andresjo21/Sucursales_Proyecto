package sucursales.presentation.acercade;

public class Model extends java.util.Observable{

    public Model() {
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
