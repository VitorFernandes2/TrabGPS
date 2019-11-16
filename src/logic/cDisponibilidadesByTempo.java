package logic;

public class cDisponibilidadesByTempo {
    
    private int iidPosto;
    private int iidIntervaloTempo;
    private boolean bdisponibilidade;

    public cDisponibilidadesByTempo(int idPosto, int idIntervaliTempo, boolean disponibilidade) {
        this.iidPosto = idPosto;
        this.iidIntervaloTempo = idIntervaliTempo;
        this.bdisponibilidade = disponibilidade;
    }

    public int getIdPosto() {
        return iidPosto;
    }

    public void setIdPosto(int idPosto) {
        this.iidPosto = idPosto;
    }

    public int getIdIntervaloTempo() {
        return iidIntervaloTempo;
    }

    public void setIdIntervaloTempo(int idIntervaloTempo) {
        this.iidIntervaloTempo = idIntervaloTempo;
    }

    public boolean isDisponibilidade() {
        return bdisponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.bdisponibilidade = disponibilidade;
    }
    
    
    
}
