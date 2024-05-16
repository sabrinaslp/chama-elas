<p align="center">
    <img src="https://github.com/sabrinaslp/chama-elas/assets/101300856/509ff8a4-c073-4751-9967-b008b3897737" alt="ChamaElas - Projeto Integrador #ElasTech 2ª Edição - Pagbank">
</p>
<p align="center">
   <img src="https://img.shields.io/badge/Status:-Concluído-green"/>
   <img src="https://img.shields.io/badge/Projeto Integrador:-ElasTech 2024-FE951E"/>
</p>
<h1 align="center">ChamaElas - Sistema de Chamados Técnicos 👩‍💻</h1>

## ✨ Índice

- [Sobre](#-sobre)
- [Pré-requisitos](#%EF%B8%8F-pr%C3%A9-requisitos)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Como utilizar](#-como-utilizar)
- [Documentação](#-documentação)
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

## 💻 Tecnologias utilizadas

### Backend:

<p>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1" title="Java"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/spring-logo.png" alt="spring-logo" title="Spring Boot"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/mysql-logo.png" alt="mysql-logo" title="MySQL Workbench"/>
    </span>
    <span>
        <img height="48" src="https://icon.icepanel.io/Technology/svg/JUnit.svg" alt="junit-logo" title="JUnit"/>
    </span>
     <span>
        <img height="48" src="https://i.imgur.com/FJUBpHg.png" alt="mockito-logo" title="Mockito"/>
    </span>
    <span>
        <img height="48" src="https://i.imgur.com/KpJqwhA.png" alt="postman-logo" title="Postman"/>
    </span>
</p>

  
### Front-end:
<p>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/html-5--v1.png" alt="html-5--v1" title="HTML5"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/css3.png" alt="css3" title="CSS3"/>
    </span>
    <span>
        <img width="48" height="48" src="https://www.thymeleaf.org/doc/images/thymeleaf.png" alt="thymeleaf-logo" title="Thymeleaf"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/bootstrap--v2.png" alt="bootstrap--v2" title="Bootstrap"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/javascript--v1.png" alt="javascript--v1" title="JavaScript"/>
    </span>
</p>

### Outras ferramentas:
<p>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/color/48/git.png" alt="git" title="Git"/>
    </span>
    <span>
        <img width="48" height="48" src="https://img.icons8.com/glyph-neue/64/github.png" alt="github" title="GitHub"/>
    </span>
      <span>
        <img width="48" height="48" src="https://seeklogo.com/images/I/intellij-idea-logo-F0395EF783-seeklogo.com.png" alt="git" title="Intellij"/>
    </span>
    <span>
        <img width="48" height="48" src="https://static-00.iconduck.com/assets.00/swagger-icon-1024x1024-09037v1r.png" alt="github" title="Swagger"/>
    </span>
</p>

## 🚀 Como utilizar

Para instalar e executar o sistema localmente, siga estas etapas:

**1.** Clone o repositório do GitHub:

   ```bash
   git clone https://github.com/sabrinaslp/chama-elas.git
   ```

**2.** Configure sua senha para o banco de dados **MySQL** de acordo com as configurações do arquivo `application.properties`.

**3.** Compile e execute a aplicação utilizando Maven:

   ```bash
   mvn spring-boot:run
   ```

**4.** Acesse o sistema através do link `http://localhost:8090`.

## 📄 Documentação

Para acessar a documentação detalhada dos endpoints da nossa API:

**1-** Realize o login como `Administrador` na aplicação em funcionamento.

**2-** Acessar a rota `localhost:8090/swagger-ui.html`.

Lá, você encontrará informações abrangentes sobre as **rotas disponíveis**, incluindo detalhes sobre os **parâmetros**, **tipos de dados** esperados e exemplos de **solicitações** e **respostas**. 

## ⚙️ Funcionalidades

O **ChamaElas** suporta vários perfis de usuários, cada um com diferentes níveis de acesso e responsabilidades:

- **Cliente**:
    - [X] Podem registrar novos chamados
    - [X] Visualização dos chamados criados pelo cliente, incluindo suas principais informações
    - [X] Possibilidade de excluir chamados que ainda não foram atendidos por um técnico

- **Técnico**:
    - [X] Visualização dos chamados em aberto.
    - [X] Definição da prioridade do chamado ao assumi-lo.
    - [X] Acesso a todos os detalhes dos chamados atribuídos ao técnico.
    - [X] Capacidade de alterar os status dos chamados. Em caso de finalização, é necessário fornecer uma descrição sobre como o problema foi resolvido.

- **Administrador**:
    - [X] Tem acesso total ao sistema.
    - [X] Pode visualizar todos os detalhes de cada chamado.
    - [X] Capaz de filtrar chamados por status.
    - [X] Visualiza a quantidade de chamados em andamento, finalizados e abertos.
    - [X] Autorizado a excluir chamados não atribuídos a um técnico ou já finalizados.
    - [X] Responsável pelo gerenciamento de usuários, podendo ativá-los ou inativá-los conforme necessário.

💡 **Cada perfil** de usuário tem acesso apenas às funcionalidades relevantes às suas responsabilidades, garantindo uma experiência **personalizada** e **segura** para todos os usuários.
<br>
<br>

## 📝 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
<br>
<br>

## 👩‍💻 Equipe de Desenvolvimento:

Para mais informações, sugestões ou dúvidas, entre em contato conosco:

<p align="center">
    <table align="center">
        <tr>
            <td align="center" width="325">
                <h3>Anna Maria</h3>
                 <a href="https://github.com/amrodrigues"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/anna-maria-rodrigues-2b375016/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: amrodrigues1307@gmail.com</p>
            </td>
            <td align="center" width="325">
                <h3>Andressa Rodrigues</h3>
                <a href="https://github.com/andressarodrigues2172dev"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/andressa-macedo-rodrigues/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: andressa.rodrigues.2172@gmail.com</p>
            </td>
            <td align="center" width="325">
                <h3>Cecília Galvão</h3>
                <a href="https://github.com/ceciliagalvaoo"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/cec%C3%ADlia-galv%C3%A3o/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: ceciliabtriz@gmail.com</p>
            </td>
        </tr>
    </table>
</p>
<p align="center">
    <table align="center">
        <tr>
            <td align="center" width="325">
                <h3>Larissa Lisboa</h3>
                <a href="https://github.com/LarissaLisboa"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/larissa-lisboa-souza/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: larissa-lisboa99@hotmail.com</p>
            </td>
            <td align="center" width="325">
                <h3>Rayane Souza</h3>
                <a href="https://github.com/szrayane"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/rayane-souza-a02658229/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: rayhsdsouza@gmail.com</p>
            </td>
            <td align="center" width="325">
                <h3>Sabrina Satriany</h3>
                <a href="https://github.com/sabrinaslp"><img src="https://imgur.com/mRvA6Kh.png" alt="GitHub Profile"></a><a href="https://www.linkedin.com/in/sabrina-satriany/"><img src="https://imgur.com/dmA9Br7.png" alt="LinkedIn Profile" width="30px"></a>
                <p>Email: sabrinaslimap@gmail.com</p>
            </td>
        </tr>
    </table>
</p>


---
