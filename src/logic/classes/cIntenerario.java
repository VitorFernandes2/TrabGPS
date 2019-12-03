/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic.classes;

import logic.cE2UData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author a21270909
 */
public class cIntenerario {
    
    private static ArrayList<String> lsCaminhoDetalhado;
    private static ArrayList<String> lsCaminhoaltDetalhado;
    private static double dPartidaLat;
    private static double dPartidaLong;
    private static double dChegadaLat;
    private static double dChegadaLong;
    private static cE2UData e2udData;
    
    public static HashMap<String, ArrayList<String>> getdirection(cE2UData data, String sPartida, String sChegada){

        e2udData = data;
        ArrayList<cPosto> alOutput = null;
        ArrayList<cPosto> alOutput2 = null;
        HashMap<String, ArrayList<String>> hasmapoutput = new HashMap<String, ArrayList<String>>();
        URL urlTest;
        URLConnection urlcReturnado;
        BufferedReader bfReader;
        String sInfo;
        
        try {
            
            urlTest = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?wp.0=" + urlAdaptor(sPartida) + ",PT&wp.1=" + urlAdaptor(sChegada) + ",PT&c=pt-PT&optmz=timeWithTraffic&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");
            
            urlcReturnado = urlTest.openConnection();
            
            urlcReturnado.setRequestProperty ("User-Agent", "java-ipapi-client");
            bfReader = new BufferedReader(
                    new InputStreamReader(urlcReturnado.getInputStream())
            );
            
            sInfo = bfReader.readLine();
            
            bfReader.close();
            
            definegpsiniciais(sInfo);
            
            alOutput = getsimplepost(sPartida, sChegada, getdistrict(sInfo, sPartida));
            
            alOutput2 = getpostoalternativo(sPartida, sChegada, getdistrict(sInfo, sPartida)); // new

        } catch (MalformedURLException ex) {
            System.out.println("[ERROR]Não foi possivel connectar a api de trajetos (MalformedURLException)");
        } catch (IOException ex) {
            System.out.println("[ERROR]Não foi possivel ler os trajetos (IOException)");
        }
        
        hasmapoutput.put("Itenerário Recomendado", getDirectionFinal(sPartida, alOutput, sChegada,1));
        
        hasmapoutput.put("Itenerário Alternativo", getDirectionFinal(sPartida, alOutput2, sChegada,2));
        
        return hasmapoutput;
        //return getDirectionFinal(sPartida, alOutput, sChegada);
    }
    
    public static String getClientCorrentLocation() { //new function
        StringBuilder sbLocalInfo = new StringBuilder();
        String sLine = null;
        URL urlIpapi = null;
        URLConnection urlcConnect = null;
        BufferedReader bfReader = null;
        String sRegion = null;
        String sAux = null;
        
        try {
            urlIpapi = new URL("https://ipapi.co/json/");
        } catch (MalformedURLException ex) {
            System.out.println("[ERROR] Possivel malformação do endereço de pesquisa de localização atual do utilizador.\n" + ex.getMessage());
            return null;
        }

        try {
            urlcConnect = urlIpapi.openConnection();
        } catch (IOException ex) {
            System.out.println("[ERROR] Erro ao conectar com o Servidor para obtenção da localização atual do utilizador.\n" + ex.getMessage());
            return null;
        }
        
        urlcConnect.setRequestProperty ("User-Agent", "java-ipapi-client");
        
        try{
            bfReader = new BufferedReader(new InputStreamReader(urlcConnect.getInputStream()));
        
            while((sLine = bfReader.readLine()) != null){
                if(!(sLine.contains("{") || sLine.contains("}"))){
                    sbLocalInfo.append(sLine.trim());
                }
            }

            bfReader.close();
        }
        catch(IOException ex){
            System.out.println("[ERROR] Excepção na leitura da Localização atual.\n" + ex.getMessage());
            return null;
        }
        
        String [] sSection = sbLocalInfo.toString().trim().split(",\"");
        
        for(String sPart : sSection){
            sAux = sPart.trim().replace("\"", "");
            if(sAux.split(": ")[0].equalsIgnoreCase("region")){
                sRegion = sAux.split(": ")[1];
                break;
            }
        }
        
        return sRegion;
        
    }
    
