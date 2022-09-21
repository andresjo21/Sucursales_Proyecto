package sucursales.presentation.sucursales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel pnaelSucursal;
    private JLabel sucursalLbl;
    private JTextField sucursalFld;
    private JButton buscarFld;
    private JTable sucursalesFld;
    private JButton agregarFld;

    public View() {
        buscarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.buscar(sucursalFld.getText());
            }
        });
    }

    public JPanel getPanelSucursal() {
        return pnaelSucursal;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) { this.controller = controller; }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver((Observer) this);
    }

    @Override
    public void update(Observable o, Object arg) {
        int[] cols = {TableModel.CODIGO, TableModel.NOMBRE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.pnaelSucursal.revalidate();
    }
}
