import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
/**
 * Třída ClientThread reprezentuje vlákno klienta, které čte UTF tval a chytá výjmky
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ClientThread implements Runnable {
    private Client client;
    private static DataInputStream dataInputStream;
    private static DataOutputStream dataOutputStream;
    String command;

    public ClientThread(DataInputStream dataInputStream, DataOutputStream dataOutputStream){
        this.dataInputStream = dataInputStream;
        this.dataOutputStream = dataOutputStream;
        command = "";
    }
    /**
     * Hlavni smycka, ktera cte a zpracovava zpravy od serveru a vykonava prislusne akce.
     * Vetsinou to jsou volani do hlavni tridy klienta, ten potom vola udalosti UI.
     */
    @Override
    public void run() {
        while(command != "$"){
            try {
                command = dataInputStream.readUTF();
                System.out.print("Echo reply: "+ command + "\n ");
             } catch (IOException e) {
                System.out.println("Nastala vyjimka (Neslo priradit hodnotu ke command v ClientThread):" + e.toString());
                e.printStackTrace();
             }
        }
    }
    /**
     * Manualni odpojeni se ze hry a zaroven vzdani se.
     */
    public synchronized void exit() throws IOException{
        this.command = "$";
        this.dataOutputStream.writeUTF("$");
    }
    /**
     * Univerzalni poslani prikazu na server.
     */
    public void sendCommand(String words) throws IOException {
        this.dataOutputStream.writeUTF(words);
    }
}
