package sucursales.presentation.Sucursal;

import sucursales.Application;
import sucursales.logic.Sucursales;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {
    private JTextField codigoFld;
    private JLabel codigoLbl;
    private JTextField referenciaFld;
    private JLabel referenciaLbl;
    private JTextField direccionFld;
    private JLabel direccionLbl;
    private JTextField zonajeFld;
    private JLabel zonajeLbl;
    private JButton guardarBtn;
    private JButton cancelarBtn;
    private JPanel panel;

    public View(){

        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()){
                    Sucursales n = take();
                    try{
                        controller.guardar(n);
                    }catch (Exception ex){
                        JOptionPane.showMessageDialog(panel, ex.getMessage(),"ERROR",JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        cancelarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.hide();
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
        Sucursales current = model.getCurrent();
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        referenciaFld.setText(current.getNombre());
        this.panel.validate();
    }

    public Sucursales take() {
        Sucursales e = new Sucursales();
        e.setCodigo(codigoFld.getText());
        e.setNombre(referenciaFld.getText());
        return e;
    }

    private boolean validate() {
        boolean valid = true;
        if (codigoFld.getText().isEmpty()) {
            valid = false;
            codigoLbl.setBorder(Application.BORDER_ERROR);
            codigoLbl.setToolTipText("Codigo requerido");
        } else {
            codigoLbl.setBorder(null);
            codigoLbl.setToolTipText(null);
        }

        if (referenciaFld.getText().length() == 0) {
            valid = false;
            referenciaFld.setBorder(Application.BORDER_ERROR);
            referenciaFld.setToolTipText("Referencia requerida");
        } else {
            referenciaFld.setBorder(null);
            referenciaFld.setToolTipText(null);
        }

        if (direccionFld.getText().length() == 0) {
            valid = false;
            direccionFld.setBorder(Application.BORDER_ERROR);
            direccionFld.setToolTipText("Direccion requerida");
        } else {
            direccionFld.setBorder(null);
            direccionFld.setToolTipText(null);
        }

        if (zonajeFld.getText().length() == 0) {
            valid = false;
            zonajeFld.setBorder(Application.BORDER_ERROR);
            zonajeFld.setToolTipText("Zonaje requerido");
        } else {
            zonajeFld.setBorder(null);
            zonajeFld.setToolTipText(null);
        }

        return valid;
    }
}
