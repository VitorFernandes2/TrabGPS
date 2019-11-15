
package classes;

public class cRegiao {
    private static  int icontaRegiao = 1;
    
    private int iidRegiao;
    private String snomeRegiao;
    private String snomeCidade;

    public cRegiao(String nomeRegiao, String nomeCidade) {
        this.snomeRegiao = nomeRegiao;
        this.snomeCidade = nomeCidade;
        iidRegiao=icontaRegiao++;
    }

    public int getIdRegiao() {
        return iidRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.iidRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return snomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.snomeRegiao = nomeRegiao;
    }
    
    public String getNomeCidade() {
        return snomeCidade;
    }
    
    public void setNomeCidade(String nomeCidade) {
        this.snomeCidade = nomeCidade;
    }
    
}
