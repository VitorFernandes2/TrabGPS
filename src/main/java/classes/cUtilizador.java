
package classes;

public class cUtilizador {
    
    private static  int icontaUser = 1;
    private int iidUtilizador;
    private String susername;
    private String spassword;

    public cUtilizador(String username, String password) {
        this.susername = username;
        this.spassword = password;
        iidUtilizador=icontaUser++;
    }

    public int getIdUtilizador() {
        return iidUtilizador;
    }


    public String getUsername() {
        return susername;
    }

    public void setUsername(String username) {
        this.susername = username;
    }

    public String getPassword() {
        return spassword;
    }

    public void setPassword(String password) {
        this.spassword = password;
    }
    
    
    
}
