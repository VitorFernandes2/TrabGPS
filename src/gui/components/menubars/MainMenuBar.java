package gui.components.menubars;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import logic.E2ULogic;

public class MainMenuBar extends MenuBar {

    private E2ULogic logic;
    private Menu menu1;
    private Menu menu2;
    private Menu menu3;
    private Menu menu4;

    public MainMenuBar(E2ULogic logic) {
        this.logic = logic;

        menu1 = new Menu("Inicio");
        menu2 = new Menu("Itinerário");
        menu3 = new Menu("Histórico");
        menu4 = new Menu("Pendentes");

        this.getMenus().addAll(menu1, menu2, menu3, menu4);
        this.getStyleClass().add("MainMenuBar");

        registerListeners();

    }

    private void registerListeners(){

        menu1.setOnAction(e->{
            this.logic.goToPesquisa();
        });

        menu2.setOnAction(e->{
            this.logic.goToItinerario();
        });

        menu3.setOnAction(e->{
                this.logic.goToHistorico();
        });

        menu4.setOnAction(e->{
                this.logic.goToPendentes();
        });

    }

}
