# Como executar o projeto
## Pré-requisitos 
* Instalar o [Docker](https://docs.docker.com/engine/install/) para Windows ou Mac.
* Instalar também o [Docker Compose](https://docs.docker.com/compose/install) caso seja o sistema operacional Linux.
## Como rodar utilizando o Docker
### Executando o ambiente de desenvolvimento com o Docker Compose
```bash
$ docker-compose up --build
```
### Executando o ambiente de produção com o Docker Compose
```bash
$ docker-compose -f docker-compose.prod.yml up --build
```
> Após os containers estarem prontos a aplicação estará disponível no endereço: http://localhost:8080 
# Arquitetura - C4 Model
## Diagrama de contexto
![contexto](./docs/diagram-architecture-c4-context.png)
## Diagrama de container
![container](./docs/diagram-architecture-c4-container.png)
> Os driagramas foram criados usando a ferramenta: https://app.diagrams.net/?src=about
