Feature: Comprar produto no site Petz

  Scenario: Validar o valor do produto no site Petz
    Given que um usuario entra no site "https://www.petz.com.br/"
    When que pesquisa e seleciona o produto "Escada Baw & Miaw Grafite para Cães e Gatos"
    And adiciona o produto na sacola
    Then devera verificar se o valor no carrinho esta correto