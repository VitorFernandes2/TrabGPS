import gui.*;
import javafx.application.Application;
import javafx.stage.Stage;
import logic.E2UData;
import logic.E2ULogic;
import logic.states.AwaitLogin;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application implements Constants {

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {
        E2UData data = new E2UData();
        E2ULogic logic = new E2ULogic(data, new AwaitLogin(data));

        GraphicInterface graphicInterface = new GraphicInterface(logic, stage);
        graphicInterface.run();
    }

    public static void main(String[] args) {
        launch();
    }
}