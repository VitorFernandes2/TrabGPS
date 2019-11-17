package gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.E2ULogic;

public class GraphicInterface implements Constants {

    private Stage primaryStage;
    private E2ULogic logic;

    public GraphicInterface(E2ULogic logic,Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.logic = logic;
    }

    public void run(){

        primaryStage.setTitle("Energy2U");
        PaneOrganizer paneOrganizer = new PaneOrganizer(logic);
        Scene scene = new Scene(paneOrganizer.getRoot());
        primaryStage.setMinHeight(HEIGTH);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(sMainCss).toExternalForm());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(sLogo)));
        primaryStage.show();

    }

}
