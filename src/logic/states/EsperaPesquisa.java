package logic.states;

import logic.E2UData;

public class EsperaPesquisa extends StateAdapter {

    public EsperaPesquisa(E2UData data) {

        this.data = data;

    }

    @Override
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim) {
        return super.Pesquisa(sLocalidade, shoraInicio, sHoraFim);
    }
    
    @Override
    public IStates goToPendentes() {
        return new VerificaPendentes(data);
    }
    
    @Override
    public IStates goToHistorico() {
        return new VerificaHistorico(data);
    }@Override
    public IStates goToItinerario() {
        return new VerificaItinerario(data);
    }
    

}
