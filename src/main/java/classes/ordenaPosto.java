/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.util.ArrayList;

/**
 *
 * @author Luís António Moreira Ferreira da Silva
 */
public class ordenaPosto {
    
    private ArrayList<Posto> PostoList;
    private ArrayList<Regiao> RegiaoList;
    private String sCidade;
    private String sRegiao;

    //Organização a partir do local atual do utilizador
    public ordenaPosto(ArrayList<Posto> PostoList, ArrayList<Regiao> RegiaoList) {
        
        this.PostoList = PostoList;
        this.RegiaoList = RegiaoList;
        
    }

    //Organização a partir do local selecionado pelo utilizador
    public ordenaPosto(ArrayList<Posto> PostoList, ArrayList<Regiao> RegiaoList, String sCidade, String sRegiao) {
        
        this.PostoList = PostoList;
        this.RegiaoList = RegiaoList;
        this.sCidade = sCidade;
        this.sRegiao = sRegiao;
        
    }
    
    public ArrayList<Posto> organiza() {
        
        if(this.sCidade == null && this.sRegiao == null){
            
            try {
                
                getUtilizadorArea();
                
            } catch (IOException ex) {
                
                ex.printStackTrace();
                System.out.println("[ERROR]Erro na obtenção da localização atual do utilizador.");
                
            }
            
        }
        
        //Organização por sCidade principal
        for(int i = 0; i < PostoList.size(); i++){
            
            if(getNomeCidade(PostoList.get(i).getIdRegiao()).equalsIgnoreCase(this.sCidade)){
                
                Posto aux = PostoList.get(i);
                if(i != 0){
                    
                    for(int j = i; j > 0; j--){
                        
                       Posto aux2 = PostoList.get(j-1);
                       PostoList.set(j-1, aux);
                       PostoList.set(j, aux2);
                       
                    }
                    
                }
                
            }
            
        }
        
        //Organização por sRegiao principal
        for(int i = 0; i < PostoList.size(); i++){
            
            if(getNomeRegiao(PostoList.get(i).getIdRegiao()).equalsIgnoreCase(this.sRegiao)){
                
                Posto aux = PostoList.get(i);
                if(i != 0){
                    
                    for(int j = i; j > 0; j--){
                        
                       Posto aux2 = PostoList.get(j-1);
                       PostoList.set(j-1, aux);
                       PostoList.set(j, aux2);
                       
                    }
                    
                }
                
            }
            
        }
        
        return PostoList;
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
            System.out.println("[ERROR]Poderá não existir conecção com a Internet.");
            
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
            System.out.println("[ERROR]Não foi recebida nenhuma informação da conecção do utilizador.");
            
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
        
        for(Regiao r : RegiaoList){
            
            if(r.getIdRegiao() == iId){
                
                return r.getNomeCidade();
                
            }
        }
        
        return null;
        
    }
    
    private String getNomeRegiao(int iId){
        
        for(Regiao r : RegiaoList){
            
            if(r.getIdRegiao() == iId){
                
                return r.getNomeRegiao();
                
            }
            
        }
        
        return null;
    }
    
}
