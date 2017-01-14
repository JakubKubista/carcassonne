import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Třída Manual obsahuje veškeré instrukce ke hře. 
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Manual extends World
{
    private Label nextPage;
    private Label prePage;
    private CallMenu callMenu;
    private MainMenu mainMenu;
    private PauseMenu pauseMenu;
    private ChoiceMenu choiceMenu;
    private int actual = 1;
    /**
     * Constructor for objects of class Manual. Mame zde tri konstruktory, Manual je zavolan podle
     * toho ktery World ho vytvoril a podle tohoto faktu ma i nastaveny konstruktor.
     * 
     */
    public Manual(MainMenu mainMenu)
    {  
        // Create a new world with 1330x2048 cells with a cell size of 1x1 pixels.
        super(1330, 2048, 1); 
        this.mainMenu =  mainMenu;
        setThisManual();
    }
    public Manual(PauseMenu pauseMenu)
    {  
        // Create a new world with 1330x2048 cells with a cell size of 1x1 pixels.
        super(1330, 2048, 1); 
        this.pauseMenu =  pauseMenu;
        setThisManual();
    }
    public Manual(ChoiceMenu choiceMenu)
    {  
        // Create a new world with 1330x2048 cells with a cell size of 1x1 pixels.
        super(1330, 2048, 1);  
        this.choiceMenu =  choiceMenu;
        setThisManual();
    }
    /**
     * Nastaveni pro chod manualu.
     * 
     */
    public void setThisManual()
    {    
        actual = 1;
        
        nextPage = new Label();
        addObject(nextPage, 1132, 2001);
        
        prePage = new Label();
        addObject(prePage, 122, 2007);
        
        prePage.setImage(new GreenfootImage("", 0, null, null));
        nextPage.setImagine("sipka1");
        
        callMenu = new CallMenu();
        addObject(callMenu, 667, 60);
    }    
    /**
     * V teto metode se pohybujeme podobne jako ve formatu pdf, muzeme otacet listy a ukoncit
     * prohlizeni.
     * 
     */        
    public void act(){
        if(mainMenu!=null){
            if((Greenfoot.mouseClicked(callMenu.getCallMenu()))) {
                    Greenfoot.setWorld(mainMenu);  
            }
        }
        if(pauseMenu!=null){
            if((Greenfoot.mouseClicked(callMenu.getCallMenu()))) {
                    Greenfoot.setWorld(pauseMenu);  
            }
        }
        if(choiceMenu!=null){
            if((Greenfoot.mouseClicked(callMenu.getCallMenu()))) {
                    Greenfoot.setWorld(choiceMenu);  
            }
        }
        if(actual == 1){
            prePage.setImage(new GreenfootImage("", 0, null, null));
            nextPage.setImagine("sipka1");
            if((Greenfoot.mouseClicked(nextPage.getLabel()))) {
                setBackground("images/manual2.png");
                actual = 2;
            }
        }
        else if(actual == 2){
            nextPage.setImagine("sipka1");
            prePage.setImagine("sipka0");
            if((Greenfoot.mouseClicked(nextPage.getLabel()))) {
                setBackground("images/manual3.png");
                actual = 3;
            }
            if((Greenfoot.mouseClicked(prePage.getLabel()))) {
                setBackground("images/manual1.png");
                actual = 1;
            }
        }
        else if(actual == 3){
            nextPage.setImagine("sipka1");
            if((Greenfoot.mouseClicked(nextPage.getLabel()))) {
                setBackground("images/manual4.png");
                actual = 4;
            }
            if((Greenfoot.mouseClicked(prePage.getLabel()))) {
                setBackground("images/manual2.png");
                actual = 2;
            }
        }
        else if(actual == 4){
            nextPage.setImagine("sipka1");
            if((Greenfoot.mouseClicked(nextPage.getLabel()))) {
                setBackground("images/manual5.png");
                actual = 5;
            }
            if((Greenfoot.mouseClicked(prePage.getLabel()))) {
                setBackground("images/manual3.png");
                actual = 3;
            }
        }
        else if(actual == 5){
            nextPage.setImage(new GreenfootImage("", 0, null, null));
            if((Greenfoot.mouseClicked(prePage.getLabel()))) {
                setBackground("images/manual4.png");
                actual = 4;
            }
        }
    }
}
