import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface {

    private String username;
    private ChatServerInterface server;

    protected ChatClient(String username, ChatServerInterface server) throws RemoteException {
        super();
        this.username = username;
        this.server = server;
    }

    @Override
    public void receiveMessage(String sender, String message) throws RemoteException {
        System.out.println("[" + sender + "]: " + message);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Instruções exibidas uma vez no início
        System.out.println("==================================================================================");
        System.out.println("Você está prestes a participar de uma conversa que pode (ou não) fazer sentido!");
        System.out.println("Comandos que você pode usar:");
        System.out.println("   /sair                         - Sair do chat");
        System.out.println("   /private <usuario> <mensagem> - Enviar mensagem privada (você será identificado, esteja ciente disso)");
        System.out.println("   /anonimo <usuario> <mensagem> - Enviar mensagem anônima (ninguém saberá que foi você, use com moderação)");
        System.out.println("   /users                        - Ver quem está online");
        System.out.println("Digite sua mensagem e pressione Enter:");
        System.out.println("==================================================================================");

        try {
            while (true) {
                System.out.print("> ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("/sair")) {
                    try {
                        server.disconnectUser(username);
                    } catch (RemoteException re) {
                        System.out.println("Algo deu errado ao tentar sair... mas já estamos cuidando disso!");
                    }
                    System.out.println("Você saiu do chat. Sentiremos sua falta (ou não kkkk). Até logo!");
                    System.exit(0);
                } else if (input.startsWith("/private ")) {
                    String[] parts = input.split(" ", 3);
                    if (parts.length >= 3) {
                        String receiver = parts[1];
                        String message = parts[2];
                        try {
                            server.sendPrivateMessage(username, receiver, message);
                        } catch (RemoteException re) {
                            System.out.println("Não conseguimos enviar sua mensagem privada. Tente novamente!");
                        }
                    } else {
                        System.out.println("Formato inválido. Tente assim: /private Fulano Olá, tudo bem?");
                    }
                } else if (input.startsWith("/anonimo ")) {
                    String[] parts = input.split(" ", 3);
                    if (parts.length >= 3) {
                        String receiver = parts[1];
                        String message = parts[2];
                        try {
                            server.sendAnonymousMessage(receiver, message);
                        } catch (RemoteException re) {
                            System.out.println("A mensagem anônima se perdeu no espaço... tente outra vez.");
                        }
                    } else {
                        System.out.println("Formato inválido. Use: /anonimo Cicrano Você nunca saberá quem sou eu.");
                    }
                } else if (input.equalsIgnoreCase("/users")) {
                    try {
                        System.out.println("Usuários online agora:");
                        for (String user : server.getOnlineUsers()) {
                            System.out.println("- " + user);
                        }
                    } catch (RemoteException re) {
                        System.out.println("Problema ao listar usuários online. Tente novamente.");
                    }
                } else {
                    try {
                        server.broadcastMessage(username, input);
                    } catch (RemoteException re) {
                        System.out.println("Mensagem não enviada. Talvez o servidor esteja de mau humor.");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Algo muito estranho aconteceu. Erro: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChatClient client = null;
        String username = "";

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            ChatServerInterface server = (ChatServerInterface) registry.lookup("ChatServer");

            boolean registered = false;
            while (!registered) {
                System.out.print("Escolha um nome de usuário: ");
                username = scanner.nextLine();
                try {
                    client = new ChatClient(username, server);
                    if (server.registerUser(username, client)) {
                        registered = true;

                        // Limpa o terminal (compatível com CMD do Windows)
                        try {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } catch (Exception ex) {
                            System.out.println("Não foi possível limpar o terminal. Mas tudo bem, seguimos o jogo.");
                        }

                        // Mensagem divertida ao entrar
                        System.out.println("Parabéns, " + username
                                + "! Agora você faz parte do chat mais quinta série da internet!");
                    } else {
                        System.out.println("Esse nome já está em uso. Tente algo mais criativo!");
                    }
                } catch (RemoteException re) {
                    System.out.println("Opa! Tivemos um problema ao registrar. Vamos tentar de novo.");
                }
            }

            client.start();
        } catch (Exception e) {
            System.out.println("Não foi possível conectar ao servidor. Talvez ele esteja tomando café...");
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}
