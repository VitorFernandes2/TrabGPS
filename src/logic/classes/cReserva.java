package logic.classes;

import java.util.GregorianCalendar;

/*Comentário:
    Esta classe corresponde a uma reserva. Guarda os dados relativos.
*/

public class cReserva 
{
    private static  int iContaReserva = 1;
    private static  int iContaServicos = 200;
    private int iIdReserva;
    private int iCodServico;
    private double dCustoPrevisto;
    private int iIdPosto;
    private String sEstado;
    private int iIdUtilizador;
    private String sDiaReserva;
    private int iIdIntervaloTempo;

    public cReserva( double dcustoPrevisto, int idPosto, int idUtilizador, int idIntervaloTempo) {
        this.iCodServico = iContaServicos +10;
        this.dCustoPrevisto = dcustoPrevisto;
        this.iIdPosto = idPosto;
        this.iIdIntervaloTempo = idIntervaloTempo;
        this.sEstado = "Ativo";
        this.iIdUtilizador = idUtilizador;
        iIdReserva = iContaReserva++;
        sDiaReserva = getData();
     
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
    
    public static int getIcontaReserva() {
        return iContaReserva;
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
