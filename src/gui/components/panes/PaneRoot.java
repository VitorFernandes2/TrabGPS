package gui.components.panes;

import gui.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Esta é a classe principal das panes que são usadas na aplicação
 * Esta permite que toda a informação seja criada na primeira execução do programa e assim
 * consegue controlar toda a vista*/
public class PaneRoot extends VBox implements Constants, PropertyChangeListener {

    private Pane mainPane;

    public PaneRoot() {
        this.mainPane = new MainPane();
        this.getChildren().addAll(mainPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
