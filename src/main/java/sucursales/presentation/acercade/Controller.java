package sucursales.presentation.acercade;

import sucursales.Application;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        //view.setModel(model);
    }

    public void show(){
        Application.window.setContentPane(view.getPanel());
    }

}
