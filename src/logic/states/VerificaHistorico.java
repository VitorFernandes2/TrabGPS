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
public class VerificaHistorico extends StateAdapter{
    
     public VerificaHistorico(cE2UData data) {
        this.data = data;
    }
     
      @Override
    public IStates consultaHistorico() {
        data.getListaHistorico();
        return this;
    }
    
}
