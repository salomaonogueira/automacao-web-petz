package com.exemplo.produtoapi.pages.logic;

import com.exemplo.produtoapi.pages.maps.HomePageMap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HomePage extends HomePageMap {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void acessarSite(String url) {
        driver.get(url);

        try {
            WebDriverWait waitCookies = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement btnCookies = waitCookies.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[contains(text(), 'Prosseguir') or contains(text(), 'Aceitar')]")
            ));
            btnCookies.click();
        } catch (Exception e) {
            System.out.println("Banner de cookies não apareceu.");
        }
    }

    public void pesquisarProduto(String nomeProduto) {
        wait.until(ExpectedConditions.elementToBeClickable(inputBusca)).sendKeys(nomeProduto, Keys.ENTER);
    }

    public void selecionarProdutoPesquisado() {
        wait.until(ExpectedConditions.elementToBeClickable(linkProduto)).click();
    }
}