package com.exemplo.produtoapi.pages.maps;

import org.openqa.selenium.By;

public class ProdutoPageMap {
    protected By precoProduto = By.cssSelector("#ecom-produto-price-default p:first-of-type");
    protected By btnAdicionarSacola = By.xpath("//button[@data-testid='ptz-button-add-to-bag'] | //button[contains(normalize-space(.), 'Adicionar à sacola')]");
    protected By btnIrParaSacolaModal = By.cssSelector("#goToCheckoutButton");
}