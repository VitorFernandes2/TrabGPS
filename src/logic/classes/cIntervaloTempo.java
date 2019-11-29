package logic.classes;

public class cIntervaloTempo {
   // private static int iContaTempo = 1;
    private int iIdIntervalo;
    private String sHoraInicio;
    private String sHoraFim;

    public cIntervaloTempo(int id, String horaInicio, String horaFim) {
        this.sHoraInicio = horaInicio;
        this.sHoraFim = horaFim;
        iIdIntervalo = id;
    }

    public int getIdIntervalo() {
        return iIdIntervalo;
    }

    public void setIdIntervalo(int idIntervalo) {
        this.iIdIntervalo = idIntervalo;
    }

    public String getHoraInicio() {
        return sHoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.sHoraInicio = horaInicio;
    }

    public String getHoraFim() {
        return sHoraFim;
    }

    public void setHoraFim(String horaFim) {
        this.sHoraFim = horaFim;
    }
    
    
    
}
