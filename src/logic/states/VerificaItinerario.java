
package logic.states;

import logic.E2UData;

public class VerificaItinerario extends StateAdapter{
    
     public VerificaItinerario(E2UData data) {
        this.data = data;
    }
      @Override
    public IStates efetuaItinerario(String partida, String destino) {
        return null;
    }
    
}
