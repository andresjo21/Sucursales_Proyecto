package sucursales.presentation.acercade;

public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
    }

    public void show(){
        view.setVisible(true);
    }

    public void hide(){
        view.setVisible(false);
    }


}
