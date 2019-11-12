
package classes;

public class Reserva {
        private static  int contaReserva = 1;

    private int idReserva;
    private int codServico;
    private double custoPrevisto;
    private int idPosto;
    private String estado;
    private int idUtilizador;

    public Reserva(int codServico, double custoPrevisto, int idPosto, String estado, int idUtilizador) {
        this.codServico = codServico;
        this.custoPrevisto = custoPrevisto;
        this.idPosto = idPosto;
        this.estado = estado;
        this.idUtilizador = idUtilizador;
        idReserva = contaReserva++;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getCodServico() {
        return codServico;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public double getCustoPrevisto() {
        return custoPrevisto;
    }

    public void setCustoPrevisto(double custoPrevisto) {
        this.custoPrevisto = custoPrevisto;
    }

    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }

    public void setIdUtilizador(int idUtilizador) {
        this.idUtilizador = idUtilizador;
    }
    
    
}
