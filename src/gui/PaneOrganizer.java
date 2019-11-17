package gui;

import gui.components.panes.PaneRoot;
import javafx.scene.layout.Pane;
import logic.E2ULogic;

public class PaneOrganizer {

    private final Pane root;

    public PaneOrganizer(E2ULogic logic) {
        this.root = new PaneRoot(logic);
    }

    public Pane getRoot() {
        return root;
    }
}
