package gui;

import gui.components.panes.PaneRoot;
import javafx.scene.layout.Pane;
import logic.cE2ULogic;

public class PaneOrganizer {

    private final Pane pnRoot;

    public PaneOrganizer(cE2ULogic logic) {
        this.pnRoot = new PaneRoot(logic);
    }

    public Pane getPnRoot() {
        return pnRoot;
    }
}
