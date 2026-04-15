

# 🐾 Automação de Testes E2E - E-commerce Petz

Projeto de automação de testes Web focado na validação do fluxo principal de compras do portal Petz. Desenvolvido com foco em **código limpo, resiliência contra bloqueios (anti-bot) e Integração Contínua (CI/CD)**.

## 🎯 Escopo do Teste
O script automatiza e valida o seguinte fluxo crítico de negócio:
1. Acesso à página inicial do portal.
2. Pesquisa dinâmica de produto.
3. Interação com modais assíncronos e adição do item à sacola.
4. Asserção rigorosa comparando o preço da página de detalhes com o preço final no checkout.

## 🛠️ Stack Tecnológica
* **Linguagem:** Java (suporte a JDK 24+)
* **Orquestração:** Maven
* **Automação Web:** Selenium WebDriver (v4.18.x)
* **Behavior-Driven Development:** Cucumber
* **Motor de Testes:** JUnit 5 (Jupiter) / JUnit 4
* **Pipeline CI/CD:** GitHub Actions (Ubuntu Runner)

## 🏗️ Arquitetura: Padrão Map-Page-Step
Para garantir alta escalabilidade e fácil manutenção, o projeto utiliza o padrão estrutural **Map-Page-Step** (uma evolução direta do Page Object Model):
* 📍 **Maps (`/maps`):** Isolamento total dos localizadores (XPath e CSS Selectors). Qualquer mudança no layout do site é ajustada em um único lugar.
* ⚙️ **Pages (`/logic`):** Encapsula a lógica de interação (cliques, preenchimentos) e esperas explícitas, herdando os elementos do Map. Foca apenas em "como" o robô age.
* 🧩 **Steps (`/steps`):** Realiza a "cola" entre os cenários BDD (Gherkin) e o código Java, sendo o único local onde ocorrem as validações de regra de negócio (`Assertions`).

## 🛡️ Resiliência e Engenharia Anti-Bot
Aplicações modernas de e-commerce possuem bloqueios severos contra automação. Este projeto implementa técnicas avançadas de bypass para rodar com estabilidade em servidores:
* **User-Agent Spoofing:** Simulação de ambiente Windows 10/11 dentro de contêineres Linux.
* **Webdriver Flag Bypass:** Desativação da flag `navigator.webdriver` e remoção da *AutomationExtension* do motor do Chrome para evasão de sistemas anti-bot (como Cloudflare/Akamai).
* **Headless Tuning:** Configuração de resolução forçada (1920x1080) no modo invisível para garantir a renderização correta do layout Desktop em pipelines.
* **Smart Waits:** Utilização de esperas explícitas (`WebDriverWait`) combinadas com extração nativa via `JavascriptExecutor` para interagir com elementos obstruídos por animações pesadas.

## 🚀 Guia de Execução Local

### 1. Pré-requisitos
* **Java (JDK 24 ou superior)** e **Maven** configurados nas variáveis de ambiente do seu sistema.
* **Google Chrome** atualizado.

### 2. Instalação e Execução
Abra o seu terminal de preferência e execute os comandos em sequência:

```bash
# Clonar o repositório
git clone [https://github.com/salomaonogueira/automacao-web-petz.git](https://github.com/salomaonogueira/automacao-web-petz.git)

# Acessar o diretório do projeto
cd automacao-web-petz

# Baixar as dependências e executar a suíte de testes E2E
mvn clean test
```

### 3. Análise de Resultados
Ao final da execução, um relatório HTML detalhado será gerado automaticamente. Para visualizá-lo de forma gráfica, abra o seguinte arquivo no seu navegador:
👉 `target/relatorio-teste.html`

## 🤖 Integração Contínua (GitHub Actions)
O repositório conta com uma pipeline de CI/CD nativa e funcional.
* **Trigger:** Acionada automaticamente a cada `push` para a branch `main`.
* **Ambiente:** Servidor Ubuntu executando os testes nativamente em background (Modo Headless).
* **Evidências em Nuvem:** O relatório gerado pela esteira CI fica disponível para download na aba **Actions** > selecionando a última execução > seção **Artifacts**.

---
*Desenvolvido por Salomão Nogueira - Analista de Testes / QA Automation*
```