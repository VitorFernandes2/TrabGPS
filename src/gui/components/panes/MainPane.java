package gui.components.panes;

import javafx.collections.ObservableList;
import javafx.scene.layout.StackPane;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MainPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;

    public MainPane(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        setupComponents();
        propertyChange(null);

    }

    private void setupComponents(){

        LoginPane loginPane = new LoginPane(logic);
        loginPane.setVisible(true);
        RegisterPane registerPane = new RegisterPane(logic);
        registerPane.setVisible(false);
        QueryPane queryPane = new QueryPane(logic);
        queryPane.setVisible(false);
        HistoryPane historyPane = new HistoryPane(logic);
        historyPane.setVisible(false);
        PendingPane pendingPane = new PendingPane(logic);
        pendingPane.setVisible(false);
        ItineraryPane itineraryPane = new ItineraryPane(logic);
        itineraryPane.setVisible(false);

        ObservableList children = this.getChildren();
        children.addAll(loginPane, registerPane, queryPane, historyPane, pendingPane, itineraryPane);

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}
