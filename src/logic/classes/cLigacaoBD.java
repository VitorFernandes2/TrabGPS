package logic.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            
            System.out.println("Ligando à base de dados ...\n");
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
        System.out.println(query);

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

