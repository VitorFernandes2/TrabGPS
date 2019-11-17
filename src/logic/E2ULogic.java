package logic;

import logic.states.*;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import logic.classes.cIntervaloTempo;
import logic.classes.cPosto;
import logic.classes.cRegiao;

public class E2ULogic extends PropertyChangeSupport {

    private IStates state;
    private E2UData data;

    public E2ULogic(IStates state) throws IOException {
        super(state);
        this.state = state;
        this.data = new E2UData();
    }

    public boolean inLogin(){
        return this.state instanceof AwaitLogin;
    }

    public boolean inRegister(){
        return this.state instanceof AwaitRegister;
    }

    public boolean inQuery(){
        return this.state instanceof AwaitQuery;
    }

    public boolean inLogout(){
        return this.state instanceof AwaitLogout;
    }
    
    public int getErro(){
        return data.getErro();
    }

    public void goToLogin(){

        if (inRegister()){

            this.state = this.state.goToLogin();
            // informa o estado da interface
            firePropertyChange(null, false, true);
        }
    }

    public void goToRegister(){

        if (inLogin()){
            this.state = this.state.goToRegisto();
            // informa o estado da interface
            firePropertyChange(null, false, true);
        }
    }

    public void Login(String sUsername, String sPassword){

        //se houver dados nas strings
        if (inLogin()){
            this.state = this.state.Login(sUsername, sPassword);
            // informa o estado da interface
            firePropertyChange(null, false, true);
        }
    }

    public void Register(String sUsername, String sPassword, String sConfPassword){

            if (inRegister()){
                this.state = this.state.Registo(sUsername, sPassword, sConfPassword);

                // informa o estado da interface
                firePropertyChange(null, false, true);
            }
    }

    public void Query(String sLocalidade, String sHoraInicio, String sHoraFim){

        //se estiver no menu de pesquisa
        if (inQuery()){

            //aplica a função de pesquisa
            this.state = this.state.Pesquisa(sLocalidade, sHoraInicio, sHoraFim);

            // informa o estado da interface
            firePropertyChange(null, false, true);
        }
    }
    
    public ArrayList<String> getLocalidades(){
        ArrayList<String> aux = new ArrayList<>();
        for(cRegiao r : data.getListaRegioes()){
            aux.add(r.getNomeRegiao());
        }
        return aux;
    }
    
    public ArrayList<String> getHorarios(){
        ArrayList<String> aux = new ArrayList<>();
        for(cIntervaloTempo i : data.getListaTempos()){
            aux.add(i.getHoraInicio());
        }
        return aux;
    }

}
