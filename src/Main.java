import gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.E2UData;
import logic.E2ULogic;
import logic.states.AwaitLogin;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main extends Application implements Constants {

    @Override
    public void start(Stage stage) throws FileNotFoundException, IOException {

        E2ULogic logic = new E2ULogic(new AwaitLogin(new E2UData()));

        GraphicInterface graphicInterface = new GraphicInterface(logic, stage);
        graphicInterface.run();
    }

    public static void main(String[] args) {
        launch();
    }

}