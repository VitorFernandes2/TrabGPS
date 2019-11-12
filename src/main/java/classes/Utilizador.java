
package classes;

public class Utilizador {
    
    private static  int contaUser = 1;
    private int idUtilizador;
    private String username;
    private String password;

    public Utilizador(String username, String password) {
        this.username = username;
        this.password = password;
        idUtilizador=contaUser++;
    }

    public int getIdUtilizador() {
        return idUtilizador;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
