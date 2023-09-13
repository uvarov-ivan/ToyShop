

import Control.Controller;
import Control.iGetModel;
import Control.iGetView;
import Model.ModelToy;
import View.View;


public class App {
    public static void main(String[] args) throws Exception {

        iGetModel model = new ModelToy("toys.txt");
        iGetView view = new View();
        Controller controller = new Controller(model, view);
        controller.run();

    }


}
