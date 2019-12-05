package logic;

import logic.classes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static logic.classes.cConstantes.*;
import static logic.classes.cItinerario.getClientCorrentLocation;

public class cE2UData {

    private ArrayList<cReserva> listaReservas;
    private ArrayList<cUtilizador> listaUtilizadores;
    private ArrayList<cRegiao> listaRegioes = new ArrayList<>(); 
    private ArrayList<cIntervaloTempo> listaTempos = new ArrayList<>(); 
    private ArrayList<cPosto> listaPostos = new ArrayList<>(); 
    private ArrayList<cDisponibilidadesByTempo> listaDisponibilidades = new ArrayList<>();
    private ArrayList<cDistrito> listadistritos = new ArrayList<>();
    private String sRegiaoUtilizador;
    private static int ierro = 0;
    private int iuserLogado;
    private cLigacaoBD ligacaoBD;
    int iRegiao ;
    
    public ArrayList<cReserva> getListaReservas() {
        return listaReservas;
    }

    public ArrayList<cUtilizador> getListaUtilizadores() {
        return listaUtilizadores;
    }
    
        public ArrayList<cDistrito> getLlistaDistritos() {
        return listadistritos;
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
        return ierro;
    }

    public void setErro(int ierro) {
        this.ierro = ierro;
    }
    
    public cE2UData() throws IOException {
        this.listaReservas = new ArrayList<>();
        listaUtilizadores = new ArrayList<>();
               //getUtilizadorArea();
        iuserLogado=0;        
        ligacaoBD = new cLigacaoBD();
        
        verificaReservas();     
        
    }
    
    public void verificaReservas(){
    
        Date dataAtual = new Date();

        for(cReserva reserva : listaReservas){
        Date dataReserva = new Date(reserva.getData());
        if(dataReserva.compareTo(dataAtual) <= 1)//dataAtual maior
            reserva.setSestado("Efetuada");
        }
        inicializaListas();
        
        
    }
    
    public void inicializaListas(){
           
        listaRegioes =  ligacaoBD.executarSelectRegiao();
        listaTempos = ligacaoBD.executarSelectTempos();
        
        iRegiao = Integer.parseInt(ligacaoBD.executarSelect("Select idRegiao from regiao where nomeRegiao = '" + getClientCorrentLocation()+"'"));
        listaPostos =    ligacaoBD.executarSelectPostos(iRegiao);
        listaDisponibilidades = ligacaoBD.executarSelectDisponibilidades();
        listadistritos = ligacaoBD.executarSelectDistrito();
        
    }
    
