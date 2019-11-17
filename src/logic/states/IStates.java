package logic.states;

public interface IStates {

    public IStates Login(String sUsername, String sPassword);
    public IStates Registo(String lUsername, String sPassword, String sConPassord);
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim);
    public IStates Logout();

}
