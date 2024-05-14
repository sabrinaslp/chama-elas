<p align="center">
    <img src="https://github.com/sabrinaslp/chama-elas/assets/101300856/509ff8a4-c073-4751-9967-b008b3897737" alt="ChamaElas - Projeto Integrador #ElasTech 2ª Edição - Pagbank">
</p>
<p align="center">
   <img src="https://img.shields.io/badge/Status:-Em_andamento-yellow"/>
   <img src="https://img.shields.io/badge/Projeto Integrador:-ElasTech 2024-FE951E"/>
</p>
<h1 align="center">ChamaElas - Sistema de Chamados Técnicos em Informática 👩‍💻</h1>


## ✨ Índice

- [Sobre](#-sobre)
- [Pré-requisitos](#%EF%B8%8F-pr%C3%A9-requisitos)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como utilizar](#-como-utilizar)
- [Testando a aplicação via Postman](#-testando-a-aplica%C3%A7%C3%A3o-via-postman)
- [Funcionalidades](#%EF%B8%8F-funcionalidades)
- [Licença](#-licen%C3%A7a)
- [Equipe de Desenvolvimento](#-equipe-de-desenvolvimento)


## 💡 Sobre

Este projeto consiste no desenvolvimento de uma **interface** e **rotas** para um sistema completo de chamados técnicos em informática. 
- Em um chamado técnico, um **usuário** solicita atendimento e um **técnico** assume essa demanda, iniciando o atendimento e modificando seu status conforme necessário.
- Cada **chamado** inclui informações como usuário, status, setor, descrição, prioridade e data de início.

## 🛠️ Pré-requisitos

Antes de começar, certifique-se de que você tenha as seguintes ferramentas instaladas em sua máquina:
- MySQL Workbench
- Java
- IDE (recomendamos a utilização do **Intellij**)

## 💻 Tecnologias Utilizadas

**Backend:**
- Java 
- Spring Boot
- MySQL Workbench
- Junit
- Mockito
- Postman
  
**Frontend:**
- HTML
- CSS
- Thymeleaf
- Bootstrap
- Javascript

**Versionamento:**
- Git / Github

## 🚀 Como utilizar

Para instalar e executar o sistema localmente, siga estas etapas:

1. Clone o repositório do GitHub:

   ```bash
   git clone https://github.com/sabrinaslp/chama-elas.git
   ```

2. Configure sua senha para o banco de dados **MySQL** de acordo com as configurações do arquivo `application.properties`.

3. Compile e execute a aplicação utilizando Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Acesse o sistema através do link `http://localhost:8090`.

## 🔎 Rotas da aplicação


## ⚙️ Funcionalidades

O **ChamaElas** suporta vários perfis de usuários, cada um com diferentes níveis de acesso e responsabilidades:

- **Usuários Comuns**: Podem registrar novos chamados e visualizar apenas os chamados atribuídos a eles.

- **Técnicos**: Além de poder visualizar e comentar em chamados atribuídos a eles, os técnicos também podem definir a prioridade dos chamados, atualizar o status dos chamados e resolver os problemas relatados.

- **Administradores**: Possuem acesso total ao sistema, podendo gerenciar usuários, configurar as preferências do sistema, entre outras funcionalidades avançadas.

**Cada perfil** de usuário tem acesso apenas às funcionalidades relevantes às suas responsabilidades, garantindo uma experiência **personalizada** e **segura** para todos os usuários.

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## 👩‍💻 Equipe de Desenvolvimento:

Para mais informações, sugestões ou dúvidas, entre em contato conosco:

<p align="center">
    <table align="center" cellspacing="20">
        <tr>
            <td align="center" width="325">
                <a href="https://github.com/amrodrigues"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/anna-maria-rodrigues-2b375016/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Anna Maria</h3>
                <p>Email: amrodrigues1307@gmail.com</p>
            </td>
            <td align="center" width="325">
                <a href="https://github.com/andressarodrigues2172dev"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/andressa-macedo-rodrigues/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Andressa Rodrigues</h3>
                <p>Email: andressa.rodrigues.2172@gmail.com</p>
            </td>
            <td align="center" width="325">
                <a href="URL_DO_PERFIL_DO_GITHUB_1"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="URL_DO_PERFIL_DO_LINKEDIN_1"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Cecília Galvão</h3>
                <p>Email: emailcecilia@gmail.com</p>
            </td>
        </tr>
    </table>
</p>
<p align="center">
    <table align="center" cellspacing="20">
        <tr>
            <td align="center" width="325">
                <a href="https://github.com/LarissaLisboa"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/larissa-lisboa-souza/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Larissa Lisboa</h3>
                <p>Email: larissa-lisboa99@hotmail.com</p>
            </td>
            <td align="center" width="325">
                <a href="https://github.com/szrayane"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/rayane-souza-a02658229/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Rayane Souza</h3>
                <p>Email: rayhsdsouza@gmail.com</p>
            </td>
            <td align="center" width="325">
                <a href="https://github.com/sabrinaslp"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/sabrina-satriany/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <h3>Sabrina Satriany</h3>
                <p>sabrinaslimap@gmail.com</p>
            </td>
        </tr>
    </table>
</p>








---
