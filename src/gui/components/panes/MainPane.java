package gui.components.panes;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

public class MainPane extends StackPane {

    public MainPane() {

        setupComponents();

    }

    private void setupComponents(){

        LoginPane loginPane = new LoginPane();
        loginPane.setVisible(true);
        RegisterPane registerPane = new RegisterPane();
        registerPane.setVisible(false);
        QueryPane queryPane = new QueryPane();
        queryPane.setVisible(false);

        ObservableList children = this.getChildren();
        children.addAll(loginPane, registerPane, queryPane);

    }

}
