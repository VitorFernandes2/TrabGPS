package gui;

import gui.components.panes.PaneRoot;
import javafx.scene.layout.Pane;
import logic.cE2ULogic;

public class PaneOrganizer {

    private final Pane root;

    public PaneOrganizer(cE2ULogic logic) {
        this.root = new PaneRoot(logic);
    }

    public Pane getRoot() {
        return root;
    }
}
