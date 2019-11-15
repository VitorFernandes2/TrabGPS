
package classes;

public class Regiao {
    private static  int contaRegiao = 1;
    
    private int idRegiao;
    private String nomeRegiao;
    private String nomeCidade;

    public Regiao(String nomeRegiao, String nomeCidade) {
        this.nomeRegiao = nomeRegiao;
        this.nomeCidade = nomeCidade;
        idRegiao=contaRegiao++;
    }

    public int getIdRegiao() {
        return idRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.idRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return nomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
    }
    
    public String getNomeCidade() {
        return nomeCidade;
    }
    
    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }
    
}
