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
 * Třída Card odpovídá vždy jen jedné z karet a charakterizuje její stav a polohu.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Card extends Actor
{
    private Player player;
    private Tile tile;
    private int i = 1;
    private int image;
    private int realNumber;
    private boolean j;
    private boolean absolute;
    private int oldX = 0;
    private int oldY = 0;
    private int x;
    private int y;
    private int left = 0;
    private Timer timer;
    private MoveLocation movelocation;
    private int tileNumber;
    private int a = 0;
    
    public Card(int i,Deck deck,MoveLocation movelocation){
        this.i = i;
        this.movelocation = movelocation;
        setImage(i,deck,movelocation);
    }    
    
    public Card(int i, int x, int y, int image,MoveLocation movelocation){
        this.i = i;
        this.x = x;
        this.y = y;
        this.image = image;
        this.movelocation = movelocation;
        if (image==0){
            setImage("images/0.png");
            this.j = true;
        }
        if((image>=1)&&(image<=4)){
            setImage("images/1.png");
        }     
        if((image>=5)&&(image<=6)){
            setImage("images/2.png");
        }
        if((image>=7)&&(image<=7)){
            setImage("images/3.png");
        }     
        if((image>=8)&&(image<=10)){
            setImage("images/4.png");
        }
        if((image>=11)&&(image<=11)){
            setImage("images/5.png");
        }     
        if((image>=12)&&(image<=12)){
            setImage("images/6.png");
        }
        if((image>=13)&&(image<=14)){
            setImage("images/7.png");
        }     
        if((image>=15)&&(image<=17)){
            setImage("images/8.png");
        }
        if((image>=18)&&(image<=19)){
            setImage("images/9.png");
        }     
        if((image>=20)&&(image<=22)){
            setImage("images/10.png");
        }
        if((image>=23)&&(image<=24)){
            setImage("images/11.png");
        }     
        if((image>=25)&&(image<=25)){
            setImage("images/12.png");
        }
        if((image>=26)&&(image<=27)){
            setImage("images/13.png");
        }     
        if((image>=28)&&(image<=29)){
            setImage("images/14.png");
        }
        if((image>=30)&&(image<=32)){
            setImage("images/15.png");
        }     
        if((image>=33)&&(image<=37)){
            setImage("images/16.png");
        }
        if((image>=38)&&(image<=40)){
            setImage("images/17.png");
        }     
        if((image>=41)&&(image<=43)){
            setImage("images/18.png");
        }
        if((image>=44)&&(image<=46)){
            setImage("images/19.png");
        }     
        if((image>=47)&&(image<=49)){
            setImage("images/20.png");
        }
        if((image>=50)&&(image<=57)){
            setImage("images/21.png");
        }     
        if((image>=58)&&(image<=66)){
            setImage("images/22.png");
        }
        if((image>=67)&&(image<=70)){
            setImage("images/23.png");
        }     
        if((image>=71)&&(image<=71)){
            setImage("images/24.png");
        }
      }    
    
    public Player getPlayer() {
        return player;
    }
    
    private void setX(int x){
        this.x = x;
    }
    
    private void setY(int y){
        this.y = y;
    }
    
    /**
     * Jedna z primárních metod, jelikož umožňuje posun a neboli rozmístění dané karty. Je zavolána
     * standartně ze Třídy Tile a nastaví nám souřadnice karty na souřadnice Tilu a označí o který
     * Tile se jedná do své proměné. Zároveň je uvedena metoda sleep na 100ms, jelikož se karta
     * pohybova příliš rychle
     * @throws Exception e ním nahlásí výjmku pokud se nepodařilo kartu při pohybu "uspat", aby se
     * nepřesouvala takovou rychlostí. Výjmka je ošetřena metodou Try-catch.
     */
    public void setTile(Tile tile) {
        if (this.tile != null) {
            this.oldX = this.tile.getX();
            this.oldY = this.tile.getY();
        }
        this.tile = tile;
        if (tile != null) {
          try {
           Thread.sleep(100);
           }catch (Exception e){
               System.out.println("Nastala vyjimka (Chybna v posunu karty):" + e.toString());
               e.printStackTrace();
           }
           Thread t = new Thread();
           t.start();
           this.setLocation(tile.getX(), tile.getY());
        } 
        animate();
    }
    
    public void setAbsolute(boolean absolute){
        this.absolute = absolute;
    } 
    
    public boolean getAbsolute(){
        return this.absolute;
    } 
    
    public void setTileAbsolute(Tile tile) {
        if (this.tile != null) {
            this.oldX = this.tile.getX();
            this.oldY = this.tile.getY();
        }
        this.tile = tile;
        if (tile != null) {
           try {
           Thread.sleep(100);
           }catch (Exception e){
            System.out.println("Nastala vyjimka (Nebyla provedena metoda sleep):" + e.toString());
            e.printStackTrace();
           }
           Thread t = new Thread();
           t.start();
           this.setLocation(tile.getX(), tile.getY());
        } 
        animate();
    }
    
    public Tile getTile() {
        return tile;
    }
    
    public void setRealNumber(int i){
        realNumber = i;
    }
    
    public int getRealNumber(){
        return realNumber;
    }
    
    public int getI(){
        return i;
    }
    
    /**
     * Tato metoda umožňuje nastavit obrátek pro danou kartu. Jelikož ve hře je 72 karet, z toho
     * máme 25 odlišných druhů a každý druh má přesně daný počet karet, tak bylo zvoleno řešení
     * za pomoci podmínek. Podle pravidel je první karta přesně stanovena, umístěna doprostřed
     * hracího pole a nemůžeme s ní jakkoliv manipulovat, tak je vymezena zvlášť ostatní karty 
     * jsou řešeny za pomoci indexu, aby se neopakovali, ale zároveň rozděleny náhodným generátorem,
     * aby se nejednalo o posloupnost.
     * @param i udává číslo karty, pro kterou obrázek vybíráme.
     * @param deck udáva deck karet
     */
    private void setImage(int i, Deck deck,MoveLocation movelocation) {
        if((i<0)||(i>=72)){
            throw new CardException("Karta c." + i +" neexistuje.");
        }
        
        if (i==0){
            setImage("images/0.png");
            deck.setUsed(i);
            image = i;
            this.j = true;
            setRealNumber(i);
        }

        if ((i>0)&&(i<72)){
          this.j = false;
          Random ran = new Random();
          int x = ran.nextInt(71) + 1; 
          if(deck.getUsed(x)){
              setImage(i,deck,movelocation);
              }else{
                   setRealNumber(x);
                    if((x>=1)&&(x<=4)){
                        setImage("images/1.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=5)&&(x<=6)){
                        setImage("images/2.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=7)&&(x<=7)){
                        setImage("images/3.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=8)&&(x<=10)){
                        setImage("images/4.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=11)&&(x<=11)){
                        setImage("images/5.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=12)&&(x<=12)){
                        setImage("images/6.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=13)&&(x<=14)){
                        setImage("images/7.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=15)&&(x<=17)){
                        setImage("images/8.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=18)&&(x<=19)){
                        setImage("images/9.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=20)&&(x<=22)){
                        setImage("images/10.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=23)&&(x<=24)){
                        setImage("images/11.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=25)&&(x<=25)){
                        setImage("images/12.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=26)&&(x<=27)){
                        setImage("images/13.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=28)&&(x<=29)){
                        setImage("images/14.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=30)&&(x<=32)){
                        setImage("images/15.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=33)&&(x<=37)){
                        setImage("images/16.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=38)&&(x<=40)){
                        setImage("images/17.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=41)&&(x<=43)){
                        setImage("images/18.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=44)&&(x<=46)){
                        setImage("images/19.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=47)&&(x<=49)){
                        setImage("images/20.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=50)&&(x<=57)){
                        setImage("images/21.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=58)&&(x<=66)){
                        setImage("images/22.png");
                        image = x;
                        deck.setUsed(x);
                    }
                    if((x>=67)&&(x<=70)){
                        setImage("images/23.png");
                        image = x;
                        deck.setUsed(x);
                    }     
                    if((x>=71)&&(x<=71)){
                        setImage("images/24.png");
                        image = x;
                        deck.setUsed(x);
                    }
              }
            }
        }
        
    /**
     * Ověření, zda jsou objekty umístěny na hrací desce.
     * @return Hodnota true nebo false.
     */
    public boolean equals(){
        if((this.oldX<0)||(this.oldY<0)){
            return false;
        }else if ((this.oldX>1350)||(this.oldY>599)){
            return false;
        }        
        return true;
    }
    
    public void animate() {
 
    }
    
    public int getA(){
        return a;
    }
    
    public void setA(int a){
        this.a = a;
    }
    
    public void radius(int a) {
        this.a = a;
        if(a!=0){
          for(int b = 0; b<a; b++){
           loadTurn();
          }
        }
    }
    
    public void setTileNumber(int tileNumber) {
        this.tileNumber = tileNumber;
    }
    
    public int getTileNumber() {
        return tileNumber;
    }
    
    /**
    * Tato metoda stejně jako act v Board nám neustále běží. Umožňuje otáčení karet, ale je 
    * ošetřena podmínkami, že Tile nesmí být prázdný a že se nesmmí jednat o použitý Tile.
    */
    public void act() {
        if (Greenfoot.mouseClicked(this)){
        if(tile!=null){
            if(!tile.getAbsolute()){
                movelocation.turn(tileNumber,realNumber);
                turn(90); 
                a++;
                setA(a);
                if(a==4){
                    a=0;
                    setA(a);
                }
            }
        }
        }
    }
    
    public void loadTurn(){
        movelocation.turn(tileNumber,realNumber);
        turn(90);
    }
}    