
package logic;

import java.io.IOException;
import java.util.*;
import logic.classes.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class E2UDataTest {
    
    private E2UData instance;
    private  static List<String> logs;
    
    public E2UDataTest() throws IOException {
        instance = new E2UData();
        logs = new ArrayList<String>();
    }
    
    @BeforeClass
    public static void setUpClass() {
        
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /*
    @Test
    public void testGetListaReservas() {
        System.out.println("getListaReservas");
        ArrayList<cReserva> expResult = null;
        ArrayList<cReserva> result = instance.getListaReservas();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testSetListaReservas() {
        System.out.println("setListaReservas");
        ArrayList<cReserva> listaReservas = null;
        instance.setListaReservas(listaReservas);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaUtilizadores() {
        System.out.println("getListaUtilizadores");
        ArrayList<cUtilizador> expResult = null;
        ArrayList<cUtilizador> result = instance.getListaUtilizadores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testSetListaUtilizadores() {
        System.out.println("setListaUtilizadores");
        ArrayList<cUtilizador> listaUtilizadores = null;
        instance.setListaUtilizadores(listaUtilizadores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaRegioes() {
        System.out.println("getListaRegioes");
        ArrayList<cRegiao> expResult = null;
        ArrayList<cRegiao> result = instance.getListaRegioes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaTempos() {
        System.out.println("getListaTempos");
        ArrayList<cIntervaloTempo> expResult = null;
        ArrayList<cIntervaloTempo> result = instance.getListaTempos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaPostos() {
        System.out.println("getListaPostos");
        ArrayList<cPosto> expResult = null;
        ArrayList<cPosto> result = instance.getListaPostos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaDisponibilidades() {
        System.out.println("getListaDisponibilidades");
        ArrayList<cDisponibilidadesByTempo> expResult = null;
        ArrayList<cDisponibilidadesByTempo> result = instance.getListaDisponibilidades();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetsRegiaoUtilizador() {
        System.out.println("getsRegiaoUtilizador");
        String expResult = "";
        String result = instance.getsRegiaoUtilizador();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testGetErro() {
        System.out.println("getErro");
        int expResult = 0;
        int result = instance.getErro();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testSetErro() {
        System.out.println("setErro");
        int erro = 0;
        instance.setErro(erro);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInicializaListas() {
        System.out.println("inicializaListas");
        instance.inicializaListas();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testInfoPosto_int() {
        System.out.println("infoPosto");
        int id = 0;
        List<String> expResult = null;
        List<String> result = instance.infoPosto(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testVerificaDadosLogin() {
        System.out.println("verificaDadosLogin");
        String sUsername = "";
        String sPassword = "";
        boolean expResult = true;
        boolean result = instance.verificaDadosLogin(sUsername, sPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testVerificaUsername() {
        System.out.println("verificaUsername");
        String sUsername = "";
        boolean expResult = true;
        boolean result = instance.verificaUsername(sUsername);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
*/
    @Test
    public void testCriaRegistoVazio() {   
        boolean result = instance.criaRegisto("", "", "");
        
        assertEquals(false, result);
        logs.add("testCriaRegistoVazio : false - " + result + "\n");
        
        //System.out.println(logs.toString());
    }
    
    @Test
    public void testCriaRegistoComNome() {
        String sUsername = "Bruno";
        String sPassword1 = "";
        String sPassVerif1 = "";
        String sPassword2 = "123";
        String sPassVerif2 = "";
        String sPassword3 = "123";
        String sPassVerif3 = "1234";
        String sPassword4 = "";
        String sPassVerif4 = "123";
        String sPassword5 = "123";
        String sPassVerif5 = "123";
       
        
        boolean result1 = instance.criaRegisto(sUsername, sPassword1, sPassVerif1);
        boolean result2 = instance.criaRegisto(sUsername, sPassword2, sPassVerif2);
        boolean result3 = instance.criaRegisto(sUsername, sPassword3, sPassVerif3);
        boolean result4 = instance.criaRegisto(sUsername, sPassword4, sPassVerif4);
        boolean result5 = instance.criaRegisto(sUsername, sPassword5, sPassVerif5);
        
        assertEquals(false, result1);
        logs.add("testCriaRegistoNomeSemPWs: false - " + result1 + "\n");
        assertEquals(false, result2);
        logs.add("testCriaRegistoNomeSemCPW : false - " + result2 + "\n");
        assertEquals(false, result3);
        logs.add("testCriaRegistoNomeSemPWIguais : false - " + result3 + "\n");
        assertEquals(false, result4);
        logs.add("testCriaRegistoNomeSemPW : false - " + result4 + "\n");
        assertEquals(true, result5);
        logs.add("testCriaRegistoNomeComPWs : true - " + result5 + "\n");
        
        //System.out.println(logs.toString());
    }

    @Test
    public void testVerificaInputUsername() {
        String sStringUsername = "";
        String sStringUsername1 = "bruno*";
        String sStringUsername2 = "brunofilipedasilvagomeslopestesteteste";
        String sStringUsername3 = "bruno";
        
        boolean result = instance.verificaInputUsername(sStringUsername);
        boolean result1 = instance.verificaInputUsername(sStringUsername1);
        boolean result2 = instance.verificaInputUsername(sStringUsername2);
        boolean result3 = instance.verificaInputUsername(sStringUsername3);
        
         assertEquals(false, result);
        logs.add("testVerificaInputUsernameVazio : false - " + result + "\n");
         assertEquals(false, result1);
        logs.add("testVerificaInputUsernameSpecial : false - " + result1 + "\n");
         assertEquals(false, result2);
        logs.add("testVerificaInputUsernameMax : false - " + result2 + "\n");
        assertEquals(true, result3);
        logs.add("testVerificaInputUsernameCerto : true - " + result3 + "\n");
       
        System.out.println(logs.toString());

    }
/*
    @Test
    public void testVerificaInputPassword() {
        System.out.println("verificaInputPassword");
        String sStringPassword = " ";
        boolean expResult = true;
        boolean result = instance.verificaInputPassword(sStringPassword);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    @Test
    public void testHasspecialcharacters() {
        String sString = "teste";
        String sString2 = "te_st*e";
        
        boolean result = instance.hasspecialcharacters(sString);
        boolean result2 = instance.hasspecialcharacters(sString2);
        
        assertEquals(false, result);
        logs.add("testCriaRegistoNomeComPWs : false - " + result + "\n");
        assertEquals(true, result2);
        logs.add("testCriaRegistoNomeComPWs : true - " + result2 + "\n");
        
    }
    /*

    @Test
    public void testOrganiza() {
        System.out.println("organiza");
        ArrayList<cPosto> expResult = null;
        ArrayList<cPosto> result = instance.organiza();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIdRegiao() {
        System.out.println("idRegiao");
        String regiao = "";
        int expResult = 0;
        int result = instance.idRegiao(regiao);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testIdIntervalo() {
        System.out.println("idIntervalo");
        String tempo = "";
        int expResult = 0;
        int result = instance.idIntervalo(tempo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInfoPosto_0args() {
        System.out.println("infoPosto");
        ArrayList<String> expResult = null;
        ArrayList<String> result = instance.infoPosto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisaTempos() {
        System.out.println("pesquisaTempos");
        String horaInicio = "";
        int expResult = 0;
        int result = instance.pesquisaTempos(horaInicio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testPesquisaIntervaloById() {
        System.out.println("pesquisaIntervaloById");
        int id = 0;
        int expResult = 0;
        int result = instance.pesquisaIntervaloById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testInfoPostosByPesquisa() {
        System.out.println("infoPostosByPesquisa");
        String regiao = "";
        String tempo = "";
        List<String> expResult = null;
        List<String> result = instance.infoPostosByPesquisa(regiao, tempo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    @Test
    public void testGetPosto() {
        System.out.println("getPosto");
        int id = 0;
        String expResult = "";
        String result = instance.getPosto(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetHorario() {
        System.out.println("getHorario");
        int id = 0;
        String expResult = "";
        String result = instance.getHorario(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaHistorico() {
        System.out.println("getListaHistorico");
        HashMap<Integer, HashMap<String, String>> expResult = null;
        HashMap<Integer, HashMap<String, String>> result = instance.getListaHistorico();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetListaPendentes() {
        System.out.println("getListaPendentes");
        List<String> expResult = null;
        List<String> result = instance.getListaPendentes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testEfetuarReserva() {
        System.out.println("efetuarReserva");
        String sdados = "";
        boolean expResult = true;
        boolean result = instance.efetuarReserva(sdados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testCancelarReserva() {
        System.out.println("cancelarReserva");
        String sdados = "";
        boolean expResult = true;
        boolean result = instance.cancelarReserva(sdados);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    */
}
