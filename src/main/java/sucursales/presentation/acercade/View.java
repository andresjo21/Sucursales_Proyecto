package sucursales.presentation.acercade;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    Controller controller;
    Model model;
    private JPanel panel;
    private JLabel nombreLbl;
    private JLabel logoLbl;

    public View() {
        logoLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/log.jpg"));
        logoLbl.setIcon(new ImageIcon(((ImageIcon) logoLbl.getIcon()).getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH)));
    }

    public void setController(sucursales.presentation.acercade.Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        this.panel.revalidate();
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setVisible(boolean b) {
    }

    //public void setController(sucursales.presentation.acercade.Controller controller) {
    //}
}
