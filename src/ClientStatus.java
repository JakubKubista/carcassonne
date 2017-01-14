import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Třída ClientStatus načítá potřebné proměnné, které následně předá Serveru
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ClientStatus {
    private ClientThread clientThread;
    public ClientStatus(ClientThread clientThread){
        this.clientThread = clientThread;
        String words;
        try {/*
        BufferedWriter bwFile1 = new BufferedWriter(new FileWriter(fileName1));
        for(int i = 0; i<72;i++){
                if(movecard.getCard(i) != null){
                 int a = movecard.getCard(i).getI();
                 int b = movecard.getCard(i).getRealNumber();
                 int c = movecard.getCard(i).getTileNumber();
                 int d = movecard.getCard(i).getA();
                 int e = movecard.getCard(i).getX();
                 int f = movecard.getCard(i).getY();
                 int g = phaseNumber;
                
                 bwFile1.write(Integer.toString(a));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(b));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(c));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(d));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(e));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(f));
                 bwFile1.write(";");
                 bwFile1.write(Integer.toString(g));
                 bwFile1.newLine();
            }
        }
        bwFile1.close();      
        } catch (Exception e){
            System.out.println("Chyba zapsani do souboru: " + fileName1 + "\n" + e.getMessage());
    }
                     
    try {
        BufferedWriter bwFile2 = new BufferedWriter(new FileWriter(fileName2));
        for(int i = 6; i >= 0;i--){
            for(int j = 0; j<=game.getI();j++){
                if(movepawn.getPawnAdvance(j,i) != null){
                     int a = game.getI();
                     int b = movepawn.getPawnAdvance(j,i).getPlayerNumber();
                     int c = movepawn.getPawnAdvance(j,i).getI();
                     int d = movepawn.getPawnAdvance(j,i).getTileNumber();
                     int e = movepawn.getPawnAdvance(j,i).getLocation().getNumber();
                     int f = movepawn.getPawnAdvance(j,i).getLocation().getType();
                     int g = game.getTotalScore(j);
                     
                     bwFile2.write(Integer.toString(a));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(b));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(c));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(d));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(e));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(f));
                     bwFile2.write(";");
                     bwFile2.write(Integer.toString(g));
                     for(int h = 1;h<=(game.getI()+1) ; h++){
                         bwFile2.write(";");
                         bwFile2.write(playerNames[h]);
                     }
                     bwFile2.newLine();
                }
            }
        }*/
            clientThread.exit();
        } catch (IOException e){
            System.out.println("Client Thread nalezen " + clientThread);
            e.printStackTrace();
        }
    }
}
