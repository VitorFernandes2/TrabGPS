package logic.states;

import logic.cE2UData;

public class StateAdapter implements IStates {

    protected cE2UData data;

    @Override
    public IStates Login(String sUsername, String sPassword) {
        return null;
    }

    @Override
    public IStates goToLogin() {
        return new EsperaLogin(data);
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

    @Override
    public IStates efetuaReserva(String dados) {
        return null;
    }

    @Override
    public IStates consultaHistorico() {
        return null;
    }

    @Override
    public IStates consultaPendentes() {
        return null;
    }
    
    @Override
    public IStates efetuaItinerario(String partida, String destino) {
        return null;
    }

    @Override
    public IStates goToPendentes() {
        return new VerificaPendentes(data);
    }

    @Override
    public IStates goToHistorico() {
        return new VerificaHistorico(data);
    }

    @Override
    public IStates goToItinerario() {
        return new VerificaItinerario(data);
    }

    @Override
    public IStates goToPesquisa() {
        return new EsperaPesquisa(data);
    }

    @Override
    public IStates cancelarReserva(String dados) {
        return null;
    }

    @Override
    public IStates InsereVeiculo(String modelo,String marca,String matricula,int pot, int auto) {
        return null;
    }
    @Override
    public IStates EliminaVeiculos(String dados) {
        return null;
    }

    @Override
    public IStates MostraVeiculos() {
        return null;
    }

    @Override
    public IStates goToVeiculos() {
        return new EsperaVeiculo(data);
    }

}
