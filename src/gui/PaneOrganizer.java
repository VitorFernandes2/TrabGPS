package gui;

import gui.components.panes.PaneRoot;
import javafx.scene.layout.Pane;

public class PaneOrganizer {

    private final Pane root;

    public PaneOrganizer() {
        this.root = new PaneRoot();
    }

    public Pane getRoot() {
        return root;
    }
}
