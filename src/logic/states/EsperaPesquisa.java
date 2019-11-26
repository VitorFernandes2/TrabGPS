package logic.states;

import logic.cE2UData;

public class EsperaPesquisa extends StateAdapter {

    public EsperaPesquisa(cE2UData data) {

        this.data = data;
    }

    @Override
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim) {
        return super.Pesquisa(sLocalidade, shoraInicio, sHoraFim);
    }

    @Override
    public IStates efetuaReserva(String dados) {
        if(data.efetuarReserva(dados))
            return new VerificaPendentes(data);

        return new EsperaPesquisa(data);
    }
}
