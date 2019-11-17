package logic.states;

public class StateAdapter implements IStates {

    @Override
    public IStates Login(String sUsername, String sPassword) {
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
