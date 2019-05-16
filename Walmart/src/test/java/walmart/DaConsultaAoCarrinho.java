package walmart;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

//@RunWith(Parameterized.class)
public class DaConsultaAoCarrinho{
    String baseUrl;
    WebDriver driver;
    String pastaPrint = new SimpleDateFormat("yyyy-MM-dd-mm").format(Calendar.getInstance().getTime());
    
    
    //Métodos de Apoio
    public void Print(String nomePrint) throws IOException {
      File foto = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(foto,new File("C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\target\\evidencias\\" + pastaPrint +"\\"  + nomePrint +".png")); 
    }
    
    //Métodos de Leitura de Dados
    //Atributos de Massa de Teste
    private String id="1";
    private String termo="smartphone";
    private String resultado="smartphone";
    private String browser="chrome";
    /*
    //Construtor
    public DaConsultaAoCarrinho(String id, String termo, String resultado, String browser) {
    this.id = id;
	this.termo = termo;
	this.resultado = resultado;
	this.browser = browser;
    }
    @Parameters
    public static Collection <String[]> LerArquivo() throws IOException {
        return LerCSV("C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\DB\\massas.csv"); 	
    	
    } 	
    
    	// Não Mexa
    
    private static Collection<String[]> LerCSV(String nomeMassa) throws IOException {
	  List<String[]> dados = new ArrayList<String[]>(); {
		  String linha;
		  BufferedReader arquivo = new BufferedReader (new FileReader(nomeMassa));
		  while ((linha = arquivo.readLine()) !=null) {
			  String campos[] = linha.split(";");
		      dados.add(campos);
 }
		  arquivo.close();
		  
	  }
    	
    	
    	
		return dados;
	}
	*/
	@Before 
    public void iniciar() {
    	baseUrl = "https://www.submarino.com.br";
    	System.setProperty("webdriver.chrome.driver",
    	"C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\Drivers\\Chrome\\74\\chromedriver.exe");
    	driver = new ChromeDriver();
    	
    }
        

	@After
    public void Finalizar() {
    	driver.quit();
    }
     
    
    @Given("^que acesso o site Submarino$")
    public void que_acesso_o_site_Submarino() throws Throwable {
    	driver.get(baseUrl);
    	System.out.println("Passo 1 - Abriu o site");
    	Print("TC0021 - Caminho Feliz - " + id +" - Passo1 - Home submarino");
    	driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(Keys.ENTER));
    	
            }

    @When("^pesquiso por \"([^\"]*)\" e pressiona Enter$")
    public void pesquiso_por_e_pressiona_Enter(String termo) throws Throwable {
    	driver.findElement(By.id("h_search-input")).clear();
    	// Usando o parametro termo a partir do arquivo feature 	
    	// driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(termo));
    	// Usando u,a funcão de leitura de arquivo CSV
    	driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(termo));
    	System.out.println("Passo 2 - Abriu o site");
    	Print("TC0021 - Caminho Feliz - " + id + " - Passo2 - Home Submarino");
    	driver.findElement(By.id("h_search-input")).sendKeys(Keys.chord(Keys.ENTER));
    }

    @Then("^exibe a lista de produtos relacionados com \"([^\"]*)\"$")
    public void exibe_a_lista_de_produtos_relacionados_com(String resultado) throws Throwable {
    	// Usando o parametro resultado a partir do arquivo feature 
    	// String resultadoEsperado = "Resultado de busca para \ "" + Resultado + "\ ""    	
      String resultadoEsperado = resultado;
      String resultadoObtido = driver.findElement(By.cssSelector("h1.h1.page-title")).getText();
      assertEquals(resultadoEsperado,resultadoObtido);
      assertTrue(resultadoObtido.contains(resultado));

      System.out.println("Passo 3 - Abriu o site");
	  Print("TC0021 - Caminho Feliz - " + id +" - Passo3 - Home Submarino");
	  	
      }
      
    }

