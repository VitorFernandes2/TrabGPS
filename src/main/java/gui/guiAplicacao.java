package gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;


public class guiAplicacao {
    BorderPane pRoot;
    HBox hbMenu;
    MenuBar mbPrinc;
    Menu mInicio,mItenerario,mHistorico,mPendentes;
    public guiAplicacao() {
        createComponents();
        setupLayout();
        registerListeners();
    }
    private void createComponents(){
        pRoot = new BorderPane();
        mInicio = new Menu("Início");
        mItenerario = new Menu("Itenerário");
        mHistorico = new Menu("Histórico");
        mPendentes = new Menu("Pendentes");
        mbPrinc = new MenuBar();
        mbPrinc.getMenus().addAll(mInicio,mItenerario,mHistorico,mPendentes);
        hbMenu = new HBox(mbPrinc);
    }
    
    private void setupLayout(){
        pRoot.setTop(hbMenu);
    }
    
    private void registerListeners(){
        
        
    }
     public Pane getRoot() {
        return pRoot;
    }
}
