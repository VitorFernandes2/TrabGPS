package logic.states;

import logic.E2UData;
import static logic.classes.cConstantes.*;

public class AwaitRegister extends StateAdapter {

    public AwaitRegister(E2UData data) {

        this.data = data;

    }

    @Override
    public IStates Registo(String sUsername, String sPassword, String sConPassord) {

        if(!data.verificaInputUsername(sUsername))
            data.setErro(ERROINPUTUSER);
        else if (!data.verificaInputPassword(sPassword))
            data.setErro(ERROINPUTPASS);
        else if (!data.verificaInputPassword(sConPassord))
            data.setErro(ERROINPUTCONFPASS);
        else if (!sConPassord.equals(sPassword))
            data.setErro(PASSWORDSDIFERENTES);
        else if (data.verificaUsername(sUsername))
            data.setErro(UTILIZADORJAEXISTE);
        else{
            data.setErro(REGISTONORMAL);

            if (data.criaRegisto(sUsername,sPassword,sConPassord))
                return new AwaitLogin(data);

        }

        return new AwaitRegister(data);
      
    }

    @Override
    public IStates goToLogin() {
        return new AwaitLogin(data);
    }

}
