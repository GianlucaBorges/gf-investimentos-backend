# GF Investimentos – Backend

Este é o projeto **backend** da aplicação **GF Investimentos**, desenvolvido com **Spring Boot (Java 17)**. Ele fornece uma API RESTful para gerenciamento de investimentos, com autenticação via **JWT** e persistência em banco **PostgreSQL**.

## ✅ Funcionalidades

- **Autenticação com JWT**
- **Cadastro de usuários**
- **CRUD de investimentos**
- **Validações de dados**
- **Integração com frontend Angular**
- **Configuração via variáveis de ambiente**
- **Ambiente Docker com PostgreSQL**

## 🔐 Autenticação

A aplicação utiliza **JWT (JSON Web Token)** para autenticação e autorização.

- Ao fazer login, o backend gera um token JWT.
- Esse token deve ser enviado no header `Authorization: Bearer <token>` para acessar rotas protegidas.

## 🔧 Variáveis de Ambiente

As variáveis podem ser definidas em um arquivo `.env` (se estiver usando Docker) ou via argumentos de inicialização. Exemplos:

```bash
DB_HOST=postgres
DB_PORT=5432
DB_NAME=gf_investimentos
DB_USER=postgres
DB_PASSWORD=senha
JWT_SECRET=chave_secreta
```

Para saber quais variáveis estão disponíveis, consulte o arquivo `application.properties`, `application.yml` ou `.env.example`.

## 🚀 Como rodar o projeto

### Pré-requisitos

- Java 17+
- Maven
- PostgreSQL (caso não use Docker)

### Rodando localmente

1. Clone o repositório:

```bash
git clone
cd gf-investimentos-backend
```

2. Configure o application.properties ou application.yml com os dados do seu banco.

3. Execute o projeto:

```bash
mvn spring-boot:run
```

4. Acesse a API em `http://localhost:8080`.

### Rodando com Docker

1. Certifique-se de ter o Docker instalado.

2. Navegue até o diretório do projeto e execute:

```bash
docker-compose up --build -d
```

3. Acesse a API em `http://localhost:8080`.

4. Para parar

```bash
docker-compose down
```

5. Para acessar o banco de dados, use um cliente PostgreSQL com as credenciais definidas no `docker-compose.yml`.

## 📚 Documentação da API

A documentação da API está disponível em `http://localhost:8080/swagger-ui/`.

## 🛠️ Tecnologias Utilizadas

Java 17

Spring Boot

Spring Security + JWT

Spring Data JPA

PostgreSQL

Maven

Docker

## 📝 Contribuição

📃 Licença
Projeto desenvolvido internamente para fins educacionais e profissionais. Uso restrito à equipe da empresa GF, salvo autorização prévia.

Desenvolvido com 💼 por [Gianluca Mendes](https://github.com/GianlucaBorges)
