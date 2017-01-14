import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Write a description of class ServerThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ServerThread implements Runnable {
    private DataOutputStream dataOutputStream;
    private DataInputStream dataInputStream;
    private Socket socket;
    private Server server;
    private HashMap<String, String[]> oldCommands;
    private String command;
    private int myId;

    public ServerThread(DataOutputStream dataOutputStream, DataInputStream dataInputStream, Server server, Socket socket){
        this.dataOutputStream = dataOutputStream;
        this.dataInputStream = dataInputStream;
        this.server = server;
        this.socket = socket;
        this.oldCommands = new HashMap<String, String[]>();
        this.command = "";
    }

    /**
     * Hlavni naslouchajici a reagujici smycka. Cte prikazy od klienta, zpracuje je a provede prislusnou akci.
     * Prislusna akce = vetsinou volani herni logiky hlavniho serveru
     */
    @Override
    public void run() {
        while(!command.equals("$")){
            try{
                command = dataInputStream.readUTF();
                server.sendCommandToAllClients(command);
                this.command = command;
            } catch (IOException e) {
                System.out.println("Nastala vyjimka (Neslo priradit hodnotu ke command v ClientThread):" + e.toString());
                e.printStackTrace();
            }
        }
    }
    /**
     * Metoda hashCode doplňuje equals při jejím ověření řádným výpočetem parametru.
     */
    public int hashCode() {
        int param = 3;
        if(this!=null){
        param = param *Integer.valueOf(command);
        }
        return param;
    }
    /**
     * Metoda oznamujici ukonceni hry klienta
     */
    public void exit() throws IOException{
        this.command = "$";
        this.dataOutputStream.writeUTF("$");
    }

    /**
     * Univerzalni odeslani jakehokoliv prikazu
     */
    public  void sendCommand(String words) throws IOException{
        this.dataOutputStream.writeUTF(words);
    }
    
    public int getCommand(){
        return Integer.valueOf(command);
    }
}