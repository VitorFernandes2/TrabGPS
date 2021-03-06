package logic.states;

import logic.cE2UData;
import static logic.classes.cConstantes.*;

public class EsperaRegisto extends StateAdapter {

    public EsperaRegisto(cE2UData data) {

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
                return new EsperaLogin(data);

        }

        return new EsperaRegisto(data);
      
    }

    @Override
    public IStates goToLogin() {
        return new EsperaLogin(data);
    }

}
