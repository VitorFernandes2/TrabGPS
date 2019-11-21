package gui.components.menubars;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import logic.E2ULogic;

public class MainMenuBar extends MenuBar {

    private E2ULogic logic;
    private Menu menu1;
    private Menu menu2;
    private Menu menu3;
    private Menu menu4;
    private Label label1;
    private Label label2;
    private Label label3;
    private Label label4;

    public MainMenuBar(E2ULogic logic) {
        this.logic = logic;

        label1 = new Label("Inicio");
        label1.setOnMouseClicked(e -> {
            this.logic.goToPesquisa();
        });
        label2 = new Label("Itinerário");
        label2.setOnMouseClicked(e -> {
            this.logic.goToItinerario();
        });
        label3 = new Label("Histórico");
        label3.setOnMouseClicked(e -> {
            this.logic.goToHistorico();
        });
        label4 = new Label("Pendentes");
        label4.setOnMouseClicked(e -> {
            this.logic.goToPendentes();
        });

        menu1 = new Menu();
        menu1.setGraphic(label1);

        menu2 = new Menu();
        menu2.setGraphic(label2);

        menu3 = new Menu();
        menu3.setGraphic(label3);

        menu4 = new Menu();
        menu4.setGraphic(label4);

        this.getMenus().addAll(menu1, menu2, menu3, menu4);
        this.getStyleClass().add("MainMenuBar");

    }

}
