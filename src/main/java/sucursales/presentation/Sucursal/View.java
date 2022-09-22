package sucursales.presentation.Sucursal;

import sucursales.Application;
import sucursales.logic.Sucursales;

import javax.swing.*;
import java.awt.*;
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
    private JLabel mapaLbl;

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
        mapaLbl.setIcon(new ImageIcon("D:\\Andres Docs\\CLASES\\Progra 3\\Proyecto\\icons\\mapa.png"));
        //hacer mas pequeño el icono que contiene el mapaLbl
        mapaLbl.setIcon(new ImageIcon(((ImageIcon) mapaLbl.getIcon()).getImage().getScaledInstance(330, 330, Image.SCALE_SMOOTH)));
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
        direccionFld.setText(current.getDireccion());
        zonajeFld.setText(current.getZonaje());
        this.panel.validate();
    }

    public Sucursales take() {
        Sucursales e = new Sucursales();
        e.setCodigo(codigoFld.getText());
        e.setNombre(referenciaFld.getText());
        e.setDireccion(direccionFld.getText());
        e.setZonaje(zonajeFld.getText());
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
            referenciaLbl.setBorder(Application.BORDER_ERROR);
            referenciaLbl.setToolTipText("Referencia requerida");
        } else {
            referenciaLbl.setBorder(null);
            referenciaLbl.setToolTipText(null);
        }

        if (direccionFld.getText().length() == 0) {
            valid = false;
            direccionLbl.setBorder(Application.BORDER_ERROR);
            direccionLbl.setToolTipText("Direccion requerida");
        } else {
            direccionLbl.setBorder(null);
            direccionLbl.setToolTipText(null);
        }

        if (zonajeFld.getText().length() == 0) {
            valid = false;
            zonajeLbl.setBorder(Application.BORDER_ERROR);
            zonajeLbl.setToolTipText("Zonaje requerido");
        } else {
            zonajeLbl.setBorder(null);
            zonajeLbl.setToolTipText(null);
        }

        return valid;
    }

    public JLabel getMapaLbl() {
        return mapaLbl;
    }
}
