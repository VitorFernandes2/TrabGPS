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
    public IStates efetuaReserva(String dados) {
        if(data.efetuarReserva(dados))
            return new EsperaPendentes(data);

        return new EsperaPesquisa(data);
    }
}
