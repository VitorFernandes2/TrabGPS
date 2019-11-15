/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.VBoxBuilder;

/**
 *
 * @author Bruno Ferreira
 */
public class guiLogin {
    BorderPane pRoot;
    VBox pVistaPrinc;
    Button bLogin;
    TextField tfUsername;
    PasswordField pfPalavraPasse;
    Hyperlink hlRegisto;
    ImageView ivLogo;
    
    public guiLogin() {
        createComponents();
        setupLayout();
        registerListeners();
    }
    
    private void createComponents(){
        pRoot = new BorderPane();
        bLogin = new Button("Login");
        tfUsername = new TextField();
        tfUsername.setPromptText("Nome do Utilizador");
        pfPalavraPasse = new PasswordField();
        pfPalavraPasse.setPromptText("Password");
        hlRegisto = new Hyperlink("Efetuar Registo");
        try {
            ivLogo = new ImageView(new Image(new FileInputStream("src/main/java/gui/Imagens/E2U.png")));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(guiLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
       ivLogo.setFitWidth(100);
       ivLogo.setFitHeight(100);
        pVistaPrinc = VBoxBuilder.create().spacing(15.0).padding(new Insets(5,5,5,5)).children(ivLogo,tfUsername,pfPalavraPasse,bLogin,hlRegisto).build();
        pVistaPrinc.setAlignment(Pos.CENTER);
    }
    
    private void setupLayout(){
        pRoot.setCenter(pVistaPrinc);
    }
    
    private void registerListeners(){
        bLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
            }
        });
        hlRegisto.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
            }
        });
    }
     public Pane getRoot() {
        return pRoot;
    }

}
