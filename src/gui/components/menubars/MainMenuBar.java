package gui.components.menubars;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import logic.cE2ULogic;

public class MainMenuBar extends MenuBar {

    private cE2ULogic logic;
    private Menu mn1;
    private Menu mn2;
    private Menu mn3;
    private Menu mn4;
    private Menu mn5;
    private Label lb1;
    private Label lb2;
    private Label lb3;
    private Label lb4;
    private Label lb5;

    public MainMenuBar(cE2ULogic logic) {
        this.logic = logic;

        lb1 = new Label("Inicio");
        lb1.setOnMouseClicked(e -> {
            this.logic.goToPesquisa();
        });
        lb2 = new Label("Itinerário");
        lb2.setOnMouseClicked(e -> {
            this.logic.goToItinerario();
        });
        lb3 = new Label("Histórico");
        lb3.setOnMouseClicked(e -> {
            this.logic.goToHistorico();
        });
        lb4 = new Label("Pendentes");
        lb4.setOnMouseClicked(e -> {
            this.logic.goToPendentes();
        });
        lb5 = new Label("Logout");
        lb5.setOnMouseClicked(e -> {
            System.exit(0);
        });

        mn1 = new Menu();
        mn1.setGraphic(lb1);

        mn2 = new Menu();
        mn2.setGraphic(lb2);

        mn3 = new Menu();
        mn3.setGraphic(lb3);

        mn4 = new Menu();
        mn4.setGraphic(lb4);

        mn5 = new Menu();
        mn5.setGraphic(lb5);

        this.getMenus().addAll(mn1, mn2, mn3, mn4);
        this.getStyleClass().add("MainMenuBar");

    }

}
