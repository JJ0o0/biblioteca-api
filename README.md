# Biblioteca API

API REST para gerenciamento de biblioteca, desenvolvida com Spring Boot e PostgreSQL.

## Tecnologias

- Java 21
- Spring Boot 3.4
- Spring Data JPA
- PostgreSQL
- Docker
- JUnit 5 + Mockito

## Funcionalidades

- Cadastro, listagem, atualização e remoção de livros
- Busca por autor
- Sistema de empréstimo e devolução
- Persistência de dados com PostgreSQL

## Pré-requisitos

- Java 21
- Docker

## Como rodar

Clone o repositório:
```bash
git clone git@github.com:seunome/biblioteca-api.git
cd biblioteca-api
```

Suba o banco de dados:
```bash
docker run --name postgres-dev \
  -e POSTGRES_PASSWORD=senha123 \
  -e POSTGRES_USER=usuario \
  -e POSTGRES_DB=bibliotecadb \
  -p 5432:5432 \
  -d postgres:16
```

Rode o projeto:
```bash
./mvnw spring-boot:run
```

## Endpoints

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/livros` | Lista todos os livros |
| GET | `/livros/{id}` | Busca livro por ID |
| GET | `/livros/autor/{autor}` | Lista livros por autor |
| POST | `/livros` | Cadastra novo livro |
| PUT | `/livros/{id}` | Atualiza livro |
| DELETE | `/livros/{id}` | Remove livro |
| PATCH | `/livros/{id}/emprestar` | Empresta livro |
| PATCH | `/livros/{id}/devolver` | Devolve livro |

## Exemplo de uso

Cadastrar livro:
```bash
curl -X POST http://localhost:8080/livros \
  -H "Content-Type: application/json" \
  -d '{"titulo": "O Hobbit", "autor": "Tolkien", "preco": 49.90, "anoPublicacao": 1937}'
```

Resposta:
```json
{
  "id": 1,
  "titulo": "O Hobbit",
  "autor": "Tolkien",
  "preco": 49.90,
  "anoPublicacao": 1937,
  "disponivel": true
}
```