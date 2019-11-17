package logic.states;

import logic.E2UData;

public class AwaitLogin extends StateAdapter {

    public AwaitLogin(E2UData data) {
        this.data = data;
    }

    @Override
    public IStates Login(String sUsername, String sPassword) {

        //fazer funções de login

        return super.Login(sUsername, sPassword);
    }

    @Override
    public IStates goToRegisto() {
        return new AwaitRegister(data);
    }

}
