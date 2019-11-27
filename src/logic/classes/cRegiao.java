
package logic.classes;

public class cRegiao {
    private static  int iContaRegiao = 1;
    
    private int iIdRegiao;
    private String sNomeRegiao;
   

    public cRegiao(String nomeRegiao) {
        this.sNomeRegiao = nomeRegiao;
        iIdRegiao = iContaRegiao++;
    }

    public int getIdRegiao() {
        return iIdRegiao;
    }

    public void setIdRegiao(int idRegiao) {
        this.iIdRegiao = idRegiao;
    }

    public String getNomeRegiao() {
        return sNomeRegiao;
    }

    public void setNomeRegiao(String nomeRegiao) {
        this.sNomeRegiao = nomeRegiao;
    }
  
    
}
