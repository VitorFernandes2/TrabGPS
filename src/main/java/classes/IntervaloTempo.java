
package classes;

public class IntervaloTempo {
    private static int contaTempo = 1;
    private int idIntervalo;
    private String horaInicio;
    private String horaFim;

    public IntervaloTempo(String horaInicio, String horaFim) {
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
        idIntervalo = contaTempo++;
    }

    public int getIdIntervalo() {
        return idIntervalo;
    }

    public void setIdIntervalo(int idIntervalo) {
        this.idIntervalo = idIntervalo;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFim() {
        return horaFim;
    }

    public void setHoraFim(String horaFim) {
        this.horaFim = horaFim;
    }
    
    
    
}
