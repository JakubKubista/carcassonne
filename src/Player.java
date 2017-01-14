import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Třída Player je vytvořená za pomoci třídy Game a hráči vlastní figurky, které se v této
 * tříde vytvářejí.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Player extends Actor
{
   private ArrayList<Pawn> pawns = new ArrayList<Pawn>();
   private Pawn[][] pawn = new Pawn[5][7];
   private String name;
   private Pocket pocket;
   private Score score;
   private boolean pawnsAdded;
   private int number;
   
   /**
    * Konstruktor výčtového typu Player nám nastaví obrázek hráče, který je definován jeho názvem.
    */
   public Player(String name, Pocket pocket, int number) {
       this.name = name;
       this.number = number;
       setImage("images/player_" + name + ".png");
   }
  
   public void getPocket(Pocket pocket){
       this.pocket = pocket;
    }
   
   public void setPlayerImage(String name){
       this.name = name;
       setImage("images/player_" + name + ".png"); 
   }
    
   public void addFiguresForPlayer(Pocket pocket) {
        for(int j = 0; j<=pocket.getNumber() ; j++){
            pawns.add(pawn[number][j] = new Pawn(name,j,pocket,number));
        }
        pocket.addFiguresForPlayer(pawn,pocket.getNumber(),number);
   }
    
   public void delFiguresOfPlayer(Pocket pocket) {
        pocket.delFiguresOfPlayer(pawn,pocket.getNumber(),number);
        pawns.clear();
   }
   
   /**
    * Tato metoda vrací hodnotu o kterého hráče (jeho label) se jedná.
    */
   public String getPlayerName() {
       return name;
   }
   
   /**
    * Tato metoda přidá hráči figurku mezi jeho figurky za pomocí ArrayListu.
    */
   public void addPawn(Pawn pawn) {
       pawns.add(pawn);
   }
   
   /**
    * Tato metoda vytvoří hráči figurky za pomocí vestavěné metody toArray, do které vložíme třídu
    * a její index pole, jenž chceme vytvořit.
    */
   public ArrayList<Pawn> getPawn() {
       return pawns;
    }
    
   public Pawn[][] getPawns() {
       return pawn;
    }
    
   public Pawn getPawn2(int i, int j) {
       return pawn[i][j];
    }
    
   public void setPlayerPawnsAdded(boolean pawnsAdded){
       this.pawnsAdded = pawnsAdded;
   }
    
   public boolean getPlayerPawnsAdded(){
       return pawnsAdded;
   }
  
   public void setScore(Score score){
       this.score = score;
   }
   
   public Score getScore(){
       return score;
   }
   
   public int getTotalScore(){
       return score.getTotalScore(number);
   }
   
   public int getNumber(){
       return number;
   }
}
