import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Třída ChoiceMenu nám umožňuje nastavit počet hráču.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ChoiceMenu extends World
{
    private Label menuLabel1;
    private Label menuLabel2;
    private Label menuLabel3;
    private Label menuLabel4;
    private Label menuLabel5;
    private Label menuLabel6;

    private String[] defaultNames = new String[6];
    private GreenfootSound sound1;
    
    private MainMenu mainMenu;
    private Manual manual  = new Manual(this);
    private Board board;
    /**
     * Constructor for objects of class PauseMenu. Do konstruktoru si naimplementujeme neviditelne
     * Labely, ktere nas odkazuji na dalsi tridy a slouzi nam tedy jako menu.
     * 
     */
    public ChoiceMenu(MainMenu mainMenu, GreenfootSound sound1, String[] defaultNames)
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        this.mainMenu = mainMenu;
        
        this.defaultNames = defaultNames;
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
        addObject(menuLabel6, 468, 457);
        menuLabel6.setImagine("MENULABEL2");
        
        this.sound1 = sound1;
    }
        
    ChoiceMenu getChoiceMenu(){
        return this;
    }
    /**
     * Meto act nam zde slouzi jako menu, ve kterem prepiname worldy. Jen vytvarime.
     */
    public void act(){
        if((Greenfoot.mouseClicked(menuLabel1.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(1,defaultNames);
            Greenfoot.setWorld(board);
            board.setSound(sound1);
            }
        if((Greenfoot.mouseClicked(menuLabel2.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(2,defaultNames);
            Greenfoot.setWorld(board);  
            board.setSound(sound1);
            }
        if((Greenfoot.mouseClicked(menuLabel3.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(3,defaultNames);
            Greenfoot.setWorld(board);  
            board.setSound(sound1);
        }
        if((Greenfoot.mouseClicked(menuLabel4.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            board = new Board(4,defaultNames);
            Greenfoot.setWorld(board); 
            board.setSound(sound1);
        }
        if((Greenfoot.mouseClicked(menuLabel5.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            Greenfoot.setWorld(manual);        
        }
        if((Greenfoot.mouseClicked(menuLabel6.getLabel()))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            Greenfoot.setWorld(mainMenu); 
        }
    }
}
