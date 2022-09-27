package sucursales.presentation.empleados;

import sucursales.Application;
import sucursales.logic.Empleado;
import sucursales.logic.Service;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.setEmpleados(Service.instance().empleadosSearch(""));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void buscar(String filtro){
        List<Empleado> rows = Service.instance().empleadosSearch(filtro);
        model.setEmpleados(rows);
        model.commit();
    }

    public void preAgregar(){
        Application.empleadoController.preAgregar();
    }

    public void editar(int row){
        String cedula = model.getEmpleados().get(row).getCedula();
        Empleado e=null;
        try {
            e= Service.instance().empleadoGet(cedula);
            Application.empleadoController.editar(e);
        } catch (Exception ex) {}
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

    public void borrar(int row){
        String cedula = model.getEmpleados().get(row).getCedula();
        Empleado e=null;
        try {
            e= Service.instance().empleadoGet(cedula);
            Service.instance().empleadoDelete(e);
            this.buscar("");
        } catch (Exception ex) {}
    }
}
