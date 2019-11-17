package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import logic.classes.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static logic.classes.cConstantes.*;

public class E2UData {

    private ArrayList<cReserva> listaReservas;
    private ArrayList<cUtilizador> listaUtilizadores;
     
    private ArrayList<cRegiao> listaRegioes = new ArrayList<>(); 
    private ArrayList<cIntervaloTempo> listaTempos = new ArrayList<>(); 
    private ArrayList<cPosto> listaPostos = new ArrayList<>(); 
    private ArrayList<cDisponibilidadesByTempo> listaDisponibilidades = new ArrayList<>(); 
    private String sRegiaoUtilizador;
    private static int erro = 0;

    private int userLogado;
    public ArrayList<cReserva> getListaReservas() {
        return listaReservas;
    }

    public void setListaReservas(ArrayList<cReserva> listaReservas) {
        this.listaReservas = listaReservas;
    }

    public ArrayList<cUtilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }

    public void setListaUtilizadores(ArrayList<cUtilizador> listaUtilizadores) {
        this.listaUtilizadores = listaUtilizadores;
    }

    public ArrayList<cRegiao> getListaRegioes() {
        return listaRegioes;
    }
    
    public ArrayList<cIntervaloTempo> getListaTempos() {
        return listaTempos;
    }

    public ArrayList<cPosto> getListaPostos() {
        return listaPostos;
    }

    public ArrayList<cDisponibilidadesByTempo> getListaDisponibilidades() {
        return listaDisponibilidades;
    }
    
    public String getsRegiaoUtilizador() {
        return sRegiaoUtilizador;
    }
   
    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }
    
    public E2UData() throws IOException {
        this.listaReservas = new ArrayList<>();
        listaUtilizadores = new ArrayList<>();
        inicializaListas();
        //getUtilizadorArea();
        userLogado=0;
    }
    
    public void inicializaListas(){
        
        listaRegioes.add(new cRegiao("Cantanhede"));// id 1
        listaRegioes.add(new cRegiao("Coimbra"));// id 2
        listaRegioes.add(new cRegiao("Penela"));// id 3
        listaRegioes.add(new cRegiao("Porto"));// id 4
        listaRegioes.add(new cRegiao("Gaia"));// id 5
        listaRegioes.add(new cRegiao("Lisboa"));// id 6
        listaRegioes.add(new cRegiao("Aveiro"));// id 7
        listaRegioes.add(new cRegiao("Leiria"));// id 8
        listaRegioes.add(new cRegiao("Pombal"));// id 9
        listaRegioes.add(new cRegiao("Faro"));// id 10
        
        listaTempos.add(new cIntervaloTempo("9:00","9:30"));// id 1
        listaTempos.add(new cIntervaloTempo("9:30","10:00"));// id 2
        listaTempos.add(new cIntervaloTempo("10:00","10:30"));// id 3
        listaTempos.add(new cIntervaloTempo("10:30","11:00"));// id 4
        listaTempos.add(new cIntervaloTempo("11:00","11:30"));// id 5
        listaTempos.add(new cIntervaloTempo("11:30","12:00"));// id 6
        listaTempos.add(new cIntervaloTempo("12:00","12:30"));// id 7
        listaTempos.add(new cIntervaloTempo("12:30","13:00"));// id 8
        listaTempos.add(new cIntervaloTempo("13:00","13:30"));// id 9
        listaTempos.add(new cIntervaloTempo("13:30","14:00"));// id 10
        
        listaPostos.add(new cPosto(1,"Rua S. Pedro",0.99));// id 1
        listaPostos.add(new cPosto(1,"Rua S. Miguel",0.79));// id 2
        listaPostos.add(new cPosto(3,"Rua de Nove de Julho",1.49));// id 3
        listaPostos.add(new cPosto(4,"Rua dos Clérigos",0.99));// id 4
        listaPostos.add(new cPosto(4,"Rua de José Falcão",1.29));// id 5
        listaPostos.add(new cPosto(6,"Rua de Trás",0.99));// id 6
        listaPostos.add(new cPosto(8,"Rua do Rosário",1.49));// id 7
        listaPostos.add(new cPosto(9,"Rua de Santa Catarina",1.99));// id 8
        listaPostos.add(new cPosto(10,"Rua de Belomonte",2.22));// id 9
        listaPostos.add(new cPosto(10,"Rua dos Bacalhoeiros",2.00));// id 10
        
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,1,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,2,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,3,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,4,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,5,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(1,6,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,1,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,2,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,3,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,4,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,5,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(2,6,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,1,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,2,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,3,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,4,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,5,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(3,6,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(4,1,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(4,2,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(4,3,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(5,4,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(5,5,true));//1
        listaDisponibilidades.add(new cDisponibilidadesByTempo(5,6,true));//1
        
    }
    public List<String> infoPosto(int id){
    
        List<String> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
    
        cPosto posto  = listaPostos.get(id);
        for(cDisponibilidadesByTempo c : listaDisponibilidades){
            if(c.getIdPosto() == posto.getIdPosto()){
                sb.append("Posto: " + posto.getLocalizacao() + " ");
                sb.append("Preço: " + posto.getPrecoCarregamento() + " ");
                cIntervaloTempo intervalo = listaTempos.get(c.getIdIntervaloTempo());
                sb.append(intervalo.getHoraInicio() + " - " + intervalo.getHoraInicio());
                if(c.isDisponibilidade())
                    sb.append("Disponivel");
                else
                    sb.append("Indisponivel");
                lista.add(sb.toString());
            }
        
        }
        return lista;
    }
    
    
    public boolean verificaDadosLogin(String sUsername,String sPassword){
        if(listaUtilizadores.isEmpty())
            return false;
        
        for(int i=0; i < listaUtilizadores.size();i++){
            
            if(sUsername.equals(listaUtilizadores.get(i).getUsername()) && sPassword.equals(listaUtilizadores.get(i).getPassword())){
                userLogado = listaUtilizadores.get(i).getIdUtilizador();
                return true;
            }
        }
        
        return false;
    }
    
    public boolean verificaUsername(String sUsername){
        
        for(int i=0; i < listaUtilizadores.size();i++){
            
            if(sUsername.equals(listaUtilizadores.get(i).getUsername())){
                return true;
            }

        }
        
        return false;
    }
    
    public boolean criaRegisto (String sUsername,String sPassword,String sPassVerif){
        
        if(verificaUsername(sUsername)){ 
            setErro(UTILIZADORJAEXISTE);
            return false;
        }else{
            if(!verificaInputUsername(sUsername)){
                setErro(ERROINPUTUSER);
                return false;
            }
            if(!verificaInputPassword(sPassword)){
                setErro(ERROINPUTPASS);
                return false;
            }
            if(!sPassword.equals(sPassVerif)){
                setErro(PASSWORDSDIFERENTES);
                return false;
            }
            
            listaUtilizadores.add(new cUtilizador(sUsername,sPassword));
            return true;
        }
    }
    
    public boolean verificaInputUsername(String sStringUsername){
        // verifica se as strings recebidas est�o de acordo com os parametros definidos
        if(sStringUsername.length() <= MAXUSERNCHAR && sStringUsername.length() != 0 && !hasspecialcharacters(sStringUsername))
            return true;
        return false;
    }
    
    public boolean verificaInputPassword(String sStringPassword){
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
    
    public ArrayList<cPosto> organiza() {
        
        for(int i = 0; i < listaPostos.size(); i++){
            
            if(getNomecRegiao(listaPostos.get(i).getIdRegiao()).equalsIgnoreCase(this.sRegiaoUtilizador)){
                
                cPosto aux = listaPostos.get(i);
                if(i != 0){
                    for(int j = i; j > 0; j--){
                       cPosto aux2 = listaPostos.get(j-1);
                       listaPostos.set(j-1, aux);
                       listaPostos.set(j, aux2);
                    }
                }
            }
        }
        
        return listaPostos;
    }
    
    private String getNomecRegiao(int iId){
        
        for(cRegiao r : listaRegioes){
            if(r.getIdRegiao()== iId){
                return r.getNomeRegiao();
            }
        }
        
        return null;
    }
    
    /*private void getUtilizadorArea() throws IOException{
        
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
            
            if(sRegiaoUtilizador != null){
                break;
            }
            if(sCortes[i].contains(":")){
                String [] sDetalhes = sCortes[i].split(":");
                
                if(sDetalhes[0].equalsIgnoreCase("region")){
                    this.sRegiaoUtilizador = sDetalhes[1];
                    
                }
            }
        }
    }*/

}
