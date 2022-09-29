package sucursales.presentation.acercade;

import sucursales.presentation.empleados.Controller;
import sucursales.presentation.empleados.Model;
import sucursales.presentation.empleados.TableModel;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel nombreFld;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {

    }

    public JPanel getPanel() {
        return panel;
    }

    public void setVisible(boolean b) {
    }

    public void setController(sucursales.presentation.acercade.Controller controller) {
    }
}
