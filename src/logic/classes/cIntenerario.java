/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import logic.E2UData;

/**
 *
 * @author a21270909
 */
public class cIntenerario {
    
    private static ArrayList<String> caminhodetalhado;
    
    public static ArrayList getdirection(String partida,String chegada){
        
            ArrayList directionarray = new ArrayList<>();
            ArrayList output = null;
        
        try {
            
            //ArrayList directionarray = new ArrayList<>();
            
            URL test = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + partida + ",PT&wp.1=" + chegada + ",PT&c=pt-PT&optmz=distance&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");
            
            URLConnection returnado = test.openConnection();
            
            returnado.setRequestProperty ("User-Agent", "java-ipapi-client");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(returnado.getInputStream())
            );
            
            String info = reader.readLine();
            reader.close();
            
            //getintenerario(info);
            //getdistrict(info);
            //getDistance(info);
            output = getsimplepost(partida,chegada,getdistrict(info));
            

        } catch (MalformedURLException ex) {
            System.out.println("Não foi possivel connectar a api de trajetos (MalformedURLException)");
        } catch (IOException ex) {
            System.out.println("Não foi possivel ler os trajetos (IOException)");
        }
        
        return getDirectionFinal(partida, output, chegada);
    }
    
    
    private static ArrayList<String> getDirectionFinal(String partida, ArrayList<cPosto> posto, String chegada){
        int iTamWaypoints = posto.size() + 2;
        ArrayList<String> directionarray = new ArrayList<>();
        List<Calendar> dWaypointDate = new ArrayList<>();
        List<Double> dWaypointDistance = new ArrayList<>();
        
        try {
            
            URL test = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?" + wpCreator(partida, posto, chegada) + "&optmz=distance&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");

            URLConnection returnado = test.openConnection();

            returnado.setRequestProperty ("User-Agent", "java-ipapi-client");
                BufferedReader reader = new BufferedReader(
                    new InputStreamReader(returnado.getInputStream())
            );

            String info = reader.readLine();
            
            caminhodetalhado = getintenerario(info);
            
            dWaypointDate = getTime(info, iTamWaypoints);
            dWaypointDistance = getDisTotal(info, iTamWaypoints);

            reader.close();
            
            for(int i = 0, j = 0; i < iTamWaypoints; i++){
                
                StringBuilder sbFinal = new StringBuilder();
                
                if(i == 0){
                    sbFinal.append("Localização: ").append(partida).append(" Distância: ").append(dWaypointDistance.get(i))
                            .append(" km Horas de Partida: ").append(dWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(dWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(dWaypointDate.get(i).get(Calendar.SECOND));
                }
                else if(i == iTamWaypoints - 1){
                    sbFinal.append("Localização: ").append(chegada).append(" Distância: ").append(dWaypointDistance.get(i))
                            .append(" km Horas de Chegada: ").append(dWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(dWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(dWaypointDate.get(i).get(Calendar.SECOND));
                }
                else{
                    sbFinal.append("Localização: ").append(posto.get(j)).append(" Distância: ").append(dWaypointDistance.get(i))
                            .append(" km Horas de Chegada: ").append(dWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(dWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(dWaypointDate.get(i).get(Calendar.SECOND));
                    j++;
                }
                
                directionarray.add(sbFinal.toString());
            }
            

        } catch (MalformedURLException ex) {
            System.out.println("Não foi possivel connectar a api de trajetos (MalformedURLException)");
        } catch (IOException ex) {
            System.out.println("Não foi possivel ler os trajetos (IOException)");
        }
        
        return directionarray;
    }
    
    public static String wpCreator(String sInicial, ArrayList<cPosto> postos, String sFim){
        
        StringBuilder sb = new StringBuilder();
        String sAtive = "áàãâéèêóòõôçíìîúùû";
        int i = 0;
        sb.append("wp").append(i++).append("=").append(sInicial);
        if(postos != null){
            for(cPosto cpp : postos){
                String sPostoName = cpp.getLocalizacao();
                StringBuilder sbb = new StringBuilder();
                boolean bFound = false;

                for(int iPop = 0; iPop < sPostoName.length(); iPop++){
                    for(int iYup = 0; iYup < sAtive.length(); iYup++){
                        if(Character.compare(sAtive.charAt(iYup),sPostoName.charAt(iPop)) == 0){
                            sbb.append("%20");
                            bFound = true;
                            break;
                        }
                    }
                    if(!bFound){
                        bFound = false;
                        continue;
                    }
                    sbb.append(sPostoName.charAt(iPop));
                }

                sb.append(",PT&wp.").append(i++).append("=").append(sbb.toString()).append(",").append("");//neste ultimo append é onde a função de getNomeRegiao virá
            }
        }
        
        sb.append(",PT&wp.").append(i++).append("=").append(sFim).append(",PT");
        
        return sb.toString();
    }
    
    public static List<Calendar> getTime(String info, int iTamWaypoints) {
        String pesquisa = "," + "\"" + "travelDuration" + "\"" + ":";
        String endstring = ",";
        String endstring2 = "]";
        String endstring3 = "}";
        boolean save = false;
        
        ArrayList<String> timearray = new ArrayList<>();
        
        while(info.contains(pesquisa)){
            
            info = info.substring(info.indexOf(pesquisa)+pesquisa.length());
            
            String copyin = info;
            String msg = copyin.split(endstring)[0];
            if(msg.contains(endstring2) || msg.contains(endstring3)){
                msg = msg.replace("]", "");
                msg = msg.replace("}", "");
            }
            if(save){
                timearray.add(msg);
                save = false;
            }
            if(Integer.parseInt(msg) == 0){
                save = true;
            }
        }
        
        Calendar initial = Calendar.getInstance();
        
        List<Calendar> waypointTime = new ArrayList<>();
        
        waypointTime.add(initial);
        
        for(int i = 0; i < iTamWaypoints - 1; i++){
            initial.setTimeInMillis(initial.getTimeInMillis() + Integer.parseInt(timearray.get(i))*1000);
            waypointTime.add(initial);
        }
        
        return waypointTime;
    }
    
    private static List<Double> getDisTotal(String info, int iTamWaypoints) {
        String pesquisa = "," + "\"" + "travelDistance" + "\"" + ":";
        String endstring = ",";
        double dDistanceTotal = 0.0;
        boolean save = false;
        List<Double> waypointDistance = new ArrayList<>();
        
        ArrayList<String> distancearray = new ArrayList<>();
        
        while(info.contains(pesquisa)){
            
            info = info.substring(info.indexOf(pesquisa)+pesquisa.length());
            
            String copyin = info;
            String msg = copyin.split(endstring)[0];
            if(save){
                distancearray.add(msg);
                save = false;
            }
            if(Double.parseDouble(msg) == 0.0){
                save = true;
            }
        }
        
        waypointDistance.add(0.0);
        
        for(int i = 0; i < iTamWaypoints - 1; i++){
            dDistanceTotal += Double.parseDouble(distancearray.get(i));
            waypointDistance.add(dDistanceTotal);
        }
        
        return waypointDistance;
    }
    
    private static ArrayList getsimplepost(String partida,String chegada,ArrayList<String> arraylist ){
        
        // inicia a classe
        E2UData information = null;
        try{
            information = new E2UData();
        }catch (IOException ex){return null;}
        
        // Inicia o array de retorno
        ArrayList retronaoarray = new ArrayList<>();
        
        // ve o posto mais proximo no distrito atual --> TEMP, falta gps
        for (int i=0; i< information.getListaPostos().size();i++){
            
            if (postotodistrict(information.getListaPostos().get(i).getIdPosto()).equals(partida)){
                retronaoarray.add(information.getPosto(i));
                break;
            }
                
        }
        
        // ve o posto mais proximo nos restantes distritos --> TEMP, falta gps
        for (int i=0; i< arraylist.size();i++){
            
            for(int j = 0; j < information.getListaPostos().size();j++){
            
                if (arraylist.get(i).equals(postotodistrict(information.getListaPostos().get(j).getIdPosto()))){
                    retronaoarray.add(information.getPosto(j));
                    break;
                }
                
            }   
        }
        
        // ve o posto mais proximo no distrito atual --> TEMP, falta gps
        for (int i=0; i< information.getListaPostos().size();i++){
            
            if (postotodistrict(information.getListaPostos().get(i).getIdPosto()).equals(chegada)){
                retronaoarray.add(information.getPosto(i));
                break;
            }
                
        }
        
        return retronaoarray;
    }
    
    private static String postotodistrict (int idregião){
        
        E2UData information = null;
        try{
            information = new E2UData();
        }catch (IOException ex){return "-1";}
        
        return information.getListaRegioes().get(idregião).getNomeRegiao();
        
    }
    
    public static ArrayList<String> getintenerario (String info){
        
        ArrayList directionarray = new ArrayList<String>();
        String pesquisa = "\"" + "text" + "\"" + ":";
        String endstring = "\"" + "}";
        
        
        while(info.contains(pesquisa)){
            
            info = info.substring(info.indexOf(pesquisa)+8);
            
            String copyin = info;
            String msg = copyin.split(endstring)[0];
            
            if(msg.contains("warningType") && msg.contains("AdminDivisionChange")){
                msg = msg.split("\""+",")[0];
                directionarray.add(msg);
            }
            else if(!msg.contains("warningType")){
                directionarray.add(msg);
            }
        }
        
        /*for(int i = 0; i < directionarray.size();i++){
            System.out.println("Pos: " + directionarray.get(i));
        }*/
        return directionarray;
        
    }
    
    public static ArrayList getdistrict (String info){
        
        ArrayList directionarray = new ArrayList<>();
        String pesquisa = "Entering";
        String endstring = "\"" + ",";
        
        
        while(info.contains(pesquisa)){
            
            info = info.substring(info.indexOf(pesquisa)+9);
            
            String copyin = info;
            String msg = copyin.split(endstring)[0];
            directionarray.add(msg);

        }
        
        for(int i = 0; i < directionarray.size();i++){
            System.out.println("Distrito: " + directionarray.get(i));
        }
        
        return directionarray;
        
    }

    private static void getDistance(String info) {

        String distance = null;
        String pesquisa = "travelDistance" + "\"" + ":";
        String endstring = ",";
        
        
        while(info.contains(pesquisa)){
            info = info.substring(info.indexOf(pesquisa)+16);
            
            String copyin = info;
            String msg = copyin.split(endstring)[0];
            distance = msg;
        }
        
        System.out.println("Distancia: " + distance + " km");
            
    }

    public static ArrayList<String> getCaminhodetalhado() {
        return caminhodetalhado;
    }

}
