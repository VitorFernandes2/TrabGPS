package logic;

import logic.states.*;

import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import logic.classes.*;

public class E2ULogic extends PropertyChangeSupport {

    private IStates state;
    private E2UData data;

    public E2ULogic(E2UData data,IStates state) throws IOException {
        super(state);
        this.state = state;
        this.data = data;
    }

    public boolean inLogin(){
        return this.state instanceof EsperaLogin;
    }

    public boolean inRegister(){
        return this.state instanceof EsperaRegisto;
    }

    public boolean inQuery(){
        return this.state instanceof EsperaPesquisa;
    }
    public boolean inLogout(){
        return this.state instanceof EsperaLogout;
    }
    public boolean inPendentes(){
        return this.state instanceof EsperaPendentes;
    }
    public boolean inHistorico(){
        return this.state instanceof EsperaHistorico;
    }
    public boolean inItinerario(){
        return this.state instanceof EsperaItinerario;
    }
    
    public int getErro(){
        return data.getErro();
    }

    public void goToLogin(){

        if (inRegister()){

            this.state = this.state.goToLogin();
            firePropertyChange(null, false, true);
        }
    }

    public void goToRegister(){

        if (inLogin()){
            this.state = this.state.goToRegisto();
            firePropertyChange(null, false, true);
        }
    }

    public void goToHistorico(){

        this.state = this.state.goToHistorico();
        firePropertyChange(null, false, true);

    }
    public void goToPendentes(){

        this.state = this.state.goToPendentes();
        firePropertyChange(null, false, true);

    }
    public void goToItinerario(){

        this.state = this.state.goToItinerario();
        firePropertyChange(null, false, true);

    }

     public void goToPesquisa(){

        this.state = this.state.goToPesquisa();
        firePropertyChange(null, false, true);
        
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
    
    public HashMap<Integer,HashMap<String, String>> historico(){

        return this.data.getListaHistorico();

    }
    
     public void reserva(String dados){

        if (inQuery()){
            this.state = this.state.efetuaReserva(dados);
            firePropertyChange(null, false, true);
        }
    }
     public void pendentes(){

        if (inPendentes()){
            this.state = this.state.consultaPendentes();
            firePropertyChange(null, false, true);
        }
    }
    public void itinerario(String partida, String destino){

        if (inItinerario()){
            this.state = this.state.efetuaItinerario(partida, destino);
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

    public ArrayList<String> getPostos(){
        return data.infoPosto();
    }

    public List<String> infoPostosByPesquisa(String regiao,String tempo){
        return data.infoPostosByPesquisa(regiao,tempo);
    }
    
    public List<String> getListaPendentes(){
        return data.getListaPendentes();
    }
     
    public HashMap<Integer,HashMap<String,String>> getListaHistorico(){
        return data.getListaHistorico();
    }

    public void cancelarReservas(String dados){
        if (inPendentes()){
            this.state = this.state.cancelarReserva(dados);
            firePropertyChange(null, false, true);
        }
    }

}
