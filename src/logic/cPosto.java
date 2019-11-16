package logic;

public class cPosto {
    private static  int  icontaPosto = 1;
    private int iidPosto;
    private int iidRegiao;
    private String slocalizacao;
    private double precoCarregamento;

    public cPosto(int idRegiao, String localizacao, double precoCarregamento) {
        this.iidRegiao = idRegiao;
        this.slocalizacao = localizacao;
        this.precoCarregamento = precoCarregamento;
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
        return precoCarregamento;
    }

    public void setPrecoCarregamento(double precoCarregamento) {
        this.precoCarregamento = precoCarregamento;
    }
    
    
}
