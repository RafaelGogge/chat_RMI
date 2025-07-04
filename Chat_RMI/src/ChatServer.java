import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer extends UnicastRemoteObject implements ChatServerInterface {

    private Map<String, ChatClientInterface> users;

    protected ChatServer() throws RemoteException {
        super();
        users = new HashMap<>();
    }

    @Override
    public synchronized boolean registerUser(String username, ChatClientInterface client) throws RemoteException {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, client);
        broadcastMessage("Servidor",
                "\"" + username + "\" acabou de entrar no chat. De as boas-vindas porque agora ficou mais divertido!");
        return true;
    }

    @Override
    public synchronized void broadcastMessage(String sender, String message) throws RemoteException {
        for (ChatClientInterface client : users.values()) {
            try {
                client.receiveMessage(sender, message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public synchronized void sendPrivateMessage(String sender, String receiver, String message) throws RemoteException {
        ChatClientInterface client = users.get(receiver);
        if (client != null) {
            client.receiveMessage(sender, message + " (mensagem privada somente para voce!)");
        } else {
            ChatClientInterface senderClient = users.get(sender);
            if (senderClient != null) {
                senderClient.receiveMessage("Servidor", "O usuario \"" + receiver + "\" nao esta online no momento.");
            }
        }
    }

    @Override
    public synchronized void sendAnonymousMessage(String receiver, String message) throws RemoteException {
        ChatClientInterface client = users.get(receiver);
        if (client != null) {
            client.receiveMessage("Anonimo", message + " (esta mensagem foi enviada de forma anonima)");
        } else {
            System.out.println("Tentativa de envio anonimo para \"" + receiver + "\", mas o usuario nao esta online.");
        }
    }

    @Override
    public synchronized List<String> getOnlineUsers() throws RemoteException {
        return new ArrayList<>(users.keySet());
    }

    @Override
    public synchronized void disconnectUser(String username) throws RemoteException {
        if (users.containsKey(username)) {
            users.remove(username);
            broadcastMessage("Servidor", "\"" + username
                    + "\" fugiu do chat! Mas tudo bem, a conversa ficou ate melhor agora. So nao conta pra ele!");
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer server = new ChatServer();
            LocateRegistry.createRegistry(1099);
            Naming.rebind("ChatServer", server);
            System.out.println("Servidor de chat RMI iniciado com sucesso. Aguardando conexoes...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