    private static ArrayList<String> getDirectionFinal(String sPartida, ArrayList<cPosto> alPosto, String sChegada,int icaminho){
        int iTamWaypoints = 2 + alPosto.size();
        ArrayList<String> alDirectionArray = new ArrayList<>();
        List<Calendar> lsWaypointDate = new ArrayList<>();
        List<Double> lsWaypointDistance = new ArrayList<>();
        
        try {
            
            URL urlTest = new URL("http://dev.virtualearth.net/REST/V1/Routes/Driving?" + wpCreator(urlAdaptor(sPartida), alPosto, urlAdaptor(sChegada)) + "&optmz=timeWithTraffic&c=pt-PT&routeAttributes=routePath&key=J4mt4gQdoqBgVNWQ63Vh~phVhHbgLfrfO2Qw2MbTdSA~Anb2YN0sBiq4cxNTMlGfIFFZZnr1UPHwECFbw_G6HbrSIlrZdO6rovqVUOp0SDEg&output=json");

            URLConnection urlcReturnado = urlTest.openConnection();

            urlcReturnado.setRequestProperty ("User-Agent", "java-ipapi-client");
                BufferedReader bfReader = new BufferedReader(
                    new InputStreamReader(urlcReturnado.getInputStream())
            );

            String sInfo = bfReader.readLine();
            
            if(icaminho == 2)
                lsCaminhoaltDetalhado = getintenerario(sInfo);
            else
                lsCaminhoDetalhado = getintenerario(sInfo);
            
            lsWaypointDate = getTime(sInfo);
            lsWaypointDistance = getDisTotal(sInfo);

            bfReader.close();
            
            for(int i = 0, j = 0; i < iTamWaypoints; i++){
                
                StringBuilder sbFinal = new StringBuilder();
                
                if(i == 0){
                    sbFinal.append("Localização: ").append(sPartida).append(" Distância: ").append(lsWaypointDistance.get(i).floatValue())
                            .append(" km Horas de Partida: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                }
                else if(i == iTamWaypoints - 1){
                    sbFinal.append("Localização: ").append(sChegada).append(" Distância: ").append(lsWaypointDistance.get(i).floatValue())
                            .append(" km Horas para Chegada: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                }
                else{
                    sbFinal.append("Localização: ").append(alPosto.get(j).getLocalizacao()).append(" Distância: ").append(lsWaypointDistance.get(i).floatValue())
                            .append(" km Horas para Chegada: ").append(lsWaypointDate.get(i).get(Calendar.HOUR_OF_DAY))
                            .append(":").append(lsWaypointDate.get(i).get(Calendar.MINUTE)).append(":")
                            .append(lsWaypointDate.get(i).get(Calendar.SECOND));
                    j++;
                }
                
                alDirectionArray.add(sbFinal.toString());
            }
            
        } catch (MalformedURLException ex) {
            System.out.println("[ERROR]Não foi possivel connectar a api de trajetos (MalformedURLException)v0.2");
        } catch (IOException ex) {
            System.out.println("[ERROR]Não foi possivel ler os trajetos (IOException)v0.2");
        }
        
        return alDirectionArray;
    }
    
    private static String urlAdaptor(String sWaypoint){
        StringBuilder sbUrlName = new StringBuilder();
        int iIndex;
        for(iIndex = 0; iIndex < sWaypoint.length(); iIndex++){
            if(sWaypoint.charAt(iIndex) == ' '){
                sbUrlName.append("%20");
            }
            else{
                sbUrlName.append(sWaypoint.charAt(iIndex));
            }
        }
        return sbUrlName.toString();
    }
    
    private static String wpCreator(String sInicial, ArrayList<cPosto> alPosto, String sFim){
        StringBuilder sbLink = new StringBuilder();
        String sRegNome;
        int i = 0;
        sbLink.append("wp.").append(i++).append("=").append(sInicial.trim());
        if(alPosto != null){
            for(cPosto cpostoPP : alPosto){
                String sPostoName = cpostoPP.getLocalizacao();
                StringBuilder sbB = new StringBuilder();

                for(int iPop = 0; iPop < sPostoName.length(); iPop++){
                    if(Character.compare(sPostoName.charAt(iPop), ' ') == 0){
                        sbB.append("%20");
                    }
                    else{
                        sbB.append(sPostoName.charAt(iPop));
                    }
                }
                
                if((sRegNome = getRegiaoNome(cpostoPP)) != null){
                    sbLink.append(",PT&wp.").append(i++).append("=").append(sbB.toString()).append(",").append(sRegNome);
                }
            }
        }
        
        sbLink.append(",PT&wp.").append(i++).append("=").append(sFim.trim()).append(",PT");
        
        return sbLink.toString();
    }
    
    private static String getRegiaoNome(cPosto cpostoPP){
        
        for(cRegiao cregiaoRR :  e2udData.getListaRegioes()){
            if(cregiaoRR.getIdRegiao() == cpostoPP.getIdRegiao()){
                return cregiaoRR.getNomeRegiao();
            }
        }
        
        return null;
    }
    
    public static List<Calendar>getTime(String sInfo) {
        int soma = 0;
        Calendar cdlDate = Calendar.getInstance();
        String sPesquisa = "," + "\"" + "travelDuration" + "\"" + ":";
        String sEndString3 = "}],";
        ArrayList<Calendar> lsWayPointTime = new ArrayList<>();
        ArrayList<String> alTimeArray = new ArrayList<>();
        String [] sCut = sInfo.split("startWaypoint");
        for(int i = 1; i < sCut.length; i++){
            alTimeArray.add(sCut[i].split(sPesquisa)[1].split(sEndString3)[0]);
        }
        
        cdlDate.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0, 0, 0);
        
        lsWayPointTime.add(cdlDate);
        
        for(int i = 0; i < alTimeArray.size(); i++){
            soma += Integer.valueOf(alTimeArray.get(i));
            Calendar cdlDatenew = Calendar.getInstance();
            cdlDatenew.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0, 0, 0);
            cdlDatenew.setTimeInMillis(cdlDatenew.getTimeInMillis() + soma * 1000);
            lsWayPointTime.add(cdlDatenew);
        }
        
        soma += Integer.valueOf(sInfo.split("\"travelDurationTraffic\":")[1].split(",")[0]);
        
        Calendar cldNewCalendar = Calendar.getInstance();
        cldNewCalendar.set(Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, 0, 0, 0);
        
        cldNewCalendar.setTimeInMillis(cldNewCalendar.getTimeInMillis() + soma * 1000);
        
        lsWayPointTime.add(cldNewCalendar);
        
        return lsWayPointTime;
    }
    
    private static List<Double> getDisTotal(String sInfo) {
        String sPesquisa = "}," + "\"" + "travelDistance" + "\"" + ":";
        String sEndString = ",";
        Double dDistanceTotal = 0.0;
        List<Double> lsWayPointDistance = new ArrayList<>();
        ArrayList<String> sDistanceArray = new ArrayList<>();
        String [] sCut = sInfo.split("startWaypoint");
        
        for(int i = 1; i < sCut.length; i++){
            sDistanceArray.add(sCut[i].split(sPesquisa)[1].split(sEndString)[0]);
        }
        
        lsWayPointDistance.add(dDistanceTotal);
        
        for(int i = 0; i < sDistanceArray.size(); i++){
            dDistanceTotal += Double.valueOf(sDistanceArray.get(i));
            lsWayPointDistance.add(dDistanceTotal);
        }
        
        lsWayPointDistance.add(getDistance(sInfo));
        
        return lsWayPointDistance;
    }
    
    private static String getdistritopeloposto (int ipos){
        
        int iidregiao = e2udData.getListaPostos().get(ipos).getIdRegiao();
        int iiddestrito = e2udData.getListaRegioes().get(iidregiao -1).getiIdDistrito();
        
        return e2udData.getLlistaDistritos().get(iiddestrito - 1).getDistrito();
        
    }
    
    private static ArrayList<cPosto> getsimplepost(String sPartida,String sChegada,ArrayList<String> alArrayList){
        
        // Inicia o array de retorno
        ArrayList<cPosto> alRetronaArray = new ArrayList<>();
        
        
        // ve o posto mais proximo no distrito atual
        double dCloser = -1;
        int iPosCloser = -1;
        for (int i=0; i<  e2udData.getListaPostos().size();i++){
            
            if (getdistritopeloposto(i).equals(sPartida)){
                              
                if(dCloser == -1){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude()) < dCloser){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }
                //alRetronaArray.add( e2udData.getPosto(i));
                //break;
            }   
        }
        if(iPosCloser!=-1){
            alRetronaArray.add( e2udData.getListaPostos().get(iPosCloser));
        }
        
        
        // ve o posto mais proximo nos restantes distritos
        for (int i=0; i< alArrayList.size();i++){
            dCloser = -1;
            iPosCloser = -1;
            for(int j = 0; j <  e2udData.getListaPostos().size();j++){

                if (alArrayList.get(i).equals(getdistritopeloposto(j))){
                    
                    if(dCloser == -1){
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude()) < dCloser){
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }
                }
            }
            if(iPosCloser!=-1){
            alRetronaArray.add( e2udData.getListaPostos().get(iPosCloser));
            }
        }
        
        return alRetronaArray;
    }
    
