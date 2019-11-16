package logic;

public class cIntervaloTempo {
    private static int icontaTempo = 1;
    private int iidIntervalo;
    private String shoraInicio;
    private String shoraFim;

    public cIntervaloTempo(String horaInicio, String horaFim) {
        this.shoraInicio = horaInicio;
        this.shoraFim = horaFim;
        iidIntervalo = icontaTempo++;
    }

    public int getIdIntervalo() {
        return iidIntervalo;
    }

    public void setIdIntervalo(int idIntervalo) {
        this.iidIntervalo = idIntervalo;
    }

    public String getHoraInicio() {
        return shoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.shoraInicio = horaInicio;
    }

    public String getHoraFim() {
        return shoraFim;
    }

    public void setHoraFim(String horaFim) {
        this.shoraFim = horaFim;
    }
    
    
    
}
