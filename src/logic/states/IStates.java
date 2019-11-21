package logic.states;

public interface IStates {

    public IStates Login(String sUsername, String sPassword);
    public IStates goToLogin();
    public IStates goToRegisto();
    public IStates Registo(String sUsername, String sPassword, String sConPassord);
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim);
    public IStates Logout();
    public IStates efetuaReserva(String dados);
    public IStates consultaHistorico();
    public IStates consultaPendentes();
    public IStates efetuaItinerario(String partida, String destino);
    public IStates goToPendentes();
    public IStates goToHistorico();
    public IStates goToItinerario();
    public IStates goToPesquisa();
    public IStates cancelarReserva(String dados);

    
}
