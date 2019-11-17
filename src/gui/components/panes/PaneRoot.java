package gui.components.panes;

import gui.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Esta é a classe principal das panes que são usadas na aplicação
 * Esta permite que toda a informação seja criada na primeira execução do programa e assim
 * consegue controlar toda a vista*/
public class PaneRoot extends VBox implements Constants, PropertyChangeListener {

    private Pane mainPane;
    private E2ULogic logic;

    public PaneRoot(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        this.mainPane = new MainPane(this.logic);
        this.getChildren().addAll(mainPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
