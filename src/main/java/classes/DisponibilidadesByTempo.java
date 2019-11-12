package classes;

public class DisponibilidadesByTempo {
    
    private int idPosto;
    private int idIntervaloTempo;
    private boolean disponibilidade;

    public DisponibilidadesByTempo(int idPosto, int idIntervaliTempo, boolean disponibilidade) {
        this.idPosto = idPosto;
        this.idIntervaloTempo = idIntervaliTempo;
        this.disponibilidade = disponibilidade;
    }

    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    public int getIdIntervaloTempo() {
        return idIntervaloTempo;
    }

    public void setIdIntervaloTempo(int idIntervaloTempo) {
        this.idIntervaloTempo = idIntervaloTempo;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }
    
    
    
}
