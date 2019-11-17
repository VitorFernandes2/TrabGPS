
package logic.classes;

import java.util.GregorianCalendar;

public class cReserva {
    private static  int icontaReserva = 1;
    private static  int icontaServicos = 200;
    private int iidReserva;
    private int icodServico;
    private double dcustoPrevisto;
    private int iidPosto;
    private String sestado;
    private int iidUtilizador;
    private String sdiaReserva;

    public cReserva( double dcustoPrevisto, int idPosto, int idUtilizador) {
        this.icodServico = icontaServicos+10;
        this.dcustoPrevisto = dcustoPrevisto;
        this.iidPosto = idPosto;
        this.sestado = "ativa";
        this.iidUtilizador = idUtilizador;
        iidReserva = icontaReserva++;
        sdiaReserva = getData();
    }
    public String getData(){
        GregorianCalendar calendar = new GregorianCalendar();
        int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(GregorianCalendar.MONTH);
        int ano = calendar.get(GregorianCalendar.YEAR);
        return dia+"/"+mes+"/"+ano;
    }
    
    public static int getIcontaReserva() {
        return icontaReserva;
    }

    public int getIidReserva() {
        return iidReserva;
    }

    public int getIcodServico() {
        return icodServico;
    }

    public double getDcustoPrevisto() {
        return dcustoPrevisto;
    }

    public int getIidPosto() {
        return iidPosto;
    }

    public String getSestado() {
        return sestado;
    }

    public int getIidUtilizador() {
        return iidUtilizador;
    }

    public String getDiaReserva() {
        return sdiaReserva;
    }

    
    
    
}
