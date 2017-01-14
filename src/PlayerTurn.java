import static java.lang.String.format;
import java.awt.Color;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Třída Loading je třída, který existuje pro spestření designu a říká nám kdo je na tahu
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */

public class PlayerTurn extends World
{
    private static final String FORMAT_PLAYERTURN_LABEL = "%s";
    private Board board;
    private Label next;
    private Label playerTurnLabel;
    private String[] playerNames = new String[5];
    private int actualPlayer;
    /**
     * Constructor for objects of class ChoiseMenu.
     * 
     */
    public PlayerTurn(Board board, String[] playerNames)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        
        this.board = board;
        this.playerNames = playerNames;
        
        next = new Label();
        addObject(next, 680, 410);
        
        playerTurnLabel = new Label(format(FORMAT_PLAYERTURN_LABEL, ""), 30, Color.black, null);
        addObject(playerTurnLabel, 675,  328);
        playerTurnLabel.setOrigX(605);
        playerTurnLabel.setAlign(1);
    }
    public void setPlayerNames(String[] playerNames){
        this.playerNames = playerNames;
    }
    public void setPlayer(int actualPlayer){
        this.actualPlayer = actualPlayer;
    }
    public void act()
    {
       playerTurnLabel.setText(format(FORMAT_PLAYERTURN_LABEL, playerNames[actualPlayer+1]+"'s turn"));
       next.setImagine("sipka1");
            if((Greenfoot.mouseClicked(this))||(Greenfoot.mouseClicked(next))||(Greenfoot.mouseClicked(playerTurnLabel))) {
             Greenfoot.playSound("sounds/menuClick.wav");
             Greenfoot.setWorld(board);     
            }
    }
}
