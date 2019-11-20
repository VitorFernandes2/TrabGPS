package logic;

import java.io.IOException;
import logic.classes.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
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
    private int erro = 0;

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
        listaUtilizadores.add(new cUtilizador("User","123"));
        
        listaReservas.add(new cReserva(30*1.5,1,1,1));
        listaReservas.add(new cReserva(10*1.5,1,1,1)); 
        listaReservas.get(1).setSestado("Efetuada");
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
        
        listaTempos.add(new cIntervaloTempo("9h00m","9h:30m"));// id 1
        listaTempos.add(new cIntervaloTempo("9h30m","10h:00m"));// id 2
        listaTempos.add(new cIntervaloTempo("10h00m","10h:30m"));// id 3
        listaTempos.add(new cIntervaloTempo("10h30m","11h:00m"));// id 4
        listaTempos.add(new cIntervaloTempo("11h00m","11h:30m"));// id 5
        listaTempos.add(new cIntervaloTempo("11h30m","12h:00m"));// id 6
        listaTempos.add(new cIntervaloTempo("12h00m","12h:30m"));// id 7
        listaTempos.add(new cIntervaloTempo("12h30m","13h:00m"));// id 8
        listaTempos.add(new cIntervaloTempo("13h00m","13h:30m"));// id 9
        listaTempos.add(new cIntervaloTempo("13h30m","14h:00m"));// id 10
        
        listaPostos.add(new cPosto(1,"Rua S. Pedro",0.99, -8.595116, 40.349884));                   // id 1
        listaPostos.add(new cPosto(1,"Rua S. Miguel",0.79, -8.409035324984872, 40.19541569649263)); // id 2
        listaPostos.add(new cPosto(3,"Rua de Nove de Julho",1.49, -8.3992249, 40.1914154));         // id 3
        listaPostos.add(new cPosto(4,"Rua dos Clérigos",0.99, -8.4242921, 40.2017605));             // id 4
        listaPostos.add(new cPosto(4,"Rua de José Falcão",1.29, -8.4307289, 40.201377));            // id 5
        listaPostos.add(new cPosto(6,"Rua de Trás",0.99, -8.6351568, 41.1631267));                  // id 6
        listaPostos.add(new cPosto(8,"Rua do Rosário",1.49, -8.6278043, 41.16463));                 // id 7
        listaPostos.add(new cPosto(9,"Rua de Santa Catarina",1.99, -8.6218613, 41.167398));         // id 8
        listaPostos.add(new cPosto(10,"Rua de Belomonte",2.22, -8.650833, 41.1775625));             // id 9
        listaPostos.add(new cPosto(10,"Rua dos Bacalhoeiros",2.00, -8.6303421, 41.1650502));        // id 10
        
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
                sb.append("Posto: " + posto.getLocalizacao() + " ; ");
                sb.append("Preço: " + posto.getPrecoCarregamento() + " ; ");
                cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                sb.append("Intervalo: "+intervalo.getHoraInicio() + "-" + intervalo.getHoraFim()+ " ; Estado: ");
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

    public int idRegiao(String regiao){

        for(cRegiao r : listaRegioes)
            if(r.getNomeRegiao().equals(regiao))
                return r.getIdRegiao();
        return -1;
    }

    public int idIntervalo(String tempo){

        for(cIntervaloTempo r :listaTempos )
            if(r.getHoraInicio().equals(tempo))
                return r.getIdIntervalo();
        return -1;
    }

    public ArrayList<String> infoPosto(){

        ArrayList<String> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(cPosto posto : listaPostos){
            for(cDisponibilidadesByTempo c : listaDisponibilidades){
                if(c.getIdPosto() == posto.getIdPosto()){
                    sb.append("Posto: " + posto.getLocalizacao() + " ; ");
                    sb.append("Preço: " + posto.getPrecoCarregamento() + " ; ");
                    cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                    sb.append("Intervalo: "+intervalo.getHoraInicio() + "-" + intervalo.getHoraFim()+ " ; Estado: ");
                    if(c.isDisponibilidade())
                        sb.append("Disponivel");
                    else
                        sb.append("Indisponivel");

                    lista.add(sb.toString());
                    sb.delete(0, sb.length());
                }
            }
        }
        return lista;
    }

    public int pesquisaTempos(String horaInicio){
        for (int i = 0; i < listaTempos.size();i++){
            if (listaTempos.get(i).getHoraInicio().equals(horaInicio))
                return i;
        }
        return -1;
    }

    public int pesquisaIntervaloById(int id){

        for (int i = 0; i < listaTempos.size(); i++) {
            if (listaTempos.get(i).getIdIntervalo() == id)
                return i;
        }

        return -1;

    }

    public List<String> infoPostosByPesquisa(String regiao, String tempo){
        int conta = 0;
        List<String> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        if(regiao != null && tempo != null){
            for(cPosto posto : listaPostos){
                if(posto.getIdRegiao() == idRegiao(regiao))
                    for(cDisponibilidadesByTempo c : listaDisponibilidades){
                        if(c.getIdPosto() == posto.getIdPosto() && c.getIdIntervaloTempo()==idIntervalo(tempo)){
                            conta++;
                            sb.append("Posto: " + posto.getLocalizacao() + " ; ");
                            sb.append("Preço: " + posto.getPrecoCarregamento() + " ; ");
                            cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                            sb.append("Intervalo: "+intervalo.getHoraInicio() + "-" + intervalo.getHoraFim()+ " ; Estado: ");
                            if(c.isDisponibilidade())
                                sb.append("Disponivel");
                            else
                                sb.append("Indisponivel");
                            lista.add(sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
            }
            if (conta == 0)
                setErro(SEMPOSTOS);
        }
        else if(regiao == null && tempo != null){
            for(cPosto posto : listaPostos){
                for(cDisponibilidadesByTempo c : listaDisponibilidades){
                    if(c.getIdPosto() == posto.getIdPosto() && c.getIdIntervaloTempo()==idIntervalo(tempo)){
                        conta++;
                        sb.append("Posto: " + posto.getLocalizacao() + " ; ");
                        sb.append("Preço: " + posto.getPrecoCarregamento() + " ; ");
                        cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                        sb.append("Intervalo: "+intervalo.getHoraInicio() + "-" + intervalo.getHoraFim()+ " ; Estado: ");
                        if(c.isDisponibilidade())
                            sb.append("Disponivel");
                        else
                            sb.append("Indisponivel");
                        lista.add(sb.toString());
                        sb.delete(0, sb.length());
                    }
                }
            }
            if(conta==0){
                setErro(SEMPOSTOSINTERVALO);
            }
        }
        else if(regiao != null && tempo == null){
            for(cPosto posto : listaPostos){
                int idRegiao = idRegiao(regiao);
                if(posto.getIdRegiao() == idRegiao){
                    for(cDisponibilidadesByTempo c : listaDisponibilidades){
                        if(c.getIdPosto() == posto.getIdPosto()){
                            conta++;
                            sb.append("Posto: " + posto.getLocalizacao() + " ; ");
                            sb.append("Preço: " + posto.getPrecoCarregamento() + " ; ");
                            cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                            sb.append("Intervalo: "+intervalo.getHoraInicio() + "-" + intervalo.getHoraFim()+ " ; Estado: ");
                            if(c.isDisponibilidade())
                                sb.append("Disponivel");
                            else
                                sb.append("Indisponivel");
                            lista.add(sb.toString());
                            sb.delete(0, sb.length());
                        }
                    }
                }
            }
            if(conta==0){
                setErro(SEMPOSTOSREGIAO);
            }

        }
        else{
            lista.addAll(infoPosto());
        }

        if (conta > 0)
            setErro(PESQUISANORMAL);

        return lista;
    }
    
    public String getPosto(int id){return listaPostos.get(id).getLocalizacao();}
    public String getHorario(int id){
        return "das " + listaTempos.get(id).getHoraInicio() + " às " + listaTempos.get(id).getHoraFim();
    }
    public HashMap<Integer,HashMap<String,String>> getListaHistorico(){
    
        HashMap<Integer,HashMap<String,String>> lista = new HashMap<Integer,HashMap<String,String>>();
        HashMap<String,String> aux = new HashMap<String,String>();
        int conta = 0;
        
        for(cReserva reserva : listaReservas){
            if(!reserva.getSestado().equalsIgnoreCase("Ativo")){
                aux.put("info","Posto: " +getPosto(reserva.getIidPosto())+" Data: " + reserva.getDiaReserva() + " " 
                + getHorario(reserva.getIidIntervaloTempo())+" Preço: " + reserva.getDcustoPrevisto() );
                aux.put("estado", reserva.getSestado());
                lista.put(reserva.getIidReserva(),aux);
                conta++;
            }
       }
        if(conta==0)
            setErro(FALTARESERVASHISTORICO);
        return lista;
    }
    
    public List<String> getListaPendentes(){
    
        int conta = 0;
        List<String> pendentes = new ArrayList<>();
        for(cReserva reserva : listaReservas){
            if(reserva.getSestado().equalsIgnoreCase("Ativa")){
                pendentes.add("Posto: " +getPosto(reserva.getIidPosto())+" Data: " + reserva.getDiaReserva() + " " 
                + getHorario(reserva.getIidIntervaloTempo())+" Preço: " + reserva.getDcustoPrevisto());
            
                conta++;                
            }
        }
        if(conta==0)
             setErro(FALTARESERVASPENDENTES);
        
        return pendentes;
    }
    
          
    public boolean efetuarReserva(String sdados){
        HashMap<String,String> a =ResolveMessages(sdados);
        Integer idPosto = null,idIntervalo = null;
        for(cPosto posto : listaPostos){
            if(posto.getLocalizacao().equals(a.get("Posto")))
                idPosto = posto.getIdPosto();
        }
        for(cIntervaloTempo intervalo : listaTempos){
            if((intervalo.getHoraInicio()+"-"+intervalo.getHoraFim()).equals(a.get("Intervalo")))
                idIntervalo = intervalo.getIdIntervalo();
        }
        if(idPosto == null || idIntervalo == null)return false;
        listaReservas.add(new cReserva(Double.parseDouble(a.get("Preço")) * 30,idPosto,userLogado,idIntervalo));
        return true;
    }
    public boolean cancelarReserva(String sdados){
        HashMap<String,String> a =ResolveMessages(sdados);
        Integer idPosto = null,idIntervalo = null;
        for(cPosto posto : listaPostos){
            if(posto.getLocalizacao().equals(a.get("Posto")))
                idPosto = posto.getIdPosto();
        }
        for(cIntervaloTempo intervalo : listaTempos){
            if((intervalo.getHoraInicio()+"-"+intervalo.getHoraFim()).equals(a.get("Intervalo")))
                idIntervalo = intervalo.getIdIntervalo();
        }
        if(idPosto == null || idIntervalo == null)return false;
        for(cReserva reserva : listaReservas){
            if(reserva.getIidUtilizador() == userLogado && reserva.getIidPosto() == idPosto && reserva.getIidIntervaloTempo() == idIntervalo)
            {  
                reserva.setSestado("Cancelada");    
                return true;
            }
        }
        return false;
    }
    
    public static HashMap<String,String> ResolveMessages(String message){
        StringTokenizer t,tokens = new StringTokenizer(message,";");
        String key,val;
        HashMap<String,String> messages = new HashMap<>();
        while (tokens.hasMoreElements()) {
            t = new StringTokenizer(tokens.nextElement().toString(),":");  
            key = t.nextElement().toString();
            val = t.nextElement().toString();
            messages.put(key, val);
        }
        return messages;
    }

}
