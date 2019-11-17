package logic.states;

import logic.E2UData;

public class AwaitRegister extends StateAdapter {

    public AwaitRegister(E2UData data) {

        this.data = data;

    }

    @Override
    public IStates Registo(String sUsername, String sPassword, String sConPassord) {

        if (!data.criaRegisto(sUsername, sPassword, sConPassord))
              return new AwaitRegister(data);
        else
            return new AwaitLogin(data);
      
    }

    @Override
    public IStates goToLogin() {
        return new AwaitLogin(data);
    }

}
