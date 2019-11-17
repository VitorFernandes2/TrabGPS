package logic.states;

import logic.E2UData;
import static logic.classes.cConstantes.*;

public class AwaitLogin extends StateAdapter {

    public AwaitLogin(E2UData data) {
        this.data = data;
    }

    @Override
    public IStates Login(String sUsername, String sPassword) {

        if(!data.verificaInputUsername(sUsername))
            data.setErro(ERROINPUTUSER);
        else if (!data.verificaInputPassword(sPassword))
             data.setErro(ERROINPUTPASS);
        else if (!data.verificaUsername(sUsername))
            data.setErro(UTILIZADORNAOEXISTE);
        else if (!data.verificaDadosLogin(sUsername, sPassword))
            data.setErro(ERRODADOSUTILIZADOR);
        else{
            data.setErro(LOGINNORMAL);
            return new AwaitQuery(data);
        }

        return new AwaitLogin(data);
    }

    @Override
    public IStates goToRegisto() {
        return new AwaitRegister(data);
    }

}
