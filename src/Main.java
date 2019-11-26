import gui.*;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.cE2UData;
import logic.cE2ULogic;
import logic.states.EsperaLogin;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application implements Constants {

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
        cE2UData data = new cE2UData();
        cE2ULogic logic = new cE2ULogic(data, new EsperaLogin(data));

        GraphicInterface graphicInterface = new GraphicInterface(logic, stage);
        graphicInterface.run();
    }

    public static void main(String[] args) {
        launch();
    }
}