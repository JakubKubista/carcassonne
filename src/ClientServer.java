import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.io.IOException;
import java.util.Collection;
 
/**
 * Třída ChoiceMenu nám nabídne volbu client-server
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ClientServer extends World
{
    private Label menuLabel1;
    private Label menuLabel2;

    private String[] defaultNames = new String[6];
    private GreenfootSound sound1;
    
    private Server server;
    private Client client;
    
    private MainMenu mainMenu;
    private Board board;

    /**
     * Constructor for objects of class PauseMenu. Do konstruktoru si naimplementujeme neviditelne
     * Labely, ktere nas odkazuji na dalsi tridy a slouzi nam tedy jako menu.
     * 
     */
    public ClientServer(MainMenu mainMenu, GreenfootSound sound1, String[] defaultNames)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        this.mainMenu = mainMenu;
        
        this.defaultNames = defaultNames;
        menuLabel1 = new Label();
        addObject(menuLabel1, 670, 327);
        menuLabel1.setImagine("MENULABEL");
        
        menuLabel2 = new Label();
        addObject(menuLabel2, 663, 396);
        menuLabel2.setImagine("MENULABEL");
             
        this.sound1 = sound1;
        }
        
    ClientServer getClientServer(){
        return this;
    }
    /**
     * Meto act nam zde slouzi jako menu, ve kterem prepiname worldy. Jen vytvarime.
     */
    public void act(){
        if((Greenfoot.mouseClicked(menuLabel1.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(1,defaultNames,1);   
            Greenfoot.setWorld(board);
            Server server = Server.getCurrent();
            server.setPort(5000);
            try{
                server.startServer();
            } catch (IOException e) {}
            board.setSound(sound1);
            }
        if((Greenfoot.mouseClicked(menuLabel2.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(1,defaultNames,2);   
            Greenfoot.setWorld(board);   
            client = new Client();
            client.createClientStatus();
            board.setSound(sound1);
            }
    }
}
