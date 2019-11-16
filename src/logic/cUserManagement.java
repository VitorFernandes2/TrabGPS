/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Faz a gestão tanto dos logins como dos Registos. Utiliza users como base de dados temporariamente
 * @author Joao Coelho
 */


public class cUserManagement implements cConstantes{
    
    
    
    ArrayList<cUtilizador> users;

    public cUserManagement(ArrayList<cUtilizador> users) {
        this.users = users;
    }

    public cUserManagement() {
        this.users = new ArrayList<cUtilizador>();
    }
    
    public boolean checkexistance(String sUsername,String sPassword){
        
        for(int i=0; i < users.size();i++){
            
            if(sUsername.equals(users.get(i).getUsername()) && sPassword.equals(users.get(i).getPassword())){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean checkUsername(String sUsername){
        
        for(int i=0; i < users.size();i++){
            
            if(sUsername.equals(users.get(i).getUsername())){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean NewRegistraton (String sUsername,String sPassword,String sPassVerif){
        
        if(checkUsername(sUsername)){ 
            return false;
            
        }else{
            if(checkInputedUsername(sUsername) && checkInputedPassword(sPassword) && (sPassword.equals(sPassVerif))){
                
                users.add(new cUtilizador(sUsername,sPassword));
                return true;
                
            }
 
        }

        return false;
    }
    
    public boolean checkInputedUsername(String sStringUsername){
        // verifica se as strings recebidas estão de acordo com os parametros definidos
        
        if(sStringUsername.length() <= MAXUSERNCHAR && sStringUsername.length() != 0 && !hasspecialcharacters(sStringUsername))
            return true;

        return false;
    }
    
    public boolean checkInputedPassword(String sStringPassword){
        // verifica se as strings recebidas estão de acordo com os parametros definidos
        
        if(sStringPassword.length() <= MAXPASSNCHAR && !hasspecialcharacters(sStringPassword) && sStringPassword.length() != 0)
            return true;
        
        return false;
    }
    
    
    public boolean hasspecialcharacters(String sString){
        
        // procura caracteres especiais. Retorna true se encontrar
        
        Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(sString);
        boolean b = m.find();

        if (b)
           return true;
        
       return false;        
    }
}
