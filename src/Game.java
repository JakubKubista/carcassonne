import java.util.*;
import greenfoot.*; 

/**
 * Tato třída Game udává pole listu hráčů, kteří právě hrají a říká nám, který hráč bude dále na řadě.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Game
{
   public static boolean ANIMATION = false; 
   
   private ArrayList<Player> players = new ArrayList<Player>();
   private Player[] player = new Player[5];
   private Score score;
   private String playerName;
   public static int current;
   public static int i;

      /**
    * Metoda jen nam vytvori hrace
    */
   public void createPlayers(int i, Pocket pocket){
          current = 0;    
          this.i = i;
          for(int j=i;j>=0;j--){
               if(j==0){playerName = "blue";}
               if(j==1){playerName = "yellow";}
               if(j==2){playerName = "green";}
               if(j==3){playerName = "red";}
               if(j==4){playerName = "black";}
                   players.add(player[j] = new Player(playerName, pocket, j));
                   pocket.addPlayer(player[j]);
          }
   }
   
  public void delPlayers(Pocket pocket) {
        pocket.delPlayers(player,i);
   }
   
  public void startingPlayer(Pocket pocket) {
        pocket.startingPlayer(player,current);
   }
   
   /**
    * Tato metoda nám vrací hráče, jenž je v příštím kole na tahu. Do proměnní i se dá aktuální index
    * aktuálního hráče a následně se zvýšeným indexem o 1 vrací daného hráče.
    */
   
   public int nextPlayer() {
           if (current==i){
               return 0;
           }else{
               return (current+1);
       }
   }   
   
    /**
    * Tato metoda spolu s předchozí umožní "předat žezlo" následujícímu hráči, jelikož nám nastaví
    * aktuálního hráče na následujícího.
    */
   public void nextPlayerTurn() {
        current = nextPlayer();
        player[current].setPlayerImage((player[current].getPlayerName()));
   }
   
   public int getCurrent(){
       return current; 
   }
   
   public void setCurrent(int current){
       this.current = current; 
   }
   
   public Player getPlayer(){
       return player[current];
    }
    
   public Player getPlayer(int i){
       return player[i];
    }
    
   public void setPlayer(int current){
       player[current].setPlayerImage((player[current].getPlayerName()));
    }
    
   public void setPawnsAdded(int i){
       player[i].setPlayerPawnsAdded(true);
   }
    
   public boolean getPawnsAdded(int i){
       return player[i].getPlayerPawnsAdded();
   }
   
   public int getI(){
       return i;
   }
   
   public void setScore(Score score){
       this.score = score;
   }
   
   public int getTotalScore(){
       return score.getTotalScore(current);
   }
   
   public int getTotalScore(int i){
       return score.getTotalScore(i);
   }
}
