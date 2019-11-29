package logic.classes;

import java.util.GregorianCalendar;

/*Comentário:
    Esta classe corresponde a uma reserva. Guarda os dados relativos.
*/

public class cReserva 
{
    private int iIdReserva;
    private int iCodServico;
    private double dCustoPrevisto;
    private int iIdPosto;
    private String sEstado;
    private int iIdUtilizador;
    private String sDiaReserva;
    private int iIdIntervaloTempo;

    public cReserva( int id, double dcustoPrevisto, int idPosto, int idUtilizador, int idIntervaloTempo,String est,String data) {
        this.dCustoPrevisto = dcustoPrevisto;
        this.iIdPosto = idPosto;
        this.iIdIntervaloTempo = idIntervaloTempo;
        this.sEstado = est;
        this.iIdUtilizador = idUtilizador;
        iIdReserva = id;
        sDiaReserva = data;
     
    }

    public String getSdiaReserva() {
        return sDiaReserva;
    }

    public void setSdiaReserva(String sdiaReserva) {
        this.sDiaReserva = sdiaReserva;
    }

    public int getIidIntervaloTempo() {
        return iIdIntervaloTempo;
    }

    public void setIidIntervaloTempo(int iidIntervaloTempo) {
        this.iIdIntervaloTempo = iidIntervaloTempo;
    }
    
   
    public int getIidReserva() {
        return iIdReserva;
    }

    public int getIcodServico() {
        return iCodServico;
    }

    public double getDcustoPrevisto() {
        return dCustoPrevisto;
    }

    public int getIidPosto() {
        return iIdPosto;
    }
    

    public String getSestado() {
        return sEstado;
    }

    public int getIidUtilizador() {
        return iIdUtilizador;
    }

    public String getDiaReserva() {
        return sDiaReserva;
    }
    

    public void setDcustoPrevisto(double dcustoPrevisto) {
        this.dCustoPrevisto = dcustoPrevisto;
    }

    public void setSestado(String sestado) {
        this.sEstado = sestado;
    }
    
     public String getData(){
        GregorianCalendar calendar = new GregorianCalendar();
        int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(GregorianCalendar.MONTH);
        int ano = calendar.get(GregorianCalendar.YEAR);
        return dia+"/"+mes+"/"+ano;
    }
}
