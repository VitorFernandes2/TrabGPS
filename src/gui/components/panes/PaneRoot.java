package gui.components.panes;

import gui.Constants;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * Esta é a classe principal das panes que são usadas na aplicação
 * Esta permite que toda a informação seja criada na primeira execução do programa e assim
 * consegue controlar toda a vista*/
public class PaneRoot extends VBox implements Constants, PropertyChangeListener {

    private Pane pnMainPane;
    private cE2ULogic logic;

    public PaneRoot(cE2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        this.pnMainPane = new MainPane(this.logic);
        this.getChildren().addAll(pnMainPane);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
