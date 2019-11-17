package gui.components.panes;

import gui.components.buttons.GreyButton;
import gui.components.buttons.QueryButton;
import gui.components.labels.LabelTitle;
import gui.components.listviews.LsView;
import gui.components.menubars.MainMenuBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import logic.E2ULogic;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class QueryPane extends StackPane implements PropertyChangeListener {

    private E2ULogic logic;
    private BorderPane borderPane;
    private MainMenuBar mainMenuBar;
    private VBox leftbox;
    private VBox vBox;
    private LabelTitle lblPesquisa;
    private ChoiceBox cbHorario;
    private HBox hbHorario;
    private ChoiceBox cbLocalidade;
    private VBox rightbox;
    private LabelTitle lblPostos;
    private LsView list;
    private HBox hbLocalidade;
    private Button btPesquisa;

    public QueryPane(E2ULogic logic) {
        this.logic = logic;
        this.logic.addPropertyChangeListener(this);

        createComponents();
        registerListeners();
        propertyChange(null);
    }

    private void createComponents() {

        ObservableList children = this.getChildren();
        children.add(interfaceA());

    }

    private BorderPane interfaceA(){

        btPesquisa = new GreyButton("Pesquisar");
        borderPane = new BorderPane();
        mainMenuBar = new MainMenuBar();

        vBox = new VBox(mainMenuBar);
        borderPane.setTop(vBox);

        leftbox = new VBox();
        leftbox.setPadding(new Insets(100,0,0,100));

        lblPesquisa = new LabelTitle("Pesquisa:");
        cbHorario = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getHorarios())
        );

        Label lbHorario = new Label("Horário: ");
        lbHorario.setFont(new Font(15));
        hbHorario = new HBox(lbHorario,cbHorario);
        hbHorario.setPadding(new Insets(0,0,15,0));
        hbHorario.setAlignment(Pos.CENTER_LEFT);

        Label lbLocalidade = new Label("Localidade: ");
        lbLocalidade.setFont(new Font(15));
        cbLocalidade = new ChoiceBox(
                FXCollections.observableArrayList(this.logic.getLocalidades())
        );

        hbLocalidade = new HBox(lbLocalidade, cbLocalidade);
        hbLocalidade.setPadding(new Insets(0,0,15,0));
        hbLocalidade.setAlignment(Pos.CENTER_LEFT);


        leftbox.getChildren().addAll(lblPesquisa, hbHorario, hbLocalidade, btPesquisa);

        rightbox = new VBox();
        rightbox.setPadding(new Insets(100,100,100,100));
        lblPostos = new LabelTitle("Postos:");

        list = new LsView();

        for (String item : this.logic.getPostos()) {

            Label TextPost = new Label(item);
            QueryButton testButton = new QueryButton("Reservar");

            HBox box2 = new HBox(TextPost);
            box2.setAlignment(Pos.CENTER_LEFT);
            box2.setHgrow(TextPost, Priority.ALWAYS);

            HBox box4 = new HBox(testButton);
            box4.setAlignment(Pos.CENTER_RIGHT);

            HBox mainBox = new HBox(box2);
            mainBox.setSpacing(20);
            mainBox.setAlignment(Pos.CENTER_LEFT);

            HBox box = new HBox(mainBox, box4);
            box.setHgrow(mainBox, Priority.ALWAYS);
            box.setSpacing(30);

            ObservableList<HBox> items = FXCollections.observableArrayList(box);

            list.getItems().add(box);

        }

        rightbox.getChildren().addAll(lblPostos, list);

        borderPane.setCenter(rightbox);
        borderPane.setLeft(leftbox);

        return borderPane;

    }

    private void registerListeners(){
        btPesquisa.setOnMouseClicked(e ->{

            list.getItems().clear();

            for (String item : this.logic.infoPostosByPesquisa((String) cbLocalidade.getValue(), (String) cbHorario.getValue())) {

                Label TextPost = new Label(item);
                QueryButton testButton = new QueryButton("Reservar");

                HBox box2 = new HBox(TextPost);
                box2.setAlignment(Pos.CENTER_LEFT);
                box2.setHgrow(TextPost, Priority.ALWAYS);

                HBox box4 = new HBox(testButton);
                box4.setAlignment(Pos.CENTER_RIGHT);

                HBox mainBox = new HBox(box2);
                mainBox.setSpacing(20);
                mainBox.setAlignment(Pos.CENTER_LEFT);

                HBox box = new HBox(mainBox, box4);
                box.setHgrow(mainBox, Priority.ALWAYS);
                box.setSpacing(30);

                ObservableList<HBox> items = FXCollections.observableArrayList(box);

                list.getItems().add(box);

            }

        });

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        setVisible(this.logic.inQuery());
    }

}
