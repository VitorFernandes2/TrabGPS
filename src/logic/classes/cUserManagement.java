/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classes;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *  Faz a gest�o tanto dos logins como dos Registos. Utiliza users como base de dados temporariamente
 * @author Joao Coelho
 */


public class cUserManagement implements cConstantes{

    ArrayList<cUtilizador> lsUsers;

    public cUserManagement(ArrayList<cUtilizador> users) {
        this.lsUsers = users;
    }

    public cUserManagement() {
        this.lsUsers = new ArrayList<cUtilizador>();
        //valor adicionado apenas para testes padrão:
        this.lsUsers.add(new cUtilizador("Vitor", "Fernandes"));
    }
    
    public boolean checkexistance(String sUsername,String sPassword){
        
        for(int i = 0; i < lsUsers.size(); i++){
            
            if(sUsername.equals(lsUsers.get(i).getUsername()) && sPassword.equals(lsUsers.get(i).getPassword())){
                return true;
            }
        }
        
        return false;
    }
    
    public boolean checkUsername(String sUsername){
        
        for(int i = 0; i < lsUsers.size(); i++){
            
            if(sUsername.equals(lsUsers.get(i).getUsername())){
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
                
                lsUsers.add(new cUtilizador(sUsername,sPassword));
                return true;
                
            }
 
        }

        return false;
    }
    
    public boolean checkInputedUsername(String sStringUsername){
        // verifica se as strings recebidas est�o de acordo com os parametros definidos
        
        if(sStringUsername.length() <= MAXUSERNCHAR && sStringUsername.length() != 0 && !hasspecialcharacters(sStringUsername))
            return true;

        return false;
    }
    
    public boolean checkInputedPassword(String sStringPassword){
        // verifica se as strings recebidas est�o de acordo com os parametros definidos
        
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
