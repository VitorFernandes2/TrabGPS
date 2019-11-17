package logic.states;

import logic.E2UData;

public class AwaitQuery extends StateAdapter {

    public AwaitQuery(E2UData data) {

        this.data = data;

    }

    @Override
    public IStates Pesquisa(String sLocalidade, String shoraInicio, String sHoraFim) {
        return super.Pesquisa(sLocalidade, shoraInicio, sHoraFim);
    }

}
