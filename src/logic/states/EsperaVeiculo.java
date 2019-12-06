package logic.states;

import logic.cE2UData;

public class EsperaVeiculo extends StateAdapter {

    public EsperaVeiculo(cE2UData data) {

        this.data = data;

    }

    @Override
    public IStates InsereVeiculo() {
        return new EsperaVeiculo(data);
    }

    @Override
    public IStates MostraVeiculos() {
        return new EsperaVeiculo(data);
    }
}
