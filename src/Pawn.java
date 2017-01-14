import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.*;
import java.util.*;
import java.awt.geom.*;  
import java.awt.geom.Point2D;
import java.awt.geom.CubicCurve2D;
import java.util.Timer.*;
import java.lang.*;
import java.util.Random.*;
/**
 * Třída Pawn odpovídá vždy jen jedné z figurek a charakterizuje její stav a polohu.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Pawn extends Actor
{
    private Player player;
    private Location location;
    private Timer timer;
    private String image;
    
    private int i = 1;
    private int oldX = 0;
    private int oldY = 0;

    private int locationNumber;
    private int playerNumber;
    private int tileNumber;
    
    private boolean absolute;
    
    public Pawn(String image,int i, Pocket pocket, int playerNumber){
        this.i = i;
        this.playerNumber = playerNumber;
        setImage("images/" + image + "_stand.png");
    }   
    
   public void setImagine(String image,String position) {
        try{
        this.setImage("images/" + image + "_" + position + ".png");
        }catch(IllegalArgumentException e){
            System.out.println("Nastala vyjimka (Chybně zadaný label):" + e.toString());
            e.printStackTrace();
        }
    } 
    
   public void deleteImagine() {
        this.setImage(new GreenfootImage("", 0, null, null));
    } 
    
    public Player getPlayer() {
        return player;
    }
    
   public int getPlayerNumber(){
       return playerNumber;
    }
    
    /**
     * Jedna z primárních metod, jelikož umožňuje posun a neboli rozmístění dané pawny. Je zavolána
     * standartně ze Třídy Location a nastaví nám souřadnice pawna na souřadnice Locat a označí o který
     * Locat se jedná do své proměné. Zároveň je uvedena metoda sleep na 100ms, jelikož se pawn
     * pohybova příliš rychle
     * @throws Exception e ním nahlásí výjmku pokud se nepodařilo kartu při pohybu "uspat", aby se
     * nepřesouvala takovou rychlostí. Výjmka je ošetřena metodou Try-catch.
     */
    public void setLocation(Location location) {
        if (this.location != null) {
            this.oldX = this.location.getX();
            this.oldY = this.location.getY();
        }
        this.location = location;
        if (location != null) {
          try {
           Thread.sleep(100);
           }catch (Exception e){
               System.out.println("Nastala vyjimka (Chybna v posunu karty):" + e.toString());
               e.printStackTrace();
           }
           Thread t = new Thread();
           t.start();
           this.setLocation(location.getX(), location.getY());;
        } 
        animate();
    }
    
    public void setAbsolute(boolean absolute){
        this.absolute = absolute;
    } 
    
    public boolean getAbsolute(){
        return this.absolute;
    } 
    
    public void setLocationAbsolute(Location location) {
        if (this.location != null) {
            this.oldX = this.location.getX();
            this.oldY = this.location.getY();
        }
        this.location = location;
        if (location != null) {
           try {
           Thread.sleep(100);
           }catch (Exception e){
            System.out.println("Nastala vyjimka (Nebyla provedena metoda sleep):" + e.toString());
            e.printStackTrace();
           }
           Thread t = new Thread();
           t.start();
           this.setLocation(location.getX(), location.getY());;
        } 
        animate();
    }
    
   public int getI(){
       return i;
   }
    
   public void setI(int i){
       this.i = i;
   }
   
   public int getTileNumber(){
       return tileNumber;
   }
   
   public void setTileNumber(int tileNumber){
       this.tileNumber = tileNumber;
   }
      
    public Location getLocation() {
        return location;
    }
    
    public void setLocationNumber(int locationNumber) {
        this.locationNumber = locationNumber;
    }
    
    public void animate() {
 
    }
    
    /**
     * Metoda act je neustále běžící metoda, která opakovaně zpracovává příkazy.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
