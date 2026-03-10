<h1 align="center">📚 Liter Alura 📚</h1>
<h2 align="center">G9ONE - Desafios</h2>


<p align="center">
  <img width="410" height="410" alt="Image" src="https://github.com/user-attachments/assets/65542313-ae6a-46a0-ad44-354395b9fe30" />
</p>

<h1 align="center">📚 Literalura</h1>

<p align="center">
  <img src="URL_DA_SUA_IMAGEM_AQUI" alt="Capa do Projeto Literalura" width="700">
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-orange" alt="Java 21">
  <img src="https://img.shields.io/badge/Spring%20Boot-4.x-brightgreen" alt="Spring Boot">
  <img src="https://img.shields.io/badge/PostgreSQL-18-blue" alt="PostgreSQL">
  <img src="https://img.shields.io/badge/Maven-Build-red" alt="Maven Build">
  <img src="https://img.shields.io/badge/Status-Concluído-success" alt="Status Concluído">
</p>

<p align="center">
  Aplicação de console desenvolvida em <strong>Java com Spring Boot</strong> que consome a API pública Gutendex, armazena livros em um banco PostgreSQL e permite realizar consultas sobre livros e autores cadastrados.
</p>

---

## 📑 Índice

* [🚀 Funcionalidades](#-funcionalidades)
* [🖥️ Demonstração](#️-demonstração)
* [🛠️ Tecnologias e Ferramentas](#️-tecnologias-e-ferramentas)
* [🗄️ Banco de Dados](#️-banco-de-dados)
* [⚙️ Configuração e Execução](#️-configuração-e-execução)
* [🔖 Versionamento e Tags](#-versionamento-e-tags)
* [👨‍💻 Autor](#-autor)

---

## 🚀 Funcionalidades

A aplicação roda totalmente no **terminal** e apresenta um menu interativo com as seguintes opções:

* **🔎 Buscar livro pelo título:** Consulta a API Gutendex, registra o livro no banco (evitando duplicações) e exibe detalhes como título, autores, idioma e downloads.
* **📚 Listar livros registrados:** Lista todos os livros armazenados localmente.
* **👨‍💻 Listar autores registrados:** Exibe todos os autores cadastrados e os livros associados a eles.
* **🧑‍🏫 Listar autores vivos em um determinado ano:** Permite filtrar e descobrir quais autores estavam vivos em um ano específico.
* **🌎 Listar livros por idioma:** Filtra livros por idiomas disponíveis (Inglês, Espanhol, Francês e Português).

---

## 🖥️ Demonstração

Ao iniciar a aplicação, o seguinte menu é exibido no terminal:

```text
====== LITERALURA ======

1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
0 - Sair

Escolha uma opção: 1
Digite o título do livro: quincas borba
```

---

## 🛠️ Tecnologias e Ferramentas

O projeto foi desenvolvido aplicando conceitos de consumo de API REST, manipulação de JSON, arquitetura em camadas e programação orientada a objetos com as seguintes tecnologias:

* **Linguagem:** Java 21
* **Framework:** Spring Boot
* **Persistência e ORM:** Spring Data JPA e Hibernate
* **Banco de Dados:** PostgreSQL
* **Gerenciamento de Dependências:** Maven
* **API Externa:** [Gutendex](https://gutendex.com/) (API pública do Projeto Gutenberg)

---

## 🗄️ Banco de Dados

O sistema utiliza um relacionamento `ManyToMany` (Muitos para Muitos) entre as entidades principais:

* **Livro:** id, titulo, idioma, numeroDownloads
* **Autor:** id, nome, anoNascimento, anoFalecimento

Um livro pode ter vários autores e um autor pode ter escrito vários livros.

---

## ⚙️ Configuração e Execução

### 1. Clonar o repositório
```bash
git clone [https://github.com/JefersonBLuz/NOME_DO_SEU_REPOSITORIO.git](https://github.com/JefersonBLuz/NOME_DO_SEU_REPOSITORIO.git)
```

### 2. Criar o Banco de Dados
Crie um banco de dados no PostgreSQL chamado `literalura`.

### 3. Configurar as Credenciais do Banco
Você pode configurar a conexão com o PostgreSQL de duas maneiras diferentes. Escolha a que melhor se adapta ao seu ambiente:

**Opção A: Diretamente no arquivo (Mais rápido para testes locais)**
Abra o arquivo `src/main/resources/application.properties` e substitua as variáveis (os valores com `$`) diretamente pelos seus dados locais:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres
```

**Opção B: Variáveis de Ambiente no Windows (Recomendado e mais seguro)**
Mantenha o arquivo `application.properties` intacto com as variáveis (`${DB_HOST}`, `${DB_USER}`, etc.) e configure-as no seu sistema:
1. Pressione a tecla `Windows` e digite **"Editar as variáveis de ambiente do sistema"**.
2. Na janela que abrir, clique no botão **"Variáveis de Ambiente..."** na parte inferior.
3. Na seção "Variáveis de usuário", clique em **"Novo..."** e adicione uma por uma:
   * Nome: `DB_HOST` | Valor: `localhost`
   * Nome: `DB_PORT` | Valor: `5432`
   * Nome: `DB_NAME` | Valor: `literalura`
   * Nome: `DB_USER` | Valor: `seu_usuario_postgres`
   * Nome: `DB_PASSWORD` | Valor: `sua_senha_postgres`
4. **Importante:** Feche e abra novamente a sua IDE ou terminal para que o sistema reconheça as novas variáveis.

### 4. Rodar a aplicação
Execute a classe `LiteraluraApplication.java` pela sua IDE ou utilize o Maven pelo terminal:
```bash
./mvnw spring-boot:run
```

---

## 🔖 Versionamento e Tags

Este projeto utiliza [Git](https://git-scm.com/) para controle de versão.

A versão atual da aplicação é a **`v1.0.0`**. 

Para fins de documentação, os seguintes comandos foram utilizados para criar e publicar esta versão no repositório:

```bash
# Criar a tag da versão final
git tag -a v1.0.0 -m "Lançamento da primeira versão do projeto Literalura"

# Enviar a tag para o repositório remoto (GitHub)
git push origin v1.0.0
```

---

## 👨‍💻 Autor

Desenvolvido por **Jeferson Braga Luz**.

[![GitHub](https://img.shields.io/badge/GitHub-JefersonBLuz-181717?style=flat-square&logo=github)](https://github.com/JefersonBLuz) 
[![Website](https://img.shields.io/badge/Website-jefersonbraga.cloud-0052CC?style=flat-square&logo=google-chrome&logoColor=white)](https://jefersonbraga.cloud)
[![WhatsApp](https://img.shields.io/badge/WhatsApp-Contato-25D366?style=flat-square&logo=whatsapp&logoColor=white)](https://wa.me/557196585476)