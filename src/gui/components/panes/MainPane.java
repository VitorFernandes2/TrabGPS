package gui.components.panes;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import logic.cE2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPane extends StackPane implements PropertyChangeListener {

    private cE2ULogic logic;

    public MainPane(cE2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        setupComponents();
        propertyChange(null);

    }

    private void setupComponents(){

        LoginPane pnLoginPane = new LoginPane(logic);
        pnLoginPane.setVisible(true);
        RegisterPane pnRegisterPane = new RegisterPane(logic);
        pnRegisterPane.setVisible(false);
        QueryPane pnQueryPane = new QueryPane(logic);
        pnQueryPane.setVisible(false);
        HistoryPane pnHistoryPane = new HistoryPane(logic);
        pnHistoryPane.setVisible(false);
        PendingPane pnPendingPane = new PendingPane(logic);
        pnPendingPane.setVisible(false);
        ItineraryPane pnItineraryPane = new ItineraryPane(logic);
        pnItineraryPane.setVisible(false);

        ObservableList oblChildren = this.getChildren();
        oblChildren.addAll(pnLoginPane, pnRegisterPane, pnQueryPane, pnHistoryPane, pnPendingPane, pnItineraryPane);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {



    }
}
