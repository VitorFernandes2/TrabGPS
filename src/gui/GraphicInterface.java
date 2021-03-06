package gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import logic.cE2ULogic;

public class GraphicInterface implements Constants {

    private Stage primaryStage;
    private cE2ULogic logic;

    public GraphicInterface(cE2ULogic logic,Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.logic = logic;
    }

    public void run(){

        primaryStage.setTitle("Energy2U");
        PaneOrganizer paneOrganizer = new PaneOrganizer(logic);
        Scene scene = new Scene(paneOrganizer.getPnRoot());
        primaryStage.setMinHeight(HEIGTH);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.centerOnScreen();
        primaryStage.setScene(scene);
        scene.getStylesheets().clear();
        scene.getStylesheets().add(getClass().getResource(sMainCss).toExternalForm());
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream(sLogo)));
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

}
