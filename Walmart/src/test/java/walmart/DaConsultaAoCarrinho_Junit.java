package walmart;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;

@RunWith(Parameterized.class)
public class DaConsultaAoCarrinho_Junit {
	String baseUrl;
	WebDriver driver;
	String pastaPrint = new SimpleDateFormat("yyyy-MM-dd-mm").format(Calendar.getInstance().getTime());
	static String caminhoFirefox;
	static String caminhoIe;
	static String caminhoChrome;
	static FirefoxOptions ffoptions;

	// Métodos de Apoio
	public void Print(String nomePrint) throws IOException {
		File foto = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(foto, new File("C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\target\\evidencias\\"
				+ pastaPrint + "\\" + nomePrint + ".png"));
	}

	// Métodos de Leitura de Dados
	// Atributos de Massa de Teste
	private String id;
	private String termo;
	private String resultado;
	private String browser;

	// Construtor
	public DaConsultaAoCarrinho_Junit(String id, String termo, String resultado, String browser) {
		this.id = id;
		this.termo = termo;
		this.resultado = resultado;
		this.browser = browser;
	}

	@Parameters
	public static Collection<String[]> LerArquivo() throws IOException {
		return LerCSV("C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\DB\\massas.csv");

	}

	// Não Mexa

	private static Collection<String[]> LerCSV(String nomeMassa) throws IOException {
		List<String[]> dados = new ArrayList<String[]>();
		{
			String linha;
			BufferedReader arquivo = new BufferedReader(new FileReader(nomeMassa));
			while ((linha = arquivo.readLine()) != null) {
				String campos[] = linha.split(";");
				dados.add(campos);
			}
			arquivo.close();

		}

		return dados;
	}

	@BeforeClass
	public static void AntesDeTudo() {
		// Apontar para os drivers
		// Chrome
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\Drivers\\Chrome\\74\\chromedriver.exe");

		// Firefox
		ffoptions = new FirefoxOptions();
		ffoptions.setLegacy(true);
		caminhoFirefox = "C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\Drivers\\Firefox\\geckodriver-v0.24.0-win64\\geckodriver.exe";
		System.setProperty("webdriver.geko.driver", caminhoFirefox);
        
		// Internet Explorer
		System.setProperty("webdriver.ie.driver",
				"C:\\Users\\Alexandre\\eclipse-workspace\\Walmart\\Drivers\\IE\\3.14.0\\32\\IEDriverServer_Win32_3.14.0\\IEDriverServer.exe");

	}

	@Before
	public void iniciar() {
		baseUrl = "https://www.walmart.com.br";

		switch (browser) {
		case "chrome":
			driver = new ChromeDriver();
			break;
		case "firefox":
			driver = new FirefoxDriver(ffoptions);
			break;
		case "ie":
			driver = new InternetExplorerDriver();
			break;
			
					}
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@After
	public void Finalizar() {
		driver.quit();
	}

	@Test
	public void que_acesso_o_site_Walmart() throws Throwable {
		driver.get(baseUrl);
		System.out.println("Passo 1 - Abriu o site");
		Print("TC0021 - Caminho Feliz - " + id + " - Passo1 - Home Walmart");
		driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(Keys.ENTER));
		driver.findElement(By.id("suggestion-search")).clear();
		// Usando o parametro termo a partir do arquivo feature
		// driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(termo));
		// Usando u,a funcão de leitura de arquivo CSV
		driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(termo));
		System.out.println("Passo 2 - Abriu o site");
		Print("TC0021 - Caminho Feliz - " + id + " - Passo2 - Home Walmart");
		driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(Keys.ENTER));
		// Usando o parametro resultado a partir do arquivo feature
		// String resultadoEsperado = "Resultado de busca para \ "" + Resultado + "\ ""
		String resultadoEsperado = "Resultados de busca para \"" + resultado + "\"";
		String resultadoObtido = driver.findElement(By.cssSelector("h1.search-title")).getText();
		assertEquals(resultadoEsperado, resultadoObtido);
		System.out.println("Passo 3 - Abriu o site");
		Print("TC0021 - Caminho Feliz - " + id + " - Passo3 - Home Walmart");
		driver.findElement(By.id("suggestion-search")).sendKeys(Keys.chord(Keys.ENTER));

	}

}
