package gui;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GraphicInterface implements Constants {

    private Stage primaryStage;
    //Implementar aqui a lógica da aplicação para poder ser tratada pelo

    public GraphicInterface(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void run(){

        primaryStage.setTitle("Energy2U");
        PaneOrganizer paneOrganizer = new PaneOrganizer();
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
