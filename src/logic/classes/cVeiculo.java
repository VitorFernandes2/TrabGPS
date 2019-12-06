
package logic.classes;

public class cVeiculo {
    
    private int iIdVeiculo;
    private String sModelo;
    private String sMarca;
    private String sMatricula;
    private int iPotencia;
    private int iAutonomia;
    private int iIdUtilizador;

    public cVeiculo(int iIdVeiculo, String sModelo, String sMarca, String sMatricula, int iPotencia, int iAutonomia, int iIdUtilizador) {
        this.iIdVeiculo = iIdVeiculo;
        this.sModelo = sModelo;
        this.sMarca = sMarca;
        this.sMatricula = sMatricula;
        this.iPotencia = iPotencia;
        this.iAutonomia = iAutonomia;
        this.iIdUtilizador = iIdUtilizador;
    }

    public int getiIdVeiculo() {
        return iIdVeiculo;
    }

    public void setiIdVeiculo(int iIdVeiculo) {
        this.iIdVeiculo = iIdVeiculo;
    }

    public String getsModelo() {
        return sModelo;
    }

    public void setsModelo(String sModelo) {
        this.sModelo = sModelo;
    }

    public String getsMarca() {
        return sMarca;
    }

    public void setsMarca(String sMarca) {
        this.sMarca = sMarca;
    }

    public String getsMatricula() {
        return sMatricula;
    }

    public void setsMatricula(String sMatricula) {
        this.sMatricula = sMatricula;
    }

    public int getiPotencia() {
        return iPotencia;
    }

    public void setiPotencia(int iPotencia) {
        this.iPotencia = iPotencia;
    }

    public int getiAutonomia() {
        return iAutonomia;
    }

    public void setiAutonomia(int iAutonomia) {
        this.iAutonomia = iAutonomia;
    }

    public int getiIdUtilizador() {
        return iIdUtilizador;
    }

    public void setiIdUtilizador(int iIdUtilizador) {
        this.iIdUtilizador = iIdUtilizador;
    }
    
    
}
