
package logic.classes;

public class cRegiao {
    //private static  int iContaRegiao = 1;
    
    private int iIdRegiao;
    private String sNomeRegiao;
    private int iIdDistrito;
   

    public cRegiao(int id, String nomeRegiao, int idDt) {
        this.sNomeRegiao = nomeRegiao;
        iIdRegiao = id;
        iIdDistrito = idDt;
    }

    public int getiIdRegiao() {
        return iIdRegiao;
    }

    public void setiIdRegiao(int iIdRegiao) {
        this.iIdRegiao = iIdRegiao;
    }

    public String getsNomeRegiao() {
        return sNomeRegiao;
    }

    public void setsNomeRegiao(String sNomeRegiao) {
        this.sNomeRegiao = sNomeRegiao;
    }

    public int getiIdDistrito() {
        return iIdDistrito;
    }

    public void setiIdDistrito(int iIdDistrito) {
        this.iIdDistrito = iIdDistrito;
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
