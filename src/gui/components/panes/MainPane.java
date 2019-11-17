package gui.components.panes;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPane extends StackPane implements PropertyChangeListener {

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

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
