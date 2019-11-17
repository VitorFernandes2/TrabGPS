
package logic.classes;

public class cRegiao {
    private static  int icontaRegiao = 1;
    
    private int iidRegiao;
    private String snomeRegiao;
   

    public cRegiao(String nomeRegiao) {
        this.snomeRegiao = nomeRegiao;       
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
  
    
}
