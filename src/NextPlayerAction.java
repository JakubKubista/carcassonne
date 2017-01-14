import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Třída NextPlayerAction je potomkem třídy Action a probíhají zde metody za účelem předání
 * žezla dalšímu hráči.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class NextPlayerAction extends Action 
{
    public NextPlayerAction() {
        setImage("images/sipka1.png");
    }
    
     /**
     * Metoda isEnabled je povolení, neboli vrací true.
     */
    public boolean isEnabled() {
        return true;
    }
    
    public void move() {
      //  Game.nextPlayerTurn();
    }
    
    public void setImagine(int cislo) {
        try{
        this.setImage("images/sipka" + cislo + ".png");
        }catch(IllegalArgumentException e){
            System.out.println("Nastala vyjimka (Chybně zadaný label):" + e.toString());
            e.printStackTrace();
        }
    } 
    
    /**
     * @return NextPlayerAction vrací sipku.
     */
    public NextPlayerAction getNextPlayerAction() {
        return this;
    } 
}
