# Como executar o projeto
## Pré-requisitos 
* Instalar o [Docker](https://docs.docker.com/engine/install/) para Windows ou Mac.
* Instalar também o [Docker Compose](https://docs.docker.com/compose/install) caso seja o sistema operacional Linux.
## Como rodar utilizando o Docker
### Executando o ambiente de desenvolvimento com o Docker Compose
> Caso o sistema operacional seja Linux é preciso dar permissão na pasta .mvn, execute o comando abaixo:
```bash
$ chmod +x mvnw
```
```bash
$ docker-compose up --build
```
### Executando o ambiente de produção com o Docker Compose
```bash
$ docker-compose -f docker-compose.prod.yml up --build
```
## Acessando a documentação da API - Swagger
> Após os containers estarem prontos utilizando um dos comandos anteriores a documentação Swagger estará disponível no endereço: http://localhost:8080/swagger-ui.html
## Documentação da API usando o Postman
> O arquivo de collection foi exportado para o caminho: [api-harry-potter.postman_collection.json](./docs/api-harry-potter.postman_collection.json)
# Arquitetura - C4 Model
## Diagrama de contexto
![contexto](./docs/diagram-architecture-c4-context.png)
## Diagrama de container
![container](./docs/diagram-architecture-c4-container.png)
> Os diagramas foram criados usando a ferramenta: https://app.diagrams.net/?src=about
## Foram desenvolvidos os testes de integrações e unitários das funcionalidades desenvolvidas.
> Para executar os testes é necessario instalar um IDE que suporte a linguagem JAVA, é recomendado a utilização do Spring Tool Suite.

