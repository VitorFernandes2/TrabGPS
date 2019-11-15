/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;

/**
 *  Faz a gestão tanto dos logins como dos Registos. Utiliza users como base de dados temporariamente
 * @author Joao Coelho
 */
public class UserManagement {
    
    ArrayList<Utilizador> users;

    public UserManagement(ArrayList<Utilizador> users) {
        this.users = users;
    }

    public UserManagement() {
        this.users = new ArrayList<Utilizador>();
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
                
                users.add(new Utilizador(sUsername,sPassword));
                return true;
                
            }
 
        }

        return false;
    }
    
    public boolean checkInputedUsername(String sString){
        // verifica se as strings recebidas estão de acordo com os parametros definidos
        
        if(sString.length() <= 10)
            return true;
        
        return false;
    }
    
    public boolean checkInputedPassword(String sString){
        // verifica se as strings recebidas estão de acordo com os parametros definidos
        
        if(sString.length() <= 16)
            return true;
        
        return false;
    }
}
