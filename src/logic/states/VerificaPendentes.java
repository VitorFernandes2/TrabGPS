/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.states;

import logic.cE2UData;

/**
 *
 * @author Bruno Ferreira
 */
public class VerificaPendentes extends StateAdapter{
    
     public VerificaPendentes(cE2UData data) {
        this.data = data;
    }
     
     @Override
    public IStates consultaPendentes() {
        
        data.getListaPendentes();
        
        return this;
    }

    @Override
    public IStates cancelarReserva(String dados) {

        data.cancelarReserva(dados);
        return new VerificaPendentes(data);

    }

}
