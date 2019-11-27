package logic.classes;

public class cDisponibilidadesByTempo {
    
    private int iIdPosto;
    private int iIdIntervaloTempo;
    private boolean bDisponibilidade;

    public cDisponibilidadesByTempo(int idPosto, int idIntervaliTempo, boolean disponibilidade) {
        this.iIdPosto = idPosto;
        this.iIdIntervaloTempo = idIntervaliTempo;
        this.bDisponibilidade = disponibilidade;
    }

    public int getIdPosto() {
        return iIdPosto;
    }

    public void setIdPosto(int idPosto) {
        this.iIdPosto = idPosto;
    }

    public int getIdIntervaloTempo() {
        return iIdIntervaloTempo;
    }

    public void setIdIntervaloTempo(int idIntervaloTempo) {
        this.iIdIntervaloTempo = idIntervaloTempo;
    }

    public boolean isDisponibilidade() {
        return bDisponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.bDisponibilidade = disponibilidade;
    }
    
    
    
}
