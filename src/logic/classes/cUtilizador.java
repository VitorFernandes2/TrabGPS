
package logic.classes;

public class cUtilizador {
    
    private static  int iContaUser = 1;
    private int iIdUtilizador;
    private String sUsername;
    private String sPassword;

    public cUtilizador(String username, String password) {
        this.sUsername = username;
        this.sPassword = password;
        iIdUtilizador = iContaUser++;
    }

    public int getIdUtilizador() {
        return iIdUtilizador;
    }


    public String getUsername() {
        return sUsername;
    }

    public void setUsername(String username) {
        this.sUsername = username;
    }

    public String getPassword() {
        return sPassword;
    }

    public void setPassword(String password) {
        this.sPassword = password;
    }
    
    
    
}
