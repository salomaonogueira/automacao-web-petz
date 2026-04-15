package steps;

import com.exemplo.produtoapi.pages.logic.HomePage;
import com.exemplo.produtoapi.pages.logic.ProdutoPage;
import com.exemplo.produtoapi.pages.logic.SacolaPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.Collections;

public class ProdutoStepDefinitions {

    private WebDriver driver;
    private HomePage homePage;
    private ProdutoPage produtoPage;
    private SacolaPage sacolaPage;
    private String precoProdutoSalvo;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");

        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);

        if (System.getenv("CI") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/122.0.0.0 Safari/537.36"); // Máscara de SO
        }

        driver = new ChromeDriver(options);
        homePage = new HomePage(driver);
        produtoPage = new ProdutoPage(driver);
        sacolaPage = new SacolaPage(driver);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("que um usuario entra no site {string}")
    public void que_um_usuario_entra_no_site(String url) {
        homePage.acessarSite(url);
    }

    @When("que pesquisa e seleciona o produto {string}")
    public void que_pesquisa_e_seleciona_o_produto(String nomeProduto) {
        homePage.pesquisarProduto(nomeProduto);
        homePage.selecionarProdutoPesquisado();
    }

    @And("adiciona o produto na sacola")
    public void adiciona_o_produto_na_sacola() {
        precoProdutoSalvo = produtoPage.obterPrecoDoProduto();
        produtoPage.clicarAdicionarNaSacola();
        produtoPage.clicarIrParaSacolaNoModal();
    }

    @Then("devera verificar se o valor no carrinho esta correto")
    public void devera_verificar_se_o_valor_no_carrinho_esta_correto() {
        String precoSacola = sacolaPage.obterPrecoTotal();

        String valorProdutoLimpo = precoProdutoSalvo.replace(" ", "");
        String valorSacolaLimpo = precoSacola.replace(" ", "");

        Assertions.assertEquals(
                valorProdutoLimpo,
                valorSacolaLimpo,
                "Erro: O valor no carrinho (" + precoSacola + ") divergiu do valor da página do produto (" + precoProdutoSalvo + ")"
        );
    }
}