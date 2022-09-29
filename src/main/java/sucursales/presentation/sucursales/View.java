package sucursales.presentation.sucursales;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

public class View implements Observer {

    private JPanel pnaelSucursal;
    private JLabel sucursalLbl;
    private JTextField sucursalFld;
    private JButton buscarFld;
    private JTable sucursalesFld;
    private JButton agregarFld;
    private JButton borrarBtn;
    private JLabel mapaLbl;
    private JButton pdfButton;
    private JLabel puntoSucursalLbl;

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
                if(e.getClickCount() == 1){
                   for(int i = 0; i < model.getSucursales().size(); i++){
                       mapaLbl.getComponent(i).setVisible(true);
                        if(i == sucursalesFld.getSelectedRow()){
                            mapaLbl.getComponent(i).setVisible(false);
                            puntoSucursalLbl = new JLabel();
                            puntoSucursalLbl.setLayout(null);
                            puntoSucursalLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/SucursalSel.png"));
                            puntoSucursalLbl.setIcon(new ImageIcon(((ImageIcon) puntoSucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
                            puntoSucursalLbl.setBounds(model.getSucursales().get(i).getX(), model.getSucursales().get(i).getY(),30,30);
                            puntoSucursalLbl.setToolTipText("<html>"+model.getSucursales().get(i).getNombre() + "<br>" + model.getSucursales().get(i).getDireccion()+"<html>");
                            mapaLbl.add(puntoSucursalLbl);
                            mapaLbl.repaint();
                        }
                    }
                }

            }
        });


        mapaLbl.setLayout(null);
        mapaLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/mapa.png"));
        //Adapta tamaño de la imagen
        mapaLbl.setIcon(new ImageIcon(((ImageIcon) mapaLbl.getIcon()).getImage().getScaledInstance(400, 400, Image.SCALE_SMOOTH)));

        borrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = sucursalesFld.getSelectedRow();
                controller.borrar(row);
            }
        });
        pdfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.imprimir();
                    if (Desktop.isDesktopSupported()) {
                        File myFile = new File("sucursales.pdf");
                        Desktop.getDesktop().open(myFile);
                    }
                } catch (Exception ex) { }
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
        int[] cols = {TableModel.CODIGO, TableModel.REFERENCIA, TableModel.DIRECCION, TableModel.ZONAJE};
        sucursalesFld.setModel(new TableModel(cols, model.getSucursales()));
        sucursalesFld.setRowHeight(30);
        sucursalesFld.getColumnModel().getColumn(2).setPreferredWidth(220);
        this.pnaelSucursal.revalidate();
        creacionSucursalLbl();
    }

    public void creacionSucursalLbl(){
        mapaLbl.removeAll();
        for(int i = 0; i < model.getSucursales().size(); i++){
            puntoSucursalLbl = new JLabel();
            puntoSucursalLbl.setLayout(null);
            puntoSucursalLbl.setIcon(new ImageIcon("./src/main/resources/sucursales/presentation/icons/Sucursal.png"));
            puntoSucursalLbl.setIcon(new ImageIcon(((ImageIcon) puntoSucursalLbl.getIcon()).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
            puntoSucursalLbl.setBounds( model.getSucursales().get(i).getX(), model.getSucursales().get(i).getY(),30, 30);
            puntoSucursalLbl.setToolTipText("<html>"+model.getSucursales().get(i).getNombre() + "<br>" + model.getSucursales().get(i).getDireccion()+"<html>");
            mapaLbl.add(puntoSucursalLbl,i);
            mapaLbl.repaint();
        }

    }
}
