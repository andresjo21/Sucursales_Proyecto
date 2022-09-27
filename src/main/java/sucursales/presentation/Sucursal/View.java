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
    private JLabel sucursalLbl;

    private int x;

    private int y;

    public View(){

        guardarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validate()){
                    Sucursales n = take();
                    try{
                        controller.guardar(n);
                        sucursalLbl = null;
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
                codigoLbl.setBorder(null);
                codigoLbl.setToolTipText(null);
                referenciaLbl.setBorder(null);
                referenciaLbl.setToolTipText(null);
                direccionLbl.setBorder(null);
                direccionLbl.setToolTipText(null);
                zonajeLbl.setBorder(null);
                zonajeLbl.setToolTipText(null);
                mapaLbl.setBorder(null);
                mapaLbl.setToolTipText(null);
            }
        });
        mapaLbl.setLayout(null);
        mapaLbl.setIcon(new ImageIcon("../icons/mapa.png"));
        //Adapta tamaño de la imagen
        mapaLbl.setIcon(new ImageIcon(((ImageIcon) mapaLbl.getIcon()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));

        //Action listener de mapaLbl que guarda las coordenadas cuando se haga doble click
        // y agrega la imagen de Sucursal.png en las coordenadas anteriormente guardadas sobre el mapaLbl
        mapaLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                x = evt.getX()-15;
                y = evt.getY()-27;
                //Agrega el label con la imagen de Sucursal en las coordenadas x,y
                creacionSucursalLbl(x,y);
            }
        });

    }

    public JPanel getPanel() {
        return panel;
    }

    public void creacionSucursalLbl(int x, int y){
        if(sucursalLbl == null) sucursalLbl = new JLabel();
        sucursalLbl.setLayout(null);
        sucursalLbl.setIcon(new ImageIcon("../icons/Sucursal.png"));
        sucursalLbl.setIcon(new ImageIcon(((ImageIcon) sucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        sucursalLbl.setBounds(x, y, 30, 30);
        mapaLbl.add(sucursalLbl);
        mapaLbl.repaint();
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
        mapaLbl.removeAll();
        this.codigoFld.setEnabled(model.getModo() == Application.MODO_AGREGAR);
        this.codigoFld.setText(current.getCodigo());
        referenciaFld.setText(current.getNombre());
        direccionFld.setText(current.getDireccion());
        zonajeFld.setText(current.getZonaje());
        if(model.getModo() == Application.MODO_EDITAR) {
            creacionSucursalLbl(current.getX(), current.getY());
            mapaLbl.add(sucursalLbl);
        }
        this.panel.validate();
    }

    public Sucursales take() {
        Sucursales e = new Sucursales();
        e.setCodigo(codigoFld.getText());
        e.setNombre(referenciaFld.getText());
        e.setDireccion(direccionFld.getText());
        e.setZonaje(zonajeFld.getText());
        e.setX(x);
        e.setY(y);
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
        if(sucursalLbl == null){
            valid = false;
            mapaLbl.setBorder(Application.BORDER_ERROR);
            mapaLbl.setToolTipText("Es requerido escoger una zona en el mapa");
        }else{
            mapaLbl.setBorder(null);
            mapaLbl.setToolTipText(null);
        }

        return valid;
    }

    public JLabel getMapaLbl() {
        return mapaLbl;
    }
}
