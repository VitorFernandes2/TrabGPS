package logic.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List.*;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class cLigacaoBD {
         
   
    static final String USER_BD = "tp_gps";
     static final String NAME_BD = "erecarga";
    static final String PASS_USER_BD = "tp_gps";
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    Connection conn_ligacao = null;
    Statement stmt = null; 
    
    public cLigacaoBD() {
            criarLigacaoBD();
    }
    
    public boolean criarLigacaoBD()
    {        
        try {
           
            Class.forName(JDBC_DRIVER);
            
            conn_ligacao = DriverManager.getConnection("jdbc:mysql://localhost/"+NAME_BD+"?useTimezone=true&serverTimezone=UTC", USER_BD, PASS_USER_BD);
            
        } catch ( SQLException ex) {
            
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public boolean executarInsert(String query)
    {
        try {
            PreparedStatement ps_query = conn_ligacao.prepareStatement(query);
            ps_query.executeUpdate();
        } catch (SQLException e) {
            System.out.println("ERRO [doInBackground]: " + e.getMessage());
            return false;
        }
        
        return true;
    }
    
    public String executarSelect(String query)
    {
        String s_resposta = "ERRO";
        try {
            stmt = conn_ligacao.createStatement();

            s_resposta = "";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                s_resposta += rs.getString(1); //neste caso ainda só é feito um select, será atualizado conforme as necessidades
            }                                              //aparecerem ao longo do programa

        }catch(SQLException e){
            System.out.println("ERRO [doInBackground]: " + e.getMessage());
               return s_resposta;
        }
        
        return s_resposta;
    }
    
     public ArrayList<cVeiculo> executarSelectVeiculos(int id) 
    {
        try {
            ArrayList<cVeiculo> lista = new ArrayList<>();
            String query = "Select * from veiculo";
            System.out.println(query);
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int i = rs.getInt("idVeiculo");
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                String matricula = rs.getString("matricula");
                int pot = rs.getInt("potencia");
                int auto = rs.getInt("autonomia");
                
                lista.add(new cVeiculo(i,modelo,marca,matricula,pot,auto,id));
            }
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<cIntervaloTempo> executarSelectTempos() 
    {
        try {
            ArrayList<cIntervaloTempo> lista = new ArrayList<>();
            String query = "Select * from intervalotempo";

            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("idIntervaloTempo");
                String ini = rs.getString("horaInicio");
                String fim = rs.getString("horaFim");
                lista.add(new cIntervaloTempo(i,ini,fim));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public ArrayList<cRegiao> executarSelectRegiao() 
    {
        try {
            ArrayList<cRegiao> lista = new ArrayList<>();
            String query = "Select * from regiao";
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("idRegiao");
                String n = rs.getString("nomeRegiao");
                int id = rs.getInt("idDistrito");
                lista.add(new cRegiao(i,n,id));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<cDisponibilidadesByTempo> executarSelectDisponibilidades() 
    {
        try {
            ArrayList<cDisponibilidadesByTempo> lista = new ArrayList<>();
            String query = "Select * from disponibilidadesbytempo";
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("idPosto");
                int n = rs.getInt("idIntervaloTempo");
                int id = rs.getInt("disponibilidade");
                if(id==0)
                    lista.add(new cDisponibilidadesByTempo(i,n,true));
                else
                    lista.add(new cDisponibilidadesByTempo(i,n,false));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    
     public ArrayList<cReserva> executarSelectReservas() 
    {
        
        try {
            ArrayList<cReserva> lista = new ArrayList<>();
            String query = "Select * from reserva";
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int idR = rs.getInt("idReserva");
                int idP = rs.getInt("idPosto");
                int idT = rs.getInt("idIntervaloTempo");
                int idU = rs.getInt("idUtilizador");
                double custo = rs.getDouble("custoPrevisto");
                String est = rs.getString("estado");
                String data = rs.getString("diaReserva");

                lista.add(new cReserva(idR,custo,idP,idU,idT,est,data));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         
    public ArrayList<cDistrito> executarSelectDistrito() 
    {
        try {
            ArrayList<cDistrito> lista = new ArrayList<>();
            String query = "Select * from distrito";
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("idDistrito");
                String n = rs.getString("nomeDistrito");
                lista.add(new cDistrito(i,n));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public ArrayList<cPosto> executarSelectPostos(int ordem) 
    {
        try {
            ArrayList<cPosto> lista = new ArrayList<>();
            String query = "Select * from posto ORDER BY idRegiao = " + ordem+" DESC ";
            stmt = conn_ligacao.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int i = rs.getInt("idPosto");
                String n = rs.getString("localizacao");
                int reg = rs.getInt("idRegiao");
                double preco = rs.getDouble("precoCarregamento");
                double lat = rs.getDouble("latitude");
                double lon = rs.getDouble("longitude");
                lista.add(new cPosto(i,reg,n,preco,lon,lat));
            }
            
            return lista;
        } catch (SQLException ex) {
            Logger.getLogger(cLigacaoBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
         
     public boolean executarUpdate(String query)
    {
        try {
            stmt = conn_ligacao.createStatement();
            stmt.executeUpdate(query);

        }catch(SQLException e){
            System.out.println("ERRO [doInBackground]: " + e.getMessage());
               return false;
        }
        
        return true;
    }
    
    
}

