import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/**
 * Třída Server nám zprostředkovává celou síťovou komunikaci. Vytvoří kanál na který se připojují klienti.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Server
{
    
    private static Server current;
    private static ServerSocket serverSocket;
    private DataOutputStream dataOutputStream;
    private int port;
    private ArrayList<ServerThread> clientThreads;
    private ConnectThread newConnection;
    /**
     * Privatni singleton
     */
    private Server() {
    }
    /**
     * Vytvoreni singletona
     */
    public static Server getCurrent() {
        if (current == null) {
            current = new Server();
        }
        return current;
    }
    /**
     * Nastaveni portu naslouchajicim pripojeni
     */
    public void setPort(int port){
        this.port = port;
    }
    /**
     * Zalozeni serveru
     */
    public void startServer() throws IOException {
        this.clientThreads = new ArrayList<ServerThread>();
        System.out.println(" ...Loading...");
        //Spusteni vlakna, ktere bude cekat na pripojeni neomezeneho mnozstvi klientu.
        try{
            serverSocket = new ServerSocket(port);
            newConnection = new ConnectThread(serverSocket, this);
            Thread thread = new Thread((Runnable) newConnection);
            thread.start();
            System.out.println("Game was created");System.out.println();
            System.out.println("Waiting for players..");
        } catch(IOException e){
            System.out.println("Error! Server wasn't created - connection lost." + e.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }
    /**
     * Metoda volana vlaknem cekajici na pripojeni klientu. Vytvori nove vlakno, ktere zpracovava
     * prikazy od jednoho klienta. Obsahuje seznam vsech vlaken obsluhujicich vsechny klienty.
     */
    public void connectUser(DataInputStream dataInputStream, DataOutputStream dataOutputStream, Socket socket){
        ServerThread user = new ServerThread(dataOutputStream, dataInputStream, this, socket);
        Thread thread = new Thread(user);
        thread.start();
        this.clientThreads.add(user);
    }
    /**
     * Metoda volana vlaknem obsluhujicim klienta, preda zpravu vsem ostatnim vlaknum - klientum
     */
    public void sendCommandToAllClients(String words){
        for (ServerThread connectedThread : clientThreads){
            try {
                connectedThread.sendCommand(words);
            } catch (IOException e){
                System.out.println("This command couldn't send : " + words + " to thread.");
                e.printStackTrace();
            }
        }
    }
    /**
     * Metoda uklizejici vlakna po odpojeni jejich klientu
     */
    public synchronized void disconnectThread(ServerThread sender){
        this.clientThreads.remove(sender);
    }
    /**
    * Zastaveni serveru a odpojeni vsech klientu.
    */
    public synchronized void stopServer() throws IOException{
        for (ServerThread connectedThread : clientThreads){
            connectedThread.exit();
        }
        clientThreads.clear();
        serverSocket.close();
        serverSocket = null;
    }
    public int getWords(){
        return 1;
    }
}