    private static ArrayList<cPosto> getpostoalternativo(String sPartida,String sChegada,ArrayList<String> alArrayList){
        
        // Inicia o array de retorno
        ArrayList<cPosto> alRetronaArray = new ArrayList<>();
        boolean bentrei = false;
        
        // ve o posto mais proximo no distrito atual
        double dCloser = -1;
        int iPosCloser = -1;
        for (int i=0; i<  e2udData.getListaPostos().size();i++){
            
            if (getdistritopeloposto(i).equals(sPartida)){

                if(dCloser == -1){
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude()) < dCloser){
                    
                    if(bentrei == false){
                    alRetronaArray.add(e2udData.getListaPostos().get(iPosCloser));
                    bentrei = true;
                    }
                    dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(i).getLatitude(), e2udData.getListaPostos().get(i).getLongitude());
                    iPosCloser = i;
                }
                //alRetronaArray.add( e2udData.getPosto(i));
                //break;
            }   
        }
        if(iPosCloser!=-1 && bentrei == false){
            alRetronaArray.add(e2udData.getListaPostos().get(iPosCloser));
        }
        
        
        // ve o posto mais proximo nos restantes distritos
        for (int i=0; i< alArrayList.size();i++){
            dCloser = -1;
            iPosCloser = -1;
            boolean bentreiaqui = false;
            for(int j = 0; j <  e2udData.getListaPostos().size();j++){

                if (alArrayList.get(i).equals(getdistritopeloposto(j))){
                    
                    if(dCloser == -1){
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }else if (twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude()) < dCloser){
                        
                        if(bentrei == false){
                            alRetronaArray.add(e2udData.getListaPostos().get(iPosCloser));
                            bentreiaqui = true;
                            bentrei = true;
                        }
                        dCloser = twopointsDistance(dPartidaLat,dPartidaLong, e2udData.getListaPostos().get(j).getLatitude(), e2udData.getListaPostos().get(j).getLongitude());
                        iPosCloser = j;
                    }
                }
            }
            if(iPosCloser!=-1 && bentreiaqui == false){
                alRetronaArray.add(e2udData.getListaPostos().get(iPosCloser));
            }

        }
        
        return alRetronaArray;
    }
    /*
    private static String postotodistrict (int iIdPorto){
        
        //return  e2udData.getListaRegioes().get(iIdRegiao).getNomeRegiao();
        return e2udData.getListaPostos().get(iIdPorto).getDistrito().sDistrito;
    }
    */
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
    
    public static ArrayList<String> getdistrict (String sInfo, String sPartida){
        
        ArrayList<String> alDirectionArray = new ArrayList<>();
        String sPesquisa = "Entrada";
        String sEndString = "\"" + ",";
        
        
        while(sInfo.contains(sPesquisa)){
            
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+11);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            
            if(sMsg.compareToIgnoreCase(sPartida) != 0){
                alDirectionArray.add(sMsg);
            }

        }
        
        return alDirectionArray;
        
    }

    private static Double getDistance(String sInfo) {

        String sDistance = null;
        String sPesquisa = "travelDistance" + "\"" + ":";
        String sEndString = ",\"travelDuration\":";
        
        
        while(sInfo.contains(sPesquisa)){
            sInfo = sInfo.substring(sInfo.indexOf(sPesquisa)+16);
            
            String sCopyin = sInfo;
            String sMsg = sCopyin.split(sEndString)[0];
            sDistance = sMsg;
        }
        
        return Double.valueOf(sDistance);
            
    }

    public static ArrayList<String> getCaminhodetalhado(int iopc) {
        if(iopc == 2)
            return lsCaminhoaltDetalhado;
        else
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
        
        dPartidaLong = Double.parseDouble(sDistance.split(",")[0]);
        dPartidaLat = Double.parseDouble(sDistance.split(",")[1]);
        dChegadaLong = Double.parseDouble(sDistance.split(",")[2]);
        dChegadaLat = Double.parseDouble(sDistance.split(",")[3]);
    }

    private static double twopointsDistance(double dPartidaLat, double dPartidaLong, double dLatitude, double dLongitude) {

        return Math.sqrt(Math.pow(dLatitude - dPartidaLat, 2) + Math.pow(dLongitude - dPartidaLong, 2));
    
    }

}