    public List<String> infoPosto(int id){
    
        List<String> lista = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
    
        cPosto posto  = listaPostos.get(id);
        for(cDisponibilidadesByTempo c : listaDisponibilidades){
            if(c.getIdPosto() == posto.getIdPosto()){
                sb.append("Posto:" + posto.getLocalizacao() + "; ");
                sb.append("Preço:" + posto.getPrecoCarregamento() + "; ");
                cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                sb.append("Intervalo:"+intervalo.getHoraInicio() + " às " + intervalo.getHoraFim()+ "; Estado: ");
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
        
        String query = "Select * from utilizador  where username='"+sUsername+"' and password = '"+sPassword+"'";
        String resultado = ligacaoBD.executarSelect(query);
        
        if(resultado.equals("ERRO") || resultado.equalsIgnoreCase(""))
            return false;
        
        iuserLogado = Integer.parseInt(resultado);
          
        return true;
    }
    
    public boolean verificaUsername(String sUsername){
                
        String query = "Select * from utilizador  where username='"+sUsername+"'";
        String resultado = ligacaoBD.executarSelect(query);
        
        if(resultado.equals("ERRO") || resultado.equalsIgnoreCase(""))
            return false;
                
        return true;
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
            
            String query = "INSERT INTO utilizador(username, password) VALUES ('"+sUsername+"','"+sPassword+"')";
            if(!ligacaoBD.executarInsert(query))
                return false;
            //listaUtilizadores.add(new cUtilizador(sUsername,sPassword));
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

        listaPostos = ligacaoBD.executarSelectPostos(iRegiao);
        listaDisponibilidades = ligacaoBD.executarSelectDisponibilidades();
        for(cPosto posto : listaPostos) {
            for(cDisponibilidadesByTempo c : listaDisponibilidades) {
                if(c.getIdPosto() == posto.getIdPosto()) {
                    sb.append("Posto:" + posto.getLocalizacao() + "; ");
                    sb.append("Preço:" + posto.getPrecoCarregamento() + "; ");
                    cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                    sb.append("Intervalo:"+intervalo.getHoraInicio() + " às " + intervalo.getHoraFim()+ "; Estado: ");
                    
                    if(c.isDisponibilidade()) {
                        sb.append("Disponivel");
                    } else {
                        sb.append("Indisponivel");
                    }
                    
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
                            sb.append("Posto:" + posto.getLocalizacao() + "; ");
                            sb.append("Preço:" + posto.getPrecoCarregamento() + "; ");
                            cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                            sb.append("Intervalo:"+intervalo.getHoraInicio() + " às " + intervalo.getHoraFim()+ "; Estado: ");
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
                        sb.append("Posto:" + posto.getLocalizacao() + "; ");
                        sb.append("Preço:" + posto.getPrecoCarregamento() + "; ");
                        cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                        sb.append("Intervalo:"+intervalo.getHoraInicio() + " às " + intervalo.getHoraFim()+ "; Estado: ");
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
                            sb.append("Posto:" + posto.getLocalizacao() + "; ");
                            sb.append("Preço: " + posto.getPrecoCarregamento() + "; ");
                            cIntervaloTempo intervalo = listaTempos.get(pesquisaIntervaloById(c.getIdIntervaloTempo()));
                            sb.append("Intervalo:"+intervalo.getHoraInicio() + " às " + intervalo.getHoraFim()+ "; Estado: ");
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
    
    public String getPosto(int id){
        for (cPosto item: listaPostos) {
            if (item.getIdPosto() == id)
                return item.getLocalizacao();
        }
        return null;
    }

    public cIntervaloTempo getIntervaloTempoById(int id){

        for (cIntervaloTempo item : listaTempos)
            if (item.getIdIntervalo() == id)
                return item;
        return null;

    }

    public String getHorario(int id){

        cIntervaloTempo intervaloTempo = getIntervaloTempoById(id);

        if (intervaloTempo != null)
            return "das " + intervaloTempo.getHoraInicio() + " às " + intervaloTempo.getHoraFim();
        return "das n às n";

    }

    public HashMap<Integer,HashMap<String,String>> getListaHistorico(){
    
        HashMap<Integer,HashMap<String,String>> lista = new HashMap<Integer,HashMap<String,String>>();
        int conta = 0;
        int i = 1;
        listaReservas = ligacaoBD.executarSelectReservas();

        for(cReserva reserva : listaReservas){
            if(!reserva.getSestado().equalsIgnoreCase("Ativo")){
                HashMap<String,String> aux = new HashMap<String,String>();
                aux.put("info","Posto: " +getPosto(reserva.getIidPosto())+" Data: " + reserva.getDiaReserva() + " " 
                + getHorario(reserva.getIidIntervaloTempo())+" Preço: " + reserva.getDcustoPrevisto() );
                aux.put("estado", reserva.getSestado());
                lista.put(i++,aux);
                conta++;
            }
       }
        if(conta==0)
            setErro(FALTARESERVASHISTORICO);
        return lista;
    }
    
    public List<String> getListaPendentes(){
    
        int iconta = 0;
        List<String> pendentes = new ArrayList<>();
        
        listaReservas = ligacaoBD.executarSelectReservas();
        for(cReserva reserva : listaReservas){
            if(reserva.getSestado().equalsIgnoreCase("Ativo") && reserva.getIidUtilizador() == iuserLogado){
                pendentes.add("Posto:" +getPosto(reserva.getIidPosto())+"; Data: " + reserva.getDiaReserva() + " "
                + getHorario(reserva.getIidIntervaloTempo())+"; Preço: " + reserva.getDcustoPrevisto());
                iconta++;                
            }
        }
        if(iconta==0)
             setErro(FALTARESERVASPENDENTES);
        
        return pendentes;
    }
    
          
    public boolean efetuarReserva(String sdados){
        
        HashMap<String,String> a = resolveMessages(sdados);   
        Integer iidPosto = null, iidIntervalo = null;
        
        for(cPosto posto : listaPostos) {
            if(posto.getLocalizacao().equals(a.get("Posto"))) {
                iidPosto = posto.getIdPosto();
            }
        }
        
        for(cIntervaloTempo intervalo : listaTempos) {
            if((intervalo.getHoraInicio()+" às "+intervalo.getHoraFim()).equals(a.get(" Intervalo"))) {
                iidIntervalo = intervalo.getIdIntervalo();
            }
        }
        
        if(iidPosto == null || iidIntervalo == null) {
            return false;
        }
       
        double preco = Double.parseDouble(a.get(" Preço"));
        
        String query = "INSERT INTO reserva(idUtilizador,custoPrevisto, idPosto, estado, diaReserva,idIntervaloTempo) VALUES ("+iuserLogado+""
                + ","+ preco * 30 + ","+iidPosto+",\'Ativo\',\'"+getData()+"\',"+iidIntervalo+")";
        ligacaoBD.executarInsert(query);

        int idDispP = 0;
        int idDispT = 0;
        for(cDisponibilidadesByTempo dips : listaDisponibilidades) {
            if(dips.getIdPosto() == iidPosto && dips.getIdIntervaloTempo() == iidIntervalo) {
                idDispP = iidPosto;
                idDispT = iidIntervalo;
            }
        }
        if(idDispP!= 0 && idDispT!=0){
            String queryUpdate = "UPDATE disponibilidadesbytempo SET disponibilidade = 1 WHERE idPosto = "+idDispP+" and idIntervaloTempo = "+idDispT;
            ligacaoBD.executarUpdate(queryUpdate);
        }

        return true;
    }
    
     public String getData(){
        GregorianCalendar calendar = new GregorianCalendar();
        int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
        int mes = calendar.get(GregorianCalendar.MONTH);
        int ano = calendar.get(GregorianCalendar.YEAR);
        return dia+"/"+mes+"/"+ano;
    }
    
    public boolean cancelarReserva(String sdados) {
        HashMap<String,String> a = resolveMessages(sdados);
        
        Integer iidPosto = null, iidIntervalo = null;
        listaReservas = ligacaoBD.executarSelectReservas();
        listaDisponibilidades = ligacaoBD.executarSelectDisponibilidades();

        for(cPosto posto : listaPostos) {
            if(posto.getLocalizacao().equals(a.get("Posto")))
                iidPosto = posto.getIdPosto();
        }
        for(cIntervaloTempo intervalo : listaTempos) {
            if((intervalo.getHoraInicio()+" às "+intervalo.getHoraFim()).equals(a.get(" Data").substring(15)))
                iidIntervalo = intervalo.getIdIntervalo();
        }
        if(iidPosto == null || iidIntervalo == null)  {
            return false;
        }
        
        int idDispP = 0;
        int idDispT = 0;
        
        for(cReserva reserva : listaReservas) {
            if(reserva.getSestado().equalsIgnoreCase("Ativo") &&
                reserva.getIidUtilizador() == iuserLogado && reserva.getIidPosto() == iidPosto &&
                reserva.getIidIntervaloTempo() == iidIntervalo) {

                    for(cDisponibilidadesByTempo dips : listaDisponibilidades) {
                        if(dips.getIdPosto() == iidPosto && dips.getIdIntervaloTempo() == iidIntervalo ) {
                             idDispP = iidPosto;
                             idDispT = iidIntervalo;
                        }
                    } 
                if(idDispP!= 0 && idDispT!=0){
                    String queryUpdate = "UPDATE disponibilidadesbytempo SET disponibilidade = 0 WHERE idPosto = "+idDispP+" and idIntervaloTempo = "+idDispT;
                    ligacaoBD.executarUpdate(queryUpdate);
                }

                String queryUpdate = "UPDATE reserva SET estado = 'Cancelada' WHERE idPosto = "+idDispP+" and idIntervaloTempo = "+idDispT;
                ligacaoBD.executarUpdate(queryUpdate);
                return true;
            }
        }
        
        return false;
    }
    
    public static HashMap<String,String> resolveMessages(String message){
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

    public HashMap<String, ArrayList<String>> getdirection(String sPartida, String sChegada){
        return cItinerario.getdirection(this, sPartida, sChegada);
    }

}
