import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.*;
import java.io.*;
import java.util.*;
/**
 * Třída PauseMenu je pomocný world, určený pro pozastavení hry, ukládání a nahrávání. 
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class PauseMenu extends World
{
    private Label menuLabel1;
    private Label menuLabel2;
    private Label menuLabel3;
    private Label menuLabel4;
    private Label menuLabel5;
    private Label savedLabel;
    
    private Board board;
    private MainMenu mainMenu;
    private Manual manual;
    
    private MoveCard movecard;
    private MovePawn movepawn;
    private Game game;
    private String[] playerNames = new String[5];
    
    private int phaseNumber;
    private GreenfootSound sound1;
    
    /**
     * Constructor for objects of class PauseMenu. Do konstruktoru si naimplementujeme neviditelne
     * Labely, ktere nas odkazuji na dalsi tridy a slouzi nam tedy jako menu.
     * 
     */
    public PauseMenu()
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
        addObject(menuLabel5, 894, 457);
        menuLabel5.setImagine("MENULABEL2");
        
        savedLabel = new Label();
        addObject(savedLabel, 680, 500);
        savedLabel.setImage(new GreenfootImage("", 0, null, null));
    }
    /**
     * Vraci hodnotu aktualniho Worldu.
     */
    PauseMenu getPauseMenu(){
        return this;
    }
    public void setSound(GreenfootSound sound1){
        this.sound1 = sound1;
    }
    /**
     * Nastavi board pro toto menu, aby se mohla hra pozastavit.
     */
    public void setBoard(Board board){
        this.board = board; 
    }
    /**
     * Dulezita metoda pro pripraveni atributu potrebnych k ukladani.
     */
    public void setForSave(MoveCard movecard, MovePawn movepawn, Game game,int phaseNumber,String[] playerNames){
        this.movecard = movecard; 
        this.movepawn = movepawn;
        this.game = game;
        this.phaseNumber = phaseNumber;
        this.playerNames = playerNames;
    }
    /**
     * Nastavi board pro toto menu, aby se mohla hra ukoncit - vjet do hlavniho menu.
     */
    public void setMainMenu(MainMenu mainMenu){
        this.mainMenu = mainMenu; 
    }
    /**
     * Klicova metoda k ukladani. Do dvou promennych algoritmus nastavi cilove cesty pro ukladani
     * jak pro kartu tak pro pesaka. Predavame celkem 14 hodnot, 7 pro kartu a 7 pro pesaka.
     * Ukladani je reseno nejednodussim zpusobem a to třídou BufferedWriter, která ukládá hodnoty
     * do textových souborů v podobě stringu. V obou pŕípadech jsou jednotlive hodnoty oddeleny
     * oddelovacem.
     */
    public void save(){
    String fileName1 = "saves/card.txt";  
    String fileName2 = "saves/pawn.txt";
    try {
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
        }
        bwFile2.close();      
        } catch (Exception e){
            System.out.println("Chyba zapsani do souboru: " + fileName2 + "\n" + e.getMessage());
    }
    savedLabel.setImagine("SAVED");
   }
    /**
     * Meto act nam zde slouzi jako menu, ve kterem prepiname worldy. Vyjmkou je zde 3 Label urceny
     * pro save.
     */
    public void act(){
        if((Greenfoot.mouseClicked(menuLabel1.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            Greenfoot.setWorld(board);  
            savedLabel.setImage(new GreenfootImage("", 0, null, null));
        }
        if((Greenfoot.mouseClicked(menuLabel2.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board();
            sound1.stop();
            board.setSound(sound1);
            Greenfoot.setWorld(board);  
        }
        if((Greenfoot.mouseClicked(menuLabel3.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            save();
        }
        if((Greenfoot.mouseClicked(menuLabel4.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            mainMenu = new MainMenu();
            sound1.stop();
            Greenfoot.setWorld(mainMenu);  
        }
        if((Greenfoot.mouseClicked(menuLabel5.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            manual = new Manual(this);
            Greenfoot.setWorld(manual);        
        }
    }
}
