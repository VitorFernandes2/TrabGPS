package logic;

import logic.states.*;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import logic.classes.cIntervaloTempo;
import logic.classes.cPosto;
import logic.classes.cRegiao;

public class E2ULogic extends PropertyChangeSupport {

    IStates state;

    public E2ULogic(IStates state) {
        super(state);
        this.state = state;
        state = new AwaitLogin();
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

    public int Login(String sUsername, String sPassword){

        //se houver dados nas strings
        if (!sUsername.isEmpty() && !sPassword.isEmpty())
            if (inLogin()){

                this.state = this.state.Login(sUsername, sPassword);

                // informa o estado da interface
                firePropertyChange(null, false, true);

                //se login bem efetuado
                if (inQuery())
                    return 1;
                //se login mal feito
                else
                    return -2;

            }

        //dados não preenchidos
        return -1;

    }

    public int Register(String sUsername, String sPassword, String sConfPassword){

        //Se tiver os dados todos preenchidos
        if (!sUsername.isEmpty() && !sPassword.isEmpty() && !sConfPassword.isEmpty())
            if (inRegister()){

                this.state = this.state.Registo(sUsername, sPassword, sConfPassword);

                // informa o estado da interface
                firePropertyChange(null, false, true);

                //se registo bem efetuado
                if (inLogin())
                    return 1;
                //se registo mal feito
                else
                    return -2;

            }

        //Se não tiver os dados todos preenchidos
        return -1;

    }

    public int Query(String sLocalidade, String sHoraInicio, String sHoraFim){

        //se estiver no menu de pesquisa
        if (inQuery()){

            //aplica a função de pesquisa
            this.state = this.state.Pesquisa(sLocalidade, sHoraInicio, sHoraFim);

            // informa o estado da interface
            firePropertyChange(null, false, true);

            //pesquisa corre sempre bem se estiver neste menu
            return 1;

        }

        //se não estiver no menu de pesquisa
        return -1;

    }
    
    //Get's de valores
    public ArrayList<String> getPostos(ArrayList<cPosto> alPosto){
        ArrayList<String> aux = new ArrayList<>();
        for(cPosto p : alPosto){
            aux.add(p.getLocalizacao());
        }
        return aux;
    }
    
    public ArrayList<String> getLocalidades(ArrayList<cRegiao> alRegiao){
        ArrayList<String> aux = new ArrayList<>();
        for(cRegiao r : alRegiao){
            aux.add(r.getNomeRegiao());
        }
        return aux;
    }
    
    public ArrayList<String> getHorarios(ArrayList<cIntervaloTempo> alIntervaloTempo){
        ArrayList<String> aux = new ArrayList<>();
        for(cIntervaloTempo i : alIntervaloTempo){
            aux.add(i.getHoraInicio());
        }
        return aux;
    }

}
