package com.exemplo.produtoapi.pages.logic;

import com.exemplo.produtoapi.pages.maps.ProdutoPageMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProdutoPage extends ProdutoPageMap {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProdutoPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public String obterPrecoDoProduto() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(precoProduto)).getText().trim();
    }

    public void clicarAdicionarNaSacola() {
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(btnAdicionarSacola));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
    }

    public void clicarIrParaSacolaNoModal() {
        WebDriverWait waitModal = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btnModal = waitModal.until(ExpectedConditions.presenceOfElementLocated(btnIrParaSacolaModal));

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnModal);
    }
}