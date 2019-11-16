
package logic;

public class cReserva {
        private static  int icontaReserva = 1;

    private int iidReserva;
    private int icodServico;
    private double custoPrevisto;
    private int iidPosto;
    private String sestado;
    private int iidUtilizador;

    public cReserva(int codServico, double custoPrevisto, int idPosto, String estado, int idUtilizador) {
        this.icodServico = codServico;
        this.custoPrevisto = custoPrevisto;
        this.iidPosto = idPosto;
        this.sestado = estado;
        this.iidUtilizador = idUtilizador;
        iidReserva = icontaReserva++;
    }

    public int getIdReserva() {
        return iidReserva;
    }

    public void setIdReserva(int idReserva) {
        this.iidReserva = idReserva;
    }

    public int getCodServico() {
        return icodServico;
    }

    public void setCodServico(int codServico) {
        this.icodServico = codServico;
    }

    public double getCustoPrevisto() {
        return custoPrevisto;
    }

    public void setCustoPrevisto(double custoPrevisto) {
        this.custoPrevisto = custoPrevisto;
    }

    public int getIdPosto() {
        return iidPosto;
    }

    public void setIdPosto(int idPosto) {
        this.iidPosto = idPosto;
    }

    public String getEstado() {
        return sestado;
    }

    public void setEstado(String estado) {
        this.sestado = estado;
    }

    public int getIdUtilizador() {
        return iidUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.iidUtilizador = idUtilizador;
    }
    
    
}
