import greenfoot.*; 
import java.lang.Object.*;
import java.lang.Class.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
/**
 * Třída MainMenu je první třída, jenž aplikace spustí, odtud se rozhodujeme, jaký typ hry chceme hrát
 * nebo můžeme nahrát poslední uloženou hru.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class MainMenu extends World
{
    private Label menuLabel1;
    private Label menuLabel2;
    private Label menuLabel3;
    private Label menuLabel4;
    private Label menuLabel5;
    private Label menuLabel6;
    private GreenfootSound sound1;
    private Board board;
    private Loading loading  = new Loading();
    private Manual manual  = new Manual(this);
    private ChoiceMenu choiceMenu;
    private ClientServer clientServer;
    private Settings settings;
    private String[] defaultNames = new String[6];
    
    private String[] playerNames;
    private int numberOfPlayers;
    /**
     * Constructor for objects of class PauseMenu. Do konstruktoru si naimplementujeme neviditelne
     * Labely, ktere nas odkazuji na dalsi tridy a slouzi nam tedy jako menu.
     * 
     */
    public MainMenu()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        
        menuLabel1 = new Label();
        addObject(menuLabel1, 689, 274);
        menuLabel1.setImagine("MENULABEL");
        
        menuLabel2 = new Label();
        addObject(menuLabel2, 611, 336);
        menuLabel2.setImagine("MENULABEL");
        
        menuLabel3 = new Label();
        addObject(menuLabel3, 696, 390);
        menuLabel3.setImagine("MENULABEL");
        
        menuLabel4 = new Label();
        addObject(menuLabel4, 646, 443);
        menuLabel4.setImagine("MENULABEL");
        
        menuLabel5 = new Label();
        addObject(menuLabel5, 891, 462);
        menuLabel5.setImagine("MENULABEL2");
        
        menuLabel6 = new Label();
        addObject(menuLabel6, 839, 461);
        menuLabel6.setImagine("MENULABEL2");
        //Greenfoot.start();
        for(int i=1;i<6;i++){
            defaultNames[i]=("Lord"+i);
        }
        
        sound1 = new GreenfootSound("sounds/MAINMENU.MP3");
        sound1.play();
   }
    
    MainMenu getMainMenu(){
        return this;
    }
    
    public GreenfootSound getSound(){
        return sound1;
    }
    
    /**
     * Meto act nam zde slouzi jako menu, ve kterem prepiname worldy. Vyjmkou je zde 4 Label urceny
     * pro zavolani JFrame, ktery nam muze nastavit nektere atributy ve hre.
     */
    public void act(){
        if((Greenfoot.mouseClicked(menuLabel1.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
                if(settings!=null){
                board = new Board(settings.getNumberOfPlayers(),settings.getPackNames());
                board.setSound(sound1);
                Greenfoot.setWorld(board);  
                }else{
                choiceMenu  = new ChoiceMenu(this,sound1,defaultNames);
                Greenfoot.setWorld(choiceMenu);  
                }
            }
        if((Greenfoot.mouseClicked(menuLabel2.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(0,defaultNames);
            board.setSound(sound1);
            Greenfoot.setWorld(board);  
            }
        if((Greenfoot.mouseClicked(menuLabel3.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            clientServer  = new ClientServer(this,sound1,defaultNames);
            Greenfoot.setWorld(clientServer); 
        }
        if((Greenfoot.mouseClicked(menuLabel4.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board();
            board.setSound(sound1);
            Greenfoot.setWorld(loading); 
            loading.loadBoard(board);
        /*  if(!loading){
              try {
               Thread.sleep(1);
               }catch (Exception e){
                   System.out.println("Nastala vyjimka (Chybna v posunu karty):" + e.toString());
                   e.printStackTrace();
               }
               Thread t = new Thread();
               t.start();
               loading = true;
          }else{
              board.load();
               Greenfoot.setWorld(board);       
          }
          */
        }
        if((Greenfoot.mouseClicked(menuLabel5.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            Greenfoot.setWorld(manual);        
        }
        if((Greenfoot.mouseClicked(menuLabel6.getLabel()))) {
            settings = new Settings(); 
        }
        if(settings!=null){
            if(settings.getTurnOff()==true){
            settings.dispose();  
            }
        }
    }
}
