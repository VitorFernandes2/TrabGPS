
package logic;

import org.junit.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class E2UDataTest {
    
    private cE2UData instance;
    private  static List<String> logs;
    
    public E2UDataTest() throws IOException {
        instance = new cE2UData();
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

    
    @Test
    public void testGetListaReservas() {
        
        System.out.println("\n--- Lista Reservas ---");
        
        int result = instance.getListaReservas().size();
        assertTrue( result >= 0);
        logs.add("testGetListaReservas >= 0 -> " + result + "\n");
        System.out.println(logs.toString());
    }

    
    @Test
    public void testGetListaUtilizadores() {
        System.out.println("\n--- Lista Utilizadores ---");

        int result = instance.getListaUtilizadores().size();
        assertTrue( result >= 0);
        logs.add("testGetListaUtilizadores >= 0 -> " + result + "\n");
        System.out.println(logs.toString());
    }
    

    @Test
    public void testGetListaRegioes() {
                System.out.println("\n--- Lista Regiões ---");

        int result = instance.getListaRegioes().size();
        assertTrue( result >= 0);
        logs.add("testGetListaRegioes >= 0 -> " + result + "\n");
        System.out.println(logs.toString());
    }

    @Test
    public void testGetListaTempos() {
                System.out.println("\n--- Lista Tempos ---");

        int result = instance.getListaTempos().size();
        assertTrue( result >= 0);
        logs.add("testGetListaTempos >= 0 -> " + result + "\n");
        System.out.println(logs.toString());
    }

    @Test
    public void testGetListaPostos() {
        
                System.out.println("\n--- Lista Postos ---");

        int result = instance.getListaPostos().size();
        assertTrue( result >= 0);
        logs.add("testGetListaPostos >= 0 ->" + result + "\n");
        System.out.println(logs.toString());
        
    }
    
    @Test
    public void testgetListaDisponibilidades() {
        System.out.println("\n--- Lista Disponibilidades ---");

        int result = instance.getListaDisponibilidades().size();
        assertTrue( result >= 0);
        logs.add("testgetListaDisponibilidades >= 0 ->" + result + "\n");
        System.out.println(logs.toString());
        
    }

    @Test
    public void testInicializaListas() {
       System.out.println("\n--- inicializa listas ---");

        instance.inicializaListas();
        assertTrue(instance.getListaPostos().size() > 0);
        assertTrue(instance.getListaRegioes().size() > 0);
        assertTrue(instance.getListaDisponibilidades().size() > 0);
        assertTrue(instance.getListaTempos().size() > 0);
        
    }

    @Test
    public void testVerificaDadosLoginVazio() {
        
        System.out.println("\n--- Login vazio ---");

        boolean result = instance.verificaDadosLogin("", "");
        assertEquals(false, result);
        logs.add("testVerificaDadosLogin : false - " + result + "\n");
        System.out.println(logs.toString());
        
    }
    
    @Test
    public void testVerificaDadosLogin() {
        
       System.out.println("\n--- login com user certo ---");

        String sUsername = "User";
        String sPassword1 = "";
        String sPassword2 = "123";
        String sPassword3 = "1234";
        
        boolean result1 = instance.verificaDadosLogin(sUsername, sPassword1);
        boolean result2 = instance.verificaDadosLogin(sUsername, sPassword2 );
        boolean result3 = instance.verificaDadosLogin(sUsername, sPassword3 );
        
        assertEquals(false, result1);
        logs.add("testVerificaDadosLoginSemPW: false - " + result1 + "\n");
        assertEquals(true, result2);
        logs.add("testVerificaDadosLoginCerto : true - " + result2 + "\n");
        assertEquals(false, result3);
        logs.add("testVerificaDadosLoginPWerrada : false - " + result3 + "\n");
        
        System.out.println(logs.toString());
        
    }
    
    @Test
    public void testVerificaDadosLoginSemUser() {
        
       System.out.println("\n--- login com user errado ---");

        String sUsername = " ";
        String sUsername2 = "teste";
        String sPassword1 = "";
        String sPassword2 = "123";
        String sPassword3 = "1234";
        
        boolean result1 = instance.verificaDadosLogin(sUsername, sPassword1);
        boolean result2 = instance.verificaDadosLogin(sUsername, sPassword2 );
        boolean result3 = instance.verificaDadosLogin(sUsername, sPassword3 );
        boolean result4= instance.verificaDadosLogin(sUsername2, sPassword1);
        boolean result5 = instance.verificaDadosLogin(sUsername2, sPassword2 );
        boolean result6 = instance.verificaDadosLogin(sUsername2, sPassword3 );
        assertEquals(false, result1);
        logs.add("testVerificaDadosLoginSemPW: false - " + result1 + "\n");
        assertEquals(false, result2);
        logs.add("testVerificaDadosLoginCerto : false - " + result2 + "\n");
        assertEquals(false, result3);
        logs.add("testVerificaDadosLoginPWerrada : false - " + result3 + "\n");
        assertEquals(false, result4);
        logs.add("testVerificaDadosLoginPWerrada : false - " + result3 + "\n");
        assertEquals(false, result5);
        logs.add("testVerificaDadosLoginPWerrada : false - " + result3 + "\n");
        assertEquals(false, result6);
        logs.add("testVerificaDadosLoginPWerrada : false - " + result3 + "\n");
        
        System.out.println(logs.toString());
        
    }
    
    @Test
    public void testVerificaUsername() {
        System.out.println("\n--- Existencia Username ---");

        String sUsername = "User";
        String sUsername1 = "teste";
   
        boolean result = instance.verificaUsername(sUsername);
        assertEquals(true, result);        
        logs.add("testVerificaUsername : User - " + result + "\n");

        boolean result1 = instance.verificaUsername(sUsername1);
        assertEquals(false, result1);        
        logs.add("testVerificaUsername : teste - " + result1 + "\n");
        System.out.println(logs.toString());
        
    }
    
    @Test
    public void testCriaRegistoVazio() {  
                System.out.println("\n--- Registo vazio ---");

        boolean result = instance.criaRegisto("", "", "");
        
        assertEquals(false, result);
        logs.add("testCriaRegistoVazio : false - " + result + "\n");
        
        System.out.println(logs.toString());
    }
    
    @Test
    public void testCriaRegistoComNome() {
        
                System.out.println("\n--- registo com nome ---");

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
        
        System.out.println(logs.toString());
    }
    
     @Test
    public void testCriaRegistoSemNome() {
                System.out.println("\n--- registo sem nome ---");

        String sUsername = "";
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
        logs.add("testCriaRegistoSemNomeSemPWs: false - " + result1 + "\n");
        assertEquals(false, result2);
        logs.add("testCriaRegistoSemNomeSemCPW : false - " + result2 + "\n");
        assertEquals(false, result3);
        logs.add("testCriaRegistoSemNomeSemPWIguais : false - " + result3 + "\n");
        assertEquals(false, result4);
        logs.add("testCriaRegistoSemNomeSemPW : false - " + result4 + "\n");
        assertEquals(false, result5);
        logs.add("testCriaRegistoSemNomeComPWs : false - " + result5 + "\n");
        
        System.out.println(logs.toString());
    }
        
    @Test
    public void testVerificaInputUsername() {
        
      System.out.println("\n--- Input username ---");
        
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

    @Test
    public void testVerificaInputPassword() {
        
              System.out.println("\n--- Input password ---");

        String sStringPassword = "";
        String sStringPassword1 = "bruno*";
        String sStringPassword2 = "brunofilipedasilvagomeslopestesteteste";
        String sStringPassword3 = "bruno";
        
        boolean result = instance.verificaInputPassword(sStringPassword);
        boolean result1 = instance.verificaInputPassword(sStringPassword1);
        boolean result2 = instance.verificaInputPassword(sStringPassword2);
        boolean result3 = instance.verificaInputPassword(sStringPassword3);
        
         assertEquals(false, result);
        logs.add("testVerificaInputPasswordVazio : false - " + result + "\n");
         assertEquals(false, result1);
        logs.add("testVerificaInputPasswordSpecial : false - " + result1 + "\n");
         assertEquals(false, result2);
        logs.add("testVerificaInputPasswordMax : false - " + result2 + "\n");
        assertEquals(true, result3);
        logs.add("testVerificaInputPasswordCerto : true - " + result3 + "\n");
       
        System.out.println(logs.toString());
    }

    
    @Test
    public void testHasspecialcharacters() {
      System.out.println("\n--- Caracteres especiais ---");

        
        String sString = "teste";
        String sString2 = "te_st*e";
        
        boolean result = instance.hasspecialcharacters(sString);
        boolean result2 = instance.hasspecialcharacters(sString2);
        
        assertEquals(false, result);
        logs.add("testHasspecialcharacters : false - " + result + "\n");
        assertEquals(true, result2);
        logs.add("testHasspecialcharacters : true - " + result2 + "\n");
        System.out.println(logs.toString());
        
    }

/*
    @Test
    public void testInfoPosto_0args() {
        instance.inicializaListas();
        ArrayList<String> result = instance.infoPosto();
        System.out.println("aqui" + result.size());

        assertNotNull("lista",result);
        logs.add("testInfoPosto_0args : lista not null - " + result + "\n");
                System.out.println(logs.toString());

    }*/

    @Test
    public void testPesquisaTempos() {
        
        System.out.println("\n--- ID Hora inicio ---");
        
        String horaInicio = "9h";
        int result = instance.pesquisaTempos(horaInicio);
        assertEquals(0, result);
        logs.add("testPesquisaTempos id = 0 -> " + result + "\n");
        
        String horaInicio1 = "9h30m";
        int result1 = instance.pesquisaTempos(horaInicio1);
        assertEquals(1, result1);
        logs.add("testPesquisaTempos 9h30m = 1 -> " + result1 + "\n");

         String horaInicio2 = "9 30m";
        int result2 = instance.pesquisaTempos(horaInicio2);
        assertEquals(-1, result2);
        logs.add("testPesquisaTempos 9 30m = -1 -> " + result2 + "\n");

        System.out.println(logs.toString());
        
    }

    @Test
    public void testInfoPostosByPesquisa() {
        System.out.println("\n--- posto by pesquisa---");

        String regiao = null;
        String tempo = null;
        
        String regiao1 = "Cantanhede";
        String tempo1 = null;
        
         String regiao2 = null;
        String tempo2 = "9h";
        
         String regiao3 = "Cantanhede";
        String tempo3 = "9h";
        
        int result = instance.infoPostosByPesquisa(regiao, tempo).size();
        assertEquals(0, result);
        logs.add("testInfoPostosByPesquisa reg: " + regiao + " | tempo: " + tempo + " => "+ result + "\n");
        /*
        int result1 = instance.infoPostosByPesquisa(regiao1, tempo1).size();
        System.out.println( instance.infoPostosByPesquisa(regiao1, tempo1).toString());
        assertEquals(12, result1);
        logs.add("testInfoPostosByPesquisa reg: " + regiao1 + " | tempo: " + tempo1 + " => "+ result1 + "\n");
        
        int result2 = instance.infoPostosByPesquisa(regiao2, tempo2).size();
        assertEquals(4, result2);
        logs.add("testInfoPostosByPesquisa reg: " + regiao2 + " | tempo: " + tempo2 + " => "+ result2 + "\n");
        
        int result3 = instance.infoPostosByPesquisa(regiao3, tempo3).size();
        assertEquals(2, result3);
        logs.add("testInfoPostosByPesquisa reg: " + regiao3 + " | tempo: " + tempo3 + " => "+ result3 + "\n");*/
        System.out.println(logs.toString());
        
        
    }
   /*
    @Test
    public void testGetPosto() {
        System.out.println("getPosto");
        
        String expResult = "Rua S. Pedro";
        String result = instance.getPosto(2);
        assertEquals(expResult, result);
        logs.add("testGetPosto id 1 : Rua S. Pedro: " + result + "\n");
        System.out.println(logs.toString());

    }
*/
    @Test
    public void testGetListaHistorico() {
        System.out.println("\n--- lista historico---");

        int expResult = 0;
        int result = instance.getListaHistorico().size();
        assertEquals(expResult, result);
        logs.add("testGetListaHistorico 0 --> " + result + "\n");
        System.out.println(logs.toString());
    }

    @Test
    public void testGetListaPendentes() {
        System.out.println("\n--- lista pendentes---");

        int expResult = 0;
        int result = instance.getListaPendentes().size();
        assertEquals(expResult, result);
        logs.add("testGetListaPendentes 0 --> " + result + "\n");
        System.out.println(logs.toString());
       
    }

    @Test
    public void testEfetuarReserva() {
        System.out.println("\n--- efetuar reserva---");
        
        String sdados = "";
        boolean result = instance.efetuarReserva(sdados);
        assertEquals(false, result);
        logs.add("testEfetuarReserva sem dados --> " + result + "\n");
        /*
        String sdados1 = "Posto:Rua S.Pedro; Preço:0.99; Intervalo:9h às 9h30m; Estado: Disponivel";
        boolean result1 = instance.efetuarReserva(sdados1);
        assertEquals(true, result1);
        logs.add("testEfetuarReserva sem dados --> " + result + "\n");
        */
        System.out.println(logs.toString());
    }
    /*
    @Test
    public void testCancelarReserva() {
        System.out.println("\n--- cancelar reserva---");
        
        String sdados = "";
        boolean result = instance.cancelarReserva(sdados);
        System.out.println("sadas  " + result);
        assertEquals(false, result);
        logs.add("testCancelarReserva sem dados --> " + result + "\n");
        
        String sdados1 = "Posto:Rua S.Pedro; Preço:0.99; Intervalo:9h às 9h30m; Estado: Disponivel";
        boolean result1 = instance.efetuarReserva(sdados1);
        assertEquals(true, result1);
        logs.add("testEfetuarReserva sem dados --> " + result + "\n");
        
        System.out.println(logs.toString());
    }
    */
    

}
