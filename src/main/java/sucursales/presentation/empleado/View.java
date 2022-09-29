package sucursales.presentation.empleado;

import sucursales.Application;
import sucursales.logic.Empleado;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import static java.lang.Float.parseFloat;

public class View implements Observer {
    private JPanel panel;
    private JTextField cedulaFld;
    private JTextField nombreFld;
    private JButton guardarFld;
    private JButton cancelarFld;
    private JLabel cedulaLbl;
    private JLabel nombreLbl;
    private JLabel mapaLbl;
    private JTextField telefonoFld;
    private JLabel telefonoLbl;
    private JTextField salarioFld;
    private JTextField sucursalFld;
    private JLabel salarioLbl;
    private JLabel sucursalLbl;
    private JLabel puntoSucursalLbl;

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
                telefonoLbl.setBorder(null);
                telefonoLbl.setToolTipText(null);
                salarioLbl.setBorder(null);
                salarioLbl.setToolTipText(null);
                sucursalLbl.setBorder(null);
                sucursalLbl.setToolTipText(null);
            }
        });
        mapaLbl.setLayout(null);
        mapaLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/mapa.png"));
        //Adapta tamaño de la imagen
        mapaLbl.setIcon(new ImageIcon(((ImageIcon) mapaLbl.getIcon()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));

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
        telefonoFld.setText(current.getTelefono());
        creacionSucursalLbl();
        if(model.getModo() == Application.MODO_EDITAR){
            for(int i = 0; i < model.getSucursalesLista().size(); i++){
                if(current.getSucursal() == model.getSucursalesLista().get(i)){
                    puntoSucursalLbl = (JLabel) mapaLbl.getComponent(i);
                    puntoSucursalLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/SucursalSel.png"));
                    puntoSucursalLbl.setIcon(new ImageIcon(((ImageIcon) puntoSucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                    puntoSucursalLbl.setBounds(model.getSucursalesLista().get(i).getX(), model.getSucursalesLista().get(i).getY(), 30, 30);
                    break;
                }
            }
            salarioFld.setText(String.valueOf(current.getSalario()));
            try{
                sucursalFld.setText(current.getSucursal().getNombre());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }else {
            salarioFld.setText("");
            sucursalFld.setText("");
        }

        this.panel.validate();
    }

    public Empleado take() {
        Empleado e = new Empleado();
        e.setCedula(cedulaFld.getText());
        e.setNombre(nombreFld.getText());
        e.setTelefono(telefonoFld.getText());
        e.setSalario(parseFloat(salarioFld.getText()));
        e.setSucursal(model.getSucursalesLista().stream().filter(s -> s.getCodigo().equals(sucursalFld.getClientProperty("codigo"))).findFirst().get());
        e.setSalarioTotal();
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

        if (telefonoFld.getText().length() == 0) {
            valid = false;
            telefonoLbl.setBorder(Application.BORDER_ERROR);
            telefonoLbl.setToolTipText("Telefono requerido");
        } else {
            telefonoLbl.setBorder(null);
            telefonoLbl.setToolTipText(null);
        }

        if (salarioFld.getText().length() == 0) {
            valid = false;
            salarioLbl.setBorder(Application.BORDER_ERROR);
            salarioLbl.setToolTipText("Salario requerido");
        } else {
            salarioLbl.setBorder(null);
            salarioLbl.setToolTipText(null);
        }

        if (sucursalFld.getText().length() == 0) {
            valid = false;
            sucursalLbl.setBorder(Application.BORDER_ERROR);
            sucursalLbl.setToolTipText("Sucursal requerido");
        } else {
            sucursalLbl.setBorder(null);
            sucursalLbl.setToolTipText(null);
        }

        return valid;
    }

    public void creacionSucursalLbl(){
        mapaLbl.removeAll();
        for(int i = 0; i < model.getSucursalesLista().size(); i++){
            puntoSucursalLbl = new JLabel();
            puntoSucursalLbl.setLayout(null);
            puntoSucursalLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/Sucursal.png"));
            puntoSucursalLbl.setIcon(new ImageIcon(((ImageIcon) puntoSucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            puntoSucursalLbl.setBounds( model.getSucursalesLista().get(i).getX(), model.getSucursalesLista().get(i).getY(),30, 30);
            puntoSucursalLbl.putClientProperty("index", new Integer(i));
               puntoSucursalLbl.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {

                        int index = (Integer) ((JLabel) e.getSource()).getClientProperty("index");
                        sucursalFld.setText(model.getSucursalesLista().get(index).getNombre());
                        sucursalFld.putClientProperty("codigo", model.getSucursalesLista().get(index).getCodigo());


                        for (int i = 0; i < model.getSucursalesLista().size(); i++) {
                            JLabel auxLbl = (JLabel) mapaLbl.getComponents()[i];
                            puntoSucursalLbl = (JLabel) e.getSource();
                            if (auxLbl == puntoSucursalLbl) {
                                puntoSucursalLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/SucursalSel.png"));
                                puntoSucursalLbl.setIcon(new ImageIcon(((ImageIcon) puntoSucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                                puntoSucursalLbl.setBounds(model.getSucursalesLista().get(index).getX(), model.getSucursalesLista().get(index).getY(), 30, 30);
                            } else {
                                auxLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/Sucursal.png"));
                                auxLbl.setIcon(new ImageIcon(((ImageIcon) auxLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                                auxLbl.setBounds(model.getSucursalesLista().get(i).getX(), model.getSucursalesLista().get(i).getY(), 30, 30);
                            }
                        }

                    }
                });

            puntoSucursalLbl.setToolTipText("<html>"+model.getSucursalesLista().get(i).getNombre() + "<br>" + model.getSucursalesLista().get(i).getDireccion()+"<html>");
            mapaLbl.add(puntoSucursalLbl,i);
            mapaLbl.repaint();
        }
    }
}
