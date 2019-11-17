package logic.states;

import logic.E2UData;

public class StateAdapter implements IStates {

    protected E2UData data;

    @Override
    public IStates Login(String sUsername, String sPassword) {
        return null;
    }

    @Override
    public IStates goToLogin() {
        return null;
    }

    @Override
    public IStates goToRegisto() {
        return null;
    }

    @Override
    public IStates Registo(String lUsername, String sPassword, String sConPassord) {
        return null;
    }

    @Override
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim) {
        return null;
    }

    @Override
    public IStates Logout() {
        return null;
    }

}
