package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;

import javax.swing.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JPanel panel;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JButton guardarFld;
    private JButton cancelarFld;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;

    public View() {
        guardarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validate()) {
                    Empleado n = take();
                    try {
                        controller.guardar(n);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarFld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
                nombreLbl.setBorder(null);
                nombreLbl.setToolTipText(null);
                cedulaLbl.setBorder(null);
                cedulaLbl.setToolTipText(null);
            }
        });
    }

    public JPanel getPanel() {
        return panel;
    }

    Controller controller;
    Model model;

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setModel(Model model) {
        this.model = model;
        model.addObserver(this);
    }

    @Override
    public void update(Observable updatedModel, Object parametros) {
        Empleado current = model.getCurrent();
        this.cedulaFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.cedulaFld.setText(current.getCedula());
        nombreFld.setText(current.getNombre());
        this.panel.validate();
    }

    public Empleado take() {
        Empleado e = new Empleado();
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (cedulaFld.getText().isEmpty()) {
            valid = false;
            cedulaLbl.setBorder(Application.BORDER_ERROR);
            cedulaLbl.setToolTipText("Id requerido");
        } else {
            cedulaLbl.setBorder(null);
            cedulaLbl.setToolTipText(null);
        }

        if (nombreFld.getText().length() == 0) {
            valid = false;
            nombreLbl.setBorder(Application.BORDER_ERROR);
            nombreLbl.setToolTipText("Nombre requerido");
        } else {
            nombreLbl.setBorder(null);
            nombreLbl.setToolTipText(null);
        }
        return valid;
    }
}
