
package logic.states;

import logic.cE2UData;

public class VerificaItinerario extends StateAdapter{
    
     public VerificaItinerario(cE2UData data) {
        this.data = data;
    }

    @Override
    public IStates efetuaReserva(String dados) {
        if(data.efetuarReservaItenerario(dados))
            return new VerificaPendentes(data);

        return new EsperaPesquisa(data);
    }

}
