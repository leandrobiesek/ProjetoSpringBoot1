# Projeto Spring Boot

## Introdução
Esta API de teste tem por objetivo possibilitar a leitura da lista de indicados e vencedores
da categoria **Pior Filme** do **Golden Raspberry Awards**.

## Requisitos de Software
* Java 11
* Apache Maven

## Execução do Projeto
Para executar o projeto execute através de um terminal na pasta do projeto o seguinte comando:

`mvn spring-boot:run`

Durante a inicialização, uma lista de dados é carregada para um banco de dados em memória.
Esta lista de dados está armazenada em um arquivo CSV localizado na pasta

`./src/main/resoures/static/movielist.csv`

Caso deseje alterar o nome ou o local relativo dentro da pasta "resources" será necessário alterar
o arquivo de configuração `application.properties`

```properties
# Nome do arquivo CSV contido na pasta "resources"
csvfilename=static/movielist.csv
```

## Testes

Para executar os testes da aplicação basta executar o segunte comando na pasta do projeto através
de um terminal:

`mvn test`

Caso o arquivo de dados de entrada tenha sido modificado será necessário alterar os dados esperados
durante o teste de integração, localizados no arquivo `PiorfilmeApplicationTests`:

```java
@BeforeAll
void setup() {
    this.apiReturnDTO = new PrizeIntervalReturnDTO();

    //Adiciona os valores esperados para o(s) produtor(es) vencedor(res) com menor intervalo
    this.apiReturnDTO.getMin().add(new PrizeIntervalDTO("Joel Silver", 1L, 1990L, 1991L));

    //Adiciona os valores esperados para o(s) produtor(es) vencedor(res) com maior intervalo
    this.apiReturnDTO.getMax().add(new PrizeIntervalDTO("Matthew Vaughn", 13L, 2002L, 2015L));
}
```

## Geração e Execução do Arquivo .JAR
Para gerar o arquivo .JAR execute o seguinte comando:

`mvn clean install`

Após isso o arquivo .JAR estará criado e poderá ser executado a partir da raiz da pasta do projeto
através do comando

`java -jar target/piorfilme-0.0.1-SNAPSHOT.jar`

# API REST

## Intervalo de prêmios

### Request
`GET /`

### Response
```
{
    "min: [
        {
            "producer":"Joel Silver",
            "interval":1,
            "previousWin":1990,
            "followingWin":1991
        }
    ],
    "max": [
        {
            "producer":"Matthew Vaughn",
            "interval":13,
            "previousWin":2002,
            "followingWin":2015
        }
    ]
}
```
