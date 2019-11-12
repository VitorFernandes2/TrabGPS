
package classes;

public class Regiao {
    private static  int contaRegiao = 1;
    
    private int idRegiao;
    private String nomeRegiao;

    public Regiao(String nomeRegiao) {
        this.nomeRegiao = nomeRegiao;
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
    
    
    
}
