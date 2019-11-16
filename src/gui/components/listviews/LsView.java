package gui.components.listviews;

import gui.components.buttons.QueryButton;
import gui.components.panes.QueryPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class LsView extends ListView {

    public LsView() {

        setupComponents();

    }

    private void setupComponents() {

        Label TextPost = new Label("Example");
        Label TextPost2 = new Label("Example2");
        QueryButton testButton = new QueryButton("teste");

        HBox box2 = new HBox(TextPost);
        box2.setAlignment(Pos.CENTER_LEFT);
        box2.setHgrow(TextPost, Priority.ALWAYS);

        HBox box3 = new HBox(TextPost2);
        box3.setAlignment(Pos.CENTER_LEFT);
        box3.setHgrow(TextPost2, Priority.ALWAYS);

        HBox box4 = new HBox(testButton);
        box4.setAlignment(Pos.CENTER_RIGHT);

        HBox mainBox = new HBox(box2, box3);
        mainBox.setSpacing(20);
        mainBox.setAlignment(Pos.CENTER_LEFT);

        HBox box = new HBox(mainBox, box4);
        box.setHgrow(mainBox, Priority.ALWAYS);
        box.setSpacing(30);

        ObservableList<HBox> items = FXCollections.observableArrayList(box);

        this.setItems(items);
        this.setPrefWidth(300);
        this.setPrefHeight(200);

    }

}
