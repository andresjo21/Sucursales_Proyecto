package sucursales.presentation.sucursales;

import sucursales.Application;
import sucursales.logic.Service;
import sucursales.logic.Sucursales;

import java.util.List;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
            model.setSucursales(Service.instance().sucursalesSearch(""));
            this.view = view;
            this.model = model;
            view.setController(this);
            view.setModel(model);
        }

        public void buscar(String filtro){
            List<Sucursales> rows = Service.instance().sucursalesSearch(filtro);
            model.setSucursales(rows);
            model.commit();
        }

        public void preAgregar(){
        Application.sucursalController.preAgregar();
    }

        public void editar(int row){
            String codigo = model.getSucursales().get(row).getCodigo();
            Sucursales e=null;
            try {
                e= Service.instance().sucursalGet(codigo);
                Application.sucursalController.editar(e);
            } catch (Exception ex) {}
        }
        public void show(){
            Application.window.setContentPane(view.getPanelSucursal());
        }
}
