package com.exemplo.produtoapi.pages.logic;

import com.exemplo.produtoapi.pages.maps.SacolaPageMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class SacolaPage extends SacolaPageMap {
    private WebDriver driver;
    private WebDriverWait wait;

    public SacolaPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public String obterPrecoTotal() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.presenceOfElementLocated(precoTotal));

        List<WebElement> elementos = driver.findElements(precoTotal);

        for (WebElement el : elementos) {
            String texto = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", el);

            if (texto != null && !texto.trim().isEmpty()) {
                return texto.trim();
            }
        }
        
        return "";
    }
}