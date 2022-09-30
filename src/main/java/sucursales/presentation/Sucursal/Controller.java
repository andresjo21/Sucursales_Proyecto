package sucursales.presentation.Sucursal;

import sucursales.Application;
import sucursales.logic.Sucursales;
import sucursales.logic.Service;

import javax.swing.*;
import java.awt.*;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model){
        model.setCurrent(new Sucursales());
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void preAgregar(){
        model.setModo(Application.MODO_AGREGAR);
        model.setCurrent(new Sucursales());
        model.commit();
        this.show();
    }

    JDialog dialog;
    public void show(){
        dialog = new JDialog(Application.window,"Sucursal", true);
        dialog.setSize(700,700);
        dialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        dialog.setContentPane(view.getPanel());
        Point location = Application.window.getLocation();
        dialog.setLocation( location.x+400,location.y+100);
        dialog.setVisible(true);
    }

    public void hide(){dialog.dispose();}

    public void guardar(Sucursales e) throws Exception {
        switch (model.getModo()) {
            case Application.MODO_AGREGAR:
                Service.instance().sucursalAdd(e);
                model.setCurrent(new Sucursales());
                break;
            case Application.MODO_EDITAR:
                Service.instance().sucursalUpdate(e);
                model.setCurrent(e);
                break;
        }
        Application.sucursalesController.buscar("");
        Application.empleadoController.getModel().setSucursalesLista(Service.instance().sucursalesSearch(""));
        model.commit();
    }

    public void editar(Sucursales e){
        model.setModo(Application.MODO_EDITAR);
        model.setCurrent(e);
        model.commit();
        this.show();
    }

}
