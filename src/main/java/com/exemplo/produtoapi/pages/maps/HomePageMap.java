package com.exemplo.produtoapi.pages.maps;

import org.openqa.selenium.By;

public class HomePageMap {
    protected By inputBusca = By.xpath("//input[contains(@placeholder, 'pet') or contains(@id, 'search')]");
    protected By linkProduto = By.cssSelector("a[data-nomeproduto='Escada Baw & Miaw Grafite para Cães e Gatos']");
}