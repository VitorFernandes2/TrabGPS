package logic.classes;

public class cPosto {
    private static  int  icontaPosto = 1;
    private int iidPosto;
    private int iidRegiao;
    private String slocalizacao;
    private double dprecoCarregamento;

    public cPosto(int idRegiao, String localizacao, double dprecoCarregamento) {
        this.iidRegiao = idRegiao;
        this.slocalizacao = localizacao;
        this.dprecoCarregamento = dprecoCarregamento;
        iidPosto=icontaPosto++;
    }

    public int getIdPosto() {
        return iidPosto;
    }

    public void setIdPosto(int idPosto) {
        this.iidPosto = idPosto;
    }

    public int getIdRegiao() {
        return iidRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.iidRegiao = idRegiao;
    }

    public String getLocalizacao() {
        return slocalizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.slocalizacao = localizacao;
    }

    public double getPrecoCarregamento() {
        return dprecoCarregamento;
    }

    public void setPrecoCarregamento(double dprecoCarregamento) {
        this.dprecoCarregamento = dprecoCarregamento;
    }
    
    
}
