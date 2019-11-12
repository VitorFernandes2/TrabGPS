
package classes;

public class Posto {
    private static  int  contaPosto = 1;
    private int idPosto;
    private int idRegiao;
    private String localizacao;
    private double precoCarregamento;

    public Posto(int idRegiao, String localizacao, double precoCarregamento) {
        this.idRegiao = idRegiao;
        this.localizacao = localizacao;
        this.precoCarregamento = precoCarregamento;
        idPosto=contaPosto++;
    }

    public int getIdPosto() {
        return idPosto;
    }

    public void setIdPosto(int idPosto) {
        this.idPosto = idPosto;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getPrecoCarregamento() {
        return precoCarregamento;
    }

    public void setPrecoCarregamento(double precoCarregamento) {
        this.precoCarregamento = precoCarregamento;
    }
    
    
}
