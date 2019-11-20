
package logic.states;

import logic.E2UData;

public class VerificaReserva extends StateAdapter{
    
     public VerificaReserva(E2UData data) {
        this.data = data;
    }
     
     @Override
    public IStates efetuaReserva(String dados) {

         if(data.efetuarReserva(dados))
             return new VerificaPendentes(data);
         
         return new EsperaPesquisa(data);

    }
    
}
