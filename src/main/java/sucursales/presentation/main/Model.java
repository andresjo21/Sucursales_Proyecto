/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sucursales.presentation.main;

import java.util.Observable;

public class Model extends Observable{

    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        commit();
    }
    
    public Model() {

    }

    public void commit(){
        setChanged();
        notifyObservers(null);            
    }

}
