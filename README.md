# Chat RMI - O Chat Mais Quinta Série da Internet

Bem-vindo ao **Chat RMI** – o sistema de chat multiusuário mais zoeiro que você já viu! Desenvolvido em Java com RMI (Remote Method Invocation), este projeto permite que vários usuários se conectem a um servidor central, conversem em tempo real, enviem mensagens privadas (identificadas ou anônimas) e muito mais.

## Funcionalidades

- **Registro de Usuários:**  
  O usuário escolhe um nome exclusivo para participar do chat. Se o nome já estiver em uso, você pode tentar outro – use a criatividade!

- **Mensagens Públicas (Broadcast):**  
  Envie mensagens para todo o grupo e veja todos os participantes interagirem em tempo real.

- **Mensagens Privadas:**  
  Utilize o comando `/private <destinatario> <mensagem>` para enviar mensagens privadas identificadas. O receptor saberá exatamente quem enviou a mensagem!

- **Mensagens Anônimas:**  
  Com o comando `/anonimo <destinatario> <mensagem>`, envie uma mensagem sem revelar sua identidade. Uma forma misteriosa de conversar sem ser visto.

- **Listagem de Usuários Online:**  
  Use o comando `/users` para ver quem está online no chat.

- **Desconexão com Estilo:**  
  Ao sair, o sistema exibe uma mensagem divertida para os demais usuários, garantindo que até sua saída seja inesquecível!

- **Limpeza do Terminal:**  
  Após o registro, o terminal é limpo para que sua entrada no chat fique limpa e sem bagunça dos comandos anteriores.

## Tecnologias

- **Java RMI:** A magia que possibilita a comunicação distribuída entre o servidor e os clientes.
- **Java SE:** Toda a base para desenvolver o projeto e compilar os arquivos `.java`.
- **CMD do Windows:** Ambiente de execução onde o projeto foi testado (a funcionalidade de limpeza do terminal é feita via `cls`).

## Como Executar

### Pré-Requisitos

- Java Development Kit (JDK) instalado (sugiro uma versão recente, ou compile com o `--release` caso esteja usando uma JRE mais antiga).
- Acesso ao CMD (para a limpeza do terminal funcionar corretamente).
- Familiaridade com a linha de comando.

### Compilação

1. Navegue até a pasta `src` onde os arquivos `.java` estão.
2. Compile todos os arquivos:
   ```bash
   javac *.java

##Iniciando o Servidor:
   ```bash
   java ChatServer

- **Iniciando o Cliente** 
1. java ChatClient

- **Exemplo de Uso:** 

- Comandos que você pode usar:
-    /sair                         - Sair do chat
-    /private <usuario> <mensagem> - Enviar mensagem privada (você será identificado)
-   /anonimo <usuario> <mensagem> - Enviar mensagem anônima (ninguém saberá que foi você)
-    /users                        - Ver quem está online
- Digite sua mensagem e pressione Enter:
 
