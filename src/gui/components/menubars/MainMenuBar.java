package gui.components.menubars;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class MainMenuBar extends MenuBar {

    public MainMenuBar() {
        Menu menu1 = new Menu("Inicio");
        Menu menu2 = new Menu("Itinerário");
        Menu menu3 = new Menu("Histórico");
        Menu menu4 = new Menu("Pendentes");

        this.getMenus().addAll(menu1, menu2, menu3, menu4);
        this.getStyleClass().add("MainMenuBar");

    }

}
