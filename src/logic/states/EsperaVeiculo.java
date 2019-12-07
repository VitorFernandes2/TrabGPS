package logic.states;

import logic.cE2UData;

public class EsperaVeiculo extends StateAdapter {

    public EsperaVeiculo(cE2UData data) {

        this.data = data;

    }

    @Override
    public IStates InsereVeiculo(String modelo,String marca,String matricula,int pot, int auto) {
        if(data.registaVeiculo(modelo, marca, matricula, pot, auto)){
            return new EsperaVeiculo(data);
        }
        return this;
    }

    @Override
    public IStates MostraVeiculos() {
        return new EsperaVeiculo(data);
    }
    
    @Override
    public IStates EliminaVeiculos(String dados){
       if(data.eliminarVeiculo(dados)){
           return new EsperaVeiculo(data);
       }
       return this;
    }
}
