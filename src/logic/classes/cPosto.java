package logic.classes;

public class cPosto {
    private static  int iContaPosto = 1;
    private int iIdPosto;
    private int iIdRegiao;
    private String sLocalizacao;
    private double dPrecoCarregamento;
    private double dLongitude;
    private double dLatitude;
    private cDistrito distrito;

    public cPosto(int id, int idRegiao, String localizacao, double dprecoCarregamento,double longitude,double latitude) {
        this.iIdRegiao = idRegiao;
        this.sLocalizacao = localizacao;
        this.dPrecoCarregamento = dprecoCarregamento;
        iIdPosto = id;
        this.dLongitude = longitude;
        this.dLatitude = latitude;
       
    }

    public double getLongitude() {
        return dLongitude;
    }

    public void setLongitude(double longitude) {
        this.dLongitude = longitude;
    }

    public double getLatitude() {
        return dLatitude;
    }

    public void setLatitude(double latitude) {
        this.dLatitude = latitude;
    }

    public int getIdPosto() {
        return iIdPosto;
    }

    public void setIdPosto(int idPosto) {
        this.iIdPosto = idPosto;
    }

    public int getIdRegiao() {
        return iIdRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.iIdRegiao = idRegiao;
    }

    public String getLocalizacao() {
        return sLocalizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.sLocalizacao = localizacao;
    }

    public double getPrecoCarregamento() {
        return dPrecoCarregamento;
    }

    public void setPrecoCarregamento(double dprecoCarregamento) {
        this.dPrecoCarregamento = dprecoCarregamento;
    }

    public cDistrito getDistrito() {
        return distrito;
    }

    public void setDistrito(cDistrito distrito) {
        this.distrito = distrito;
    }
    
    
}
