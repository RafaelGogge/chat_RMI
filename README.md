

# 💬 Chat RMI — O Chat Mais Quinta Série da Internet

<div style="text-align: center;">
  <img src="[https://i.pinimg.com/originals/89/50/5d/89505d16d932d8cca00011c78a54a69d.gif](https://private-user-images.githubusercontent.com/74038190/271839856-3b4607a1-1cc6-41f1-926f-892ae880e7a5.gif?jwt=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJnaXRodWIuY29tIiwiYXVkIjoicmF3LmdpdGh1YnVzZXJjb250ZW50LmNvbSIsImtleSI6ImtleTUiLCJleHAiOjE3NDQ2NDE2NzksIm5iZiI6MTc0NDY0MTM3OSwicGF0aCI6Ii83NDAzODE5MC8yNzE4Mzk4NTYtM2I0NjA3YTEtMWNjNi00MWYxLTkyNmYtODkyYWU4ODBlN2E1LmdpZj9YLUFtei1BbGdvcml0aG09QVdTNC1ITUFDLVNIQTI1NiZYLUFtei1DcmVkZW50aWFsPUFLSUFWQ09EWUxTQTUzUFFLNFpBJTJGMjAyNTA0MTQlMkZ1cy1lYXN0LTElMkZzMyUyRmF3czRfcmVxdWVzdCZYLUFtei1EYXRlPTIwMjUwNDE0VDE0MzYxOVomWC1BbXotRXhwaXJlcz0zMDAmWC1BbXotU2lnbmF0dXJlPTQ2OTIxOTkzZWE1MDIxNTAxYjE3OWZkM2RhZDJjYWQwYmM0MmFmODhlODQxZmU1OGNiZGE3ZjQ4NmQ3NzNjNzcmWC1BbXotU2lnbmVkSGVhZGVycz1ob3N0In0.gBx4RssjVUzngXR7710Sq-0WoHmWJ9ZQGxO5qE0kiFA)" width="150" />
</div>

Seja muito bem-vindo ao **Chat RMI**, o sistema de chat multiusuário mais zoeiro (e funcional!) que você respeita. Desenvolvido com **Java RMI (Remote Method Invocation)**, este projeto permite que diversos usuários se conectem a um servidor central e conversem em tempo real — com direito a mensagens privadas, mensagens anônimas e muita bagunça organizada.

---

## 🚀 Funcionalidades

- **👤 Registro de Usuários:**  
  Cada participante escolhe um nome único. Se já estiver em uso, tente outro — a zoeira não tem limites!

- **🌍 Mensagens Públicas (Broadcast):**  
  Mande aquela mensagem marota para todo mundo ver e responder.

- **📩 Mensagens Privadas:**  
  Com o comando `/private <usuario> <mensagem>`, você envia uma mensagem exclusiva para alguém — e a pessoa vai saber que foi você!

- **🕵️‍♂️ Mensagens Anônimas:**  
  Com `/anonimo <usuario> <mensagem>`, ninguém descobre quem mandou (a menos que você se entregue no texto).

- **🧑‍🤝‍🧑 Listar Usuários Online:**  
  Use `/users` e descubra quem ainda não fugiu do chat.

- **👋 Saída com Estilo:**  
  Ao sair, o servidor avisa todo mundo com uma mensagem cômica (e o usuário nem verá).

- **🧼 Limpeza de Terminal:**  
  Ao entrar no chat, o terminal é limpo automaticamente para deixar tudo bonito e pronto pra zoeira.

---

## 🛠️ Tecnologias Utilizadas

- **Java RMI:** Comunicação distribuída no melhor estilo remoto.
- **Java SE (JDK):** Para compilar e executar.
- **CMD do Windows:** Local de testes (a limpeza de terminal foi feita para funcionar com o `cls` do CMD).

---

## 💻 Como Executar

### ⚙️ Pré-requisitos

- Java JDK instalado.
- Ambiente CMD (no Windows).
- Navegador de terminal ou editor com suporte a execução Java (opcional).

### 🧱 Compilando

Dentro da pasta `src/`, execute:

```bash
javac *.java
```

---

### ▶️ Iniciando o Servidor

No terminal:

```bash
java ChatServer
```

---

### 🧑‍💻 Iniciando um Cliente

Em outro terminal:

```bash
java ChatClient
```

Você pode abrir quantas janelas quiser para simular múltiplos usuários conectados.

---

## 🎮 Exemplo de Uso

Após conectar:

```
=========================================
Bem-vindo ao sistema de chat mais legal!
Comandos que você pode usar:
   /sair                         - Sair do chat
   /private <usuario> <mensagem> - Enviar mensagem privada (você será identificado)
   /anonimo <usuario> <mensagem> - Enviar mensagem anônima (ninguém saberá que foi você)
   /users                        - Ver quem está online
Digite sua mensagem e pressione Enter:
=========================================
> 
```

---

## 🤝 Contribuições

Contribuições são sempre bem-vindas!  
Se quiser sugerir melhorias, encontrar bugs ou adicionar funcionalidades novas, fique à vontade para abrir uma **issue** ou fazer um **pull request**.

---

## 📄 Licença

Este projeto está licenciado sob a [MIT License](LICENSE).  
Use, modifique, melhore e compartilhe — mas sem tirar o crédito da zoeira.

---

Divirta-se, aprenda, ria e... **digite com responsabilidade!**  
🧠💻🗨️  
_O time do Chat RMI_

- RA 6924106689 - Breno Nunes dos Santos
- RA 6924106574 - Emanuel Gonçalves Ferreira
- RA 6924106412 - Gabriel Lima Groner
- RA 6922203723 - Rafael Vieira Gogge
- RA 6924106672 - Renan da Cruz Santos
