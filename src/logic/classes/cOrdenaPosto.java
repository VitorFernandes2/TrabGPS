/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Lu�s Ant�nio Moreira Ferreira da Silva
 */
public class cOrdenaPosto {
    
    private ArrayList<cPosto> cPostoList;
    private ArrayList<cRegiao> cRegiaoList;
    private String sCidade;
    private String sRegiao;

    //Organiza��o a partir do local atual do utilizador
    public cOrdenaPosto(ArrayList<cPosto> cPostoList, ArrayList<cRegiao> cRegiaoList) {
        
        this.cPostoList = cPostoList;
        this.cRegiaoList = cRegiaoList;
        
    }

    //Organiza��o a partir do local selecionado pelo utilizador
    public cOrdenaPosto(ArrayList<cPosto> cPostoList, ArrayList<cRegiao> cRegiaoList, String sCidade, String sRegiao) {
        
        this.cPostoList = cPostoList;
        this.cRegiaoList = cRegiaoList;
        this.sCidade = sCidade;
        this.sRegiao = sRegiao;
        
    }
    
    public ArrayList<cPosto> organiza() {
        
        if(this.sCidade == null && this.sRegiao == null){
            
            try {
                
                getUtilizadorArea();
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
                System.out.println("[ERROR]Erro na obten��o da localiza��o atual do utilizador.");
                
            }
            
        }
        
        //Organiza��o por sCidade principal
        for(int i = 0; i < cPostoList.size(); i++){
            
            if(getNomeCidade(cPostoList.get(i).getIdRegiao()).equalsIgnoreCase(this.sCidade)){
                
                cPosto aux = cPostoList.get(i);
                if(i != 0){
                    
                    for(int j = i; j > 0; j--){
                        
                       cPosto aux2 = cPostoList.get(j-1);
                       cPostoList.set(j-1, aux);
                       cPostoList.set(j, aux2);
                       
                    }
                    
                }
                
            }
            
        }
        
        //Organiza��o por scRegiao principal
        for(int i = 0; i < cPostoList.size(); i++){
            
            if(getNomecRegiao(cPostoList.get(i).getIdRegiao()).equalsIgnoreCase(this.sRegiao)){
                
                cPosto aux = cPostoList.get(i);
                if(i != 0){
                    
                    for(int j = i; j > 0; j--){
                        
                       cPosto aux2 = cPostoList.get(j-1);
                       cPostoList.set(j-1, aux);
                       cPostoList.set(j, aux2);
                       
                    }
                    
                }
                
            }
            
        }
        
        return cPostoList;
    }
    
    private void getUtilizadorArea() throws IOException{
        
        URL urlIpapi = new URL("https://urlIpapi.co/json/");
        URLConnection urlcConnection = urlIpapi.openConnection();
        urlcConnection.setRequestProperty("User-Agent", "java-urlIpapi-client");
        BufferedReader brReader = null;
        try{
            
            brReader = new BufferedReader(
                    new InputStreamReader(urlcConnection.getInputStream())
            );
            
        }
        catch(UnknownHostException ex){
            
            ex.printStackTrace();
            System.out.println("[ERROR]Poder� n�o existir conec��o com a Internet.");
            
        }

        String sAuxi;

        StringBuilder sbString = new StringBuilder();
        
        try{
            
            while ((sAuxi = brReader.readLine()) != null) {
                
                sbString.append(sAuxi.replace('{', ' ').replace('}', ' ').replace('"', ' ').trim());
                
            }
            
        }
        catch(NullPointerException ex){
            
            ex.printStackTrace();
            System.out.println("[ERROR]N�o foi recebida nenhuma informa��o da conec��o do utilizador.");
            
        }
        
        String [] sCortes = sbString.toString().replaceAll(" ", "").split(",");
        
        for(int i = 0; i < sCortes.length; i++){
            
            if(this.sCidade != null && this.sRegiao != null){
                
                break;
                
            }
            if(sCortes[i].contains(":")){
                
                String [] sDetalhes = sCortes[i].split(":");
                
                if(sDetalhes[0].equalsIgnoreCase("city")){
                    
                    this.sCidade = sDetalhes[1];
                    
                }
                else if(sDetalhes[0].equalsIgnoreCase("region")){
                    
                    this.sRegiao = sDetalhes[1];
                    
                }
                
            }
            
        }
        
    }
    
    private String getNomeCidade(int iId){
        
        for(cRegiao r : cRegiaoList){
            
            if(r.getIdRegiao()== iId){
                
                return r.getNomeCidade();
                
            }
        }
        
        return null;
        
    }
    
    private String getNomecRegiao(int iId){
        
        for(cRegiao r : cRegiaoList){
            
            if(r.getIdRegiao()== iId){
                
                return r.getNomeRegiao();
                
            }
            
        }
        
        return null;
    }
    
}
