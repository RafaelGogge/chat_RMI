import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ChatClient extends UnicastRemoteObject implements ChatClientInterface {

    private String username;
    private ChatServerInterface server;

    protected ChatClient(String username, ChatServerInterface server) throws RemoteException {
        this.username = username;
        this.server = server;
    }

    @Override
    public void receiveMessage(String sender, String message) throws RemoteException {
        System.out.println("[" + sender + "]: " + message);
    }

    // Metodo reutilizavel para exibir comandos e mensagens de boas-vindas.
    private void exibirTelaInicial() {
        System.out.println();
        System.out.println("==================================================================================");
        System.out.println("> Voce esta prestes a participar de uma conversa que pode (ou nao) fazer sentido!");
        System.out.println("> Comandos que voce pode usar:");
        System.out.println();
        System.out.println("  - /sair                          Sair do chat");
        System.out.println(
                "  - /private <usuario> <mensagem> Enviar mensagem privada (voce sera identificado, esteja ciente disso)");
        System.out.println(
                "  - /anonimo <usuario> <mensagem> Enviar mensagem anonima (ninguem sabera que foi voce, use com moderacao)");
        System.out.println("  - /users                         Ver quem esta online");
        System.out.println("  - /limpar                        Limpar o terminal");
        System.out.println("  - /ajuda                         Mostrar esta lista de comandos");
        System.out.println();
        System.out.println("> Digite sua mensagem e pressione Enter:");
        System.out.println("==================================================================================");
        System.out.println();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        // Exibe a tela de instrucoes uma unica vez ao entrar para nao poluir o chat.
        exibirTelaInicial();

        try {
            while (true) {
                System.out.print("> ");
                System.out.print(" ");

                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("/sair")) {
                    try {
                        server.disconnectUser(username);
                    } catch (RemoteException re) {
                        System.out.println("Algo deu errado ao tentar sair... mas ja estamos cuidando disso!");
                    }
                    System.out.println("Voce saiu do chat. Sentiremos sua falta (ou nao kkkk). Ate logo!");
                    System.exit(0);

                } else if (input.startsWith("/private ")) {
                    String[] parts = input.split(" ", 3);
                    if (parts.length >= 3) {
                        String receiver = parts[1];
                        String message = parts[2];
                        try {
                            server.sendPrivateMessage(username, receiver, message);
                        } catch (RemoteException re) {
                            System.out.println("Nao conseguimos enviar sua mensagem privada. Tente novamente!");
                        }
                    } else {
                        System.out.println("Formato invalido. Tente assim: /private Fulano Ola, tudo bem?");
                    }

                } else if (input.startsWith("/anonimo ")) {
                    String[] parts = input.split(" ", 3);
                    if (parts.length >= 3) {
                        String receiver = parts[1];
                        String message = parts[2];
                        try {
                            server.sendAnonymousMessage(receiver, message);
                        } catch (RemoteException re) {
                            System.out.println("A mensagem anonima se perdeu no espaco... tente outra vez.");
                        }
                    } else {
                        System.out.println("Formato invalido. Use: /anonimo Cicrano Voce nunca sabera quem sou eu.");
                    }

                } else if (input.equalsIgnoreCase("/users")) {
                    try {
                        System.out.println();
                        System.out.println("========== USUARIOS ONLINE ==========");
                        for (String user : server.getOnlineUsers()) {
                            System.out.println(" - " + user);
                        }
                        System.out.println("=====================================");
                        System.out.println();
                    } catch (RemoteException re) {
                        System.out.println("Problema ao listar usuarios online. Tente novamente.");
                    }

                } else if (input.equalsIgnoreCase("/ajuda")) {
                    exibirTelaInicial();

                } else if (input.equalsIgnoreCase("/limpar")) {
                    try {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    } catch (Exception ex) {
                        for (int i = 0; i < 50; i++)
                            System.out.println();
                    }

                } else {
                    try {
                        server.broadcastMessage(username, input);
                    } catch (RemoteException re) {
                        System.out.println("Mensagem nao enviada. Talvez o servidor esteja de mau humor.");
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
}
