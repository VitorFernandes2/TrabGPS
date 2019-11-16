import gui.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application implements Constants {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        /*Scene login = new Scene(new guiLogin().getRoot(),400,400);
        Scene registo = new Scene(new guiRegisto().getRoot(),400,400);
        Scene app = new Scene(new guiAplicacao().getRoot(),400,400);
        stage.setTitle("Energy2U");
        stage.setScene(login);
        stage.show();
        Stage stageRegisto = new Stage();
        stageRegisto.setTitle("Energy2U");
        stageRegisto.setScene(registo);
        stageRegisto.show();
        Stage stageApp = new Stage();
        stageApp.setTitle("Energy2U");
        stageApp.setScene(app);
        stageApp.show();*/
        GraphicInterface graphicInterface = new GraphicInterface(stage);
        graphicInterface.run();
    }

    public static void main(String[] args) {
        launch();
    }

}