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
import java.util.List;
import logic.E2UData;

/**
 *
 * @author a21270909
 */
public class cIntenerario {
    
    private static ArrayList<String> lsCaminhoDetalhado;
    private static double dPartidaLat;
    private static double dPartidaLong;
    private static double dChegadaLat;
    private static double dChegadaLong;
    
    public static ArrayList getdirection(String sPartida, String sChegada){
        
        ArrayList alOutput = null;
        
        try {
            
            URL urlTest = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + sPartida + ",PT&wp.1=" + sChegada + ",PT&c=pt-PT&optmz=sDistance&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");
            
            URLConnection urlcReturnado = urlTest.openConnection();
            
            urlcReturnado.setRequestProperty ("User-Agent", "java-ipapi-client");
            BufferedReader bfReader = new BufferedReader(
                    new InputStreamReader(urlcReturnado.getInputStream())
            );
            
            String sInfo = bfReader.readLine();
            bfReader.close();
            
            alOutput = getsimplepost(sPartida, sChegada, getdistrict(sInfo));

            definegpsiniciais(sInfo);
            
        } catch (MalformedURLException ex) {
            System.out.println("[ERROR]Não foi possivel connectar a api de trajetos (MalformedURLException)");
        } catch (IOException ex) {
            System.out.println("[ERROR]Não foi possivel ler os trajetos (IOException)");
        }
        
        return getDirectionFinal(sPartida, alOutput, sChegada);
    }
    
    
    private static ArrayList<String> getDirectionFinal(String sPartida, ArrayList<cPosto> alPosto, String sChegada){
        int iTamWaypoints = 2;
        ArrayList<String> alDirectionArray = new ArrayList<>();
        List<Calendar> lsWaypointDate = new ArrayList<>();
        List<Double> lsWaypointDistance = new ArrayList<>();
        
        try {
            
            URL urlTest = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?" + wpCreator(sPartida, alPosto, sChegada) + "&optmz=sDistance&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");

            URLConnection urlcReturnado = urlTest.openConnection();

            urlcReturnado.setRequestProperty ("User-Agent", "java-ipapi-client");
                BufferedReader bfReader = new BufferedReader(
                    new InputStreamReader(urlcReturnado.getInputStream())
            );

            String sInfo = bfReader.readLine();
            
            lsCaminhoDetalhado = getintenerario(sInfo);
            
            for(cPosto cpostoPP : alPosto){
                if(getRegiaoNome(cpostoPP) != null){
                    iTamWaypoints++;
                }
            }
            
            lsWaypointDate = getTime(sInfo, iTamWaypoints);
            lsWaypointDistance = getDisTotal(sInfo, iTamWaypoints);

            bfReader.close();
            
            for(int i = 0, j = 0; i < iTamWaypoints; i++){
                
                StringBuilder sbFinal = new StringBuilder();
                
                if(i == 0){
                    sbFinal.append("Localização: ").append(sPartida).append(" Distância: ").append(lsWaypointDistance.get(i))
                            .append(" km Horas de Partida: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                }
                else if(i == iTamWaypoints - 1){
                    sbFinal.append("Localização: ").append(sChegada).append(" Distância: ").append(lsWaypointDistance.get(i))
                            .append(" km Horas de Chegada: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                }
                else{
                    sbFinal.append("Localização: ").append(alPosto.get(j)).append(" Distância: ").append(lsWaypointDistance.get(i))
                            .append(" km Horas de Chegada: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                    j++;
                }
                
                alDirectionArray.add(sbFinal.toString());
            }
            

        } catch (MalformedURLException ex) {
            System.out.println("[ERROR]Não foi possivel connectar a api de trajetos (MalformedURLException)");
        } catch (IOException ex) {
            System.out.println("[ERROR]Não foi possivel ler os trajetos (IOException)");
        }
        
        return alDirectionArray;
    }
    
    public static String wpCreator(String sInicial, ArrayList<cPosto> alPosto, String sFim){
        boolean bFound;
        StringBuilder sbLink = new StringBuilder();
        String sAtive = "áàãâéèêóòõôçíìîúùû";
        String sRegNome;
        int i = 0;
        sbLink.append("wp").append(i++).append("=").append(sInicial);
        if(alPosto != null){
            for(cPosto cpostoPP : alPosto){
                String sPostoName = cpostoPP.getLocalizacao();
                StringBuilder sbB = new StringBuilder();
                bFound = false;

                for(int iPop = 0; iPop < sPostoName.length(); iPop++){
                    for(int iYup = 0; iYup < sAtive.length(); iYup++){
                        if(Character.compare(sAtive.charAt(iYup),sPostoName.charAt(iPop)) == 0){
                            sbB.append("%20");
                            bFound = true;
                            break;
                        }
                    }
                    if(!bFound){
                        bFound = false;
                        continue;
                    }
                    sbB.append(sPostoName.charAt(iPop));
                }
                
                if((sRegNome = getRegiaoNome(cpostoPP)) != null){
                    sbLink.append(",PT&wp.").append(i++).append("=").append(sbB.toString()).append(",").append(sRegNome);
                }
            }
        }
        
        sbLink.append(",PT&wp.").append(i++).append("=").append(sFim).append(",PT");
        
        return sbLink.toString();
    }
    
    private static String getRegiaoNome(cPosto cpostoPP){
        E2UData  e2udInformation = null;
        try{
             e2udInformation = new E2UData();
        }catch (IOException ex){return null;}
        
        for(cRegiao cregiaoRR :  e2udInformation.getListaRegioes()){
            if(cregiaoRR.getIdRegiao() == cpostoPP.getIdRegiao()){
                return cregiaoRR.getNomeRegiao();
            }
        }
        
        return null;
    }
    
    public static List<Calendar> getTime(String sInfo, int iTamWaypoints) {
        String sPesquisa = "," + "\"" + "travelDuration" + "\"" + ":";
        String sEndString = ",";
        String sEndString2 = "]";
        String sEndString3 = "}";
        boolean bSave = false;
        
        ArrayList<String> alTimeArray = new ArrayList<>();
        
        while(sInfo.contains(sPesquisa)){
            
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+sPesquisa.length());
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            if(sMsg.contains(sEndString2) || sMsg.contains(sEndString3)){
                sMsg = sMsg.replace("]", "");
                sMsg = sMsg.replace("}", "");
            }
            if(bSave){
                alTimeArray.add(sMsg);
                bSave = false;
            }
            if(Integer.parseInt(sMsg) == 0){
                bSave = true;
            }
        }
        
        Calendar cldInitial = Calendar.getInstance();
        
        List<Calendar> lsWayPointTime = new ArrayList<>();
        
        lsWayPointTime.add(cldInitial);
        
        for(int i = 0; i < iTamWaypoints - 1; i++){
            cldInitial.setTimeInMillis(cldInitial.getTimeInMillis() + Integer.parseInt(alTimeArray.get(i))*1000);
            lsWayPointTime.add(cldInitial);
        }
        
        return lsWayPointTime;
    }
    
    private static List<Double> getDisTotal(String sInfo, int iTamWaypoints) {
        String sPesquisa = "," + "\"" + "travelDistance" + "\"" + ":";
        String sEndString = ",";
        double dDistanceTotal = 0.0;
        boolean bSave = false;
        List<Double> lsWayPointDistance = new ArrayList<>();
        
        ArrayList<String> sDistanceArray = new ArrayList<>();
        
        while(sInfo.contains(sPesquisa)){
            
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+sPesquisa.length());
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            if(bSave){
                sDistanceArray.add(sMsg);
                bSave = false;
            }
            if(Double.parseDouble(sMsg) == 0.0){
                bSave = true;
            }
        }
        
        lsWayPointDistance.add(0.0);
        
        for(int i = 0; i < iTamWaypoints - 1; i++){
            dDistanceTotal += Double.parseDouble(sDistanceArray.get(i));
            lsWayPointDistance.add(dDistanceTotal);
        }
        
        return lsWayPointDistance;
    }
    
    private static ArrayList getsimplepost(String sPartida,String sChegada,ArrayList<String> alArrayList){
        
        // inicia a classe
        E2UData  e2udInformation = null;
        try{
             e2udInformation = new E2UData();
        }catch (IOException ex){return null;}
        
        // Inicia o array de retorno
        ArrayList alRetronaArray = new ArrayList<>();
        
        
        // ve o posto mais proximo no distrito atual
        double dCloser = -1;
        int iPosCloser = -1;
        for (int i=0; i<  e2udInformation.getListaPostos().size();i++){
            
            if (postotodistrict( e2udInformation.getListaPostos().get(i).getIdPosto()).equals(sPartida)){

                if(dCloser == -1){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude()) < dCloser){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }
                //alRetronaArray.add( e2udInformation.getPosto(i));
                //break;
            }   
        }
        if(iPosCloser!=-1){
            alRetronaArray.add( e2udInformation.getPosto(iPosCloser));
        }
        
        
        // ve o posto mais proximo nos restantes distritos
        for (int i=0; i< alArrayList.size();i++){
            dCloser = -1;
            iPosCloser = -1;
            for(int j = 0; j <  e2udInformation.getListaPostos().size();j++){

                if (alArrayList.get(i).equals(postotodistrict( e2udInformation.getListaPostos().get(j).getIdPosto()))){
                    
                    if(dCloser == -1){
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(j).getLatitude(), e2udInformation.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(j).getLatitude(), e2udInformation.getListaPostos().get(j).getLongitude()) < dCloser){
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(j).getLatitude(), e2udInformation.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }
                }
            }
            if(iPosCloser!=-1){
            alRetronaArray.add( e2udInformation.getPosto(iPosCloser));
            }
        }
        
        // ve o posto mais proximo no distrito atual
        dCloser = -1;
        iPosCloser = -1;
        for (int i=0; i<  e2udInformation.getListaPostos().size();i++){
            
            if (postotodistrict( e2udInformation.getListaPostos().get(i).getIdPosto()).equals(sChegada)){
                
                if(dCloser == -1){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude()) < dCloser){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udInformation.getListaPostos().get(i).getLatitude(), e2udInformation.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }
            }
                
        }
        if(iPosCloser!=-1){
            alRetronaArray.add( e2udInformation.getPosto(iPosCloser));
        }
        
        return alRetronaArray;
    }
    
    private static String postotodistrict (int iIdRegiao){
        
        E2UData  e2udInformation = null;
        try{
             e2udInformation = new E2UData();
        }catch (IOException ex){return "-1";}
        
        return  e2udInformation.getListaRegioes().get(iIdRegiao).getNomeRegiao();
        
    }
    
    public static ArrayList<String> getintenerario (String sInfo){
        
        ArrayList alDirectionArray = new ArrayList<String>();
        String sPesquisa = "\"" + "text" + "\"" + ":";
        String sEndString = "\"" + "}";
        
        
        while(sInfo.contains(sPesquisa)){
            
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+8);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            
            if(sMsg.contains("warningType") && sMsg.contains("AdminDivisionChange")){
                sMsg = sMsg.split("\""+",")[0];
                alDirectionArray.add(sMsg);
            }
            else if(!sMsg.contains("warningType")){
                alDirectionArray.add(sMsg);
            }
        }
        
        /*for(int i = 0; i < alDirectionArray.size();i++){
            System.out.println("Pos: " + alDirectionArray.get(i));
        }*/
        return alDirectionArray;
        
    }
    
    public static ArrayList getdistrict (String sInfo){
        
        ArrayList alDirectionArray = new ArrayList<>();
        String sPesquisa = "Entering";
        String sEndString = "\"" + ",";
        
        
        while(sInfo.contains(sPesquisa)){
            
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+9);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            alDirectionArray.add(sMsg);

        }
        
        for(int i = 0; i < alDirectionArray.size();i++){
            System.out.println("Distrito: " + alDirectionArray.get(i));
        }
        
        return alDirectionArray;
        
    }

    private static void getDistance(String sInfo) {

        String sDistance = null;
        String sPesquisa = "travelDistance" + "\"" + ":";
        String sEndString = ",";
        
        
        while(sInfo.contains(sPesquisa)){
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+16);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            sDistance = sMsg;
        }
        
        System.out.println("Distancia: " + sDistance + " km");
            
    }

    public static ArrayList<String> getCaminhodetalhado() {
        return lsCaminhoDetalhado;
    }

    private static void definegpsiniciais(String sInfo) {
        
        String sDistance = null;
        String sPesquisa = "bbox" + "\"" + ":" + "[";
        String sEndString = "]";
        
        while(sInfo.contains(sPesquisa)){
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+7);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            sDistance = sMsg;
        }
        
        System.out.println("Distancia: " + sDistance + " km");
        
        dPartidaLong = Double.parseDouble(sDistance.split(",")[0]);
        dPartidaLat = Double.parseDouble(sDistance.split(",")[1]);
        dChegadaLong = Double.parseDouble(sDistance.split(",")[2]);
        dChegadaLat = Double.parseDouble(sDistance.split(",")[3]);
    }

    private static double twopointsDistance(double dPartidaLat, double dPartidaLong, double dLatitude, double dLongitude) {

        return Math.sqrt(Math.pow(dLatitude - dPartidaLat, 2) + Math.pow(dLongitude - dPartidaLong, 2));
    
    }

}
