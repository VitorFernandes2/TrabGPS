
package logic.states;

import logic.E2UData;

public class EsperaReserva extends StateAdapter{
    
     public EsperaReserva(E2UData data) {
        this.data = data;
    }
     
     @Override
    public IStates efetuaReserva(String dados) {

         if(data.efetuarReserva(dados))
             return new EsperaPendentes(data);
         
         return new EsperaPesquisa(data);

    }
    
}
