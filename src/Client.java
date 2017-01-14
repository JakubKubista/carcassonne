import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Třída Client je jádrem klienta, kterého připojujeme na server pro možný multiplayer
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
class Client
{
    private static Socket socket;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    private static ClientThread clientThread;
    private static ClientStatus clientStatus;
    
    public static void connectToServer(String host, int port) throws IOException {
        socket = new Socket(host, port);
        dataOutputStream = new DataOutputStream(socket.getOutputStream());
        dataInputStream = new DataInputStream(socket.getInputStream());
        clientThread = new ClientThread(dataInputStream, dataOutputStream);
        Thread thread = new Thread(clientThread);
        thread.start();
    }
    public static void createClientStatus(){
        try{
            connectToServer("127.0.0.1",5000);
        } catch (IOException e) {
            System.out.println("Nastala vyjimka (Neslo pripojit k serveru):" + e.toString());
            e.printStackTrace();
        }
        clientStatus = new ClientStatus(clientThread);
    }
}