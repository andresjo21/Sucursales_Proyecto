package sucursales.presentation.sucursales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
        agregarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.preAgregar();
            }
        });

        sucursalesFld.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = sucursalesFld.getSelectedRow();
                    controller.editar(row);
                }
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
        model.addObserver(this);
    }

    @Override
    public void update(Observable updateModel, Object parametros) {
        int[] cols = {TableModel.CODIGO, TableModel.NOMBRE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        this.pnaelSucursal.revalidate();
    }
}
