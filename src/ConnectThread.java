import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Třída ConnectThread reprezentuje vlákna klientů a serveru. V této třídě nasloucháme a čekáme
 * kdo se na server připojí. Poté server propojíme s klientem.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ConnectThread implements Runnable {
    private static ServerSocket serverSocket;
    private Server server;
    
    public ConnectThread(ServerSocket serverSocket, Server server){
        this.serverSocket = serverSocket;
        this.server = server;
    }
    @Override
    public void run() {
        try{
            this.listen();
        } catch (Exception e) {
            System.out.println("Nastala vyjimka (Time out - no connection):" + e.toString());
            e.printStackTrace();
        }
    }
    private void listen() throws IOException, InterruptedException{
        while (true){
            Socket socket = serverSocket.accept();
            System.out.println("New client joined! (IP: " + socket.getInetAddress() + ")");
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            this.server.connectUser(dataInputStream, dataOutputStream, socket);
        }
    }
}