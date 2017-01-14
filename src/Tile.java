import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Třída Tile odpovídá vždy jen jednomu z políček a charakterizuje jejo stav a polohu.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Tile extends Actor
{   
    private final int x;
    private final int y;
    private boolean absolute;
    private boolean check;
    private Card card;
    
    /**
     * Metoda act je neustále běžící metoda, která opakovaně zpracovává příkazy.
     */  
    
    
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Metoda getX vrací návratovou hodnotu.
     * @return Hodnota x.
     */ 
    public int getX()
    {
        return this.x;
    }
    
    /**
     * Metoda getY vrací návratovou hodnotu.
     * @return Hodnota y.
     */ 
    public int getY()
    {
        return this.y;
    }
    
    /**
     * Metoda isFree vrací návratovou hodnotu..
     * @return Hodnota vyprázdní kartu.
     */ 
    public boolean isFree() {
        return card == null;
    }   
    
     /**
     * Tato metoda je zavolána z pohybu karty. Zvolíme kartu, kterou chceme umístit a zavolá v dané
     * kartě metodu pro dokončení celé akce.
     * @param card udává, kterou kartu chceme na políčko umístit.
     */    
    public void putCard(Card card) {
        // od ted pracuji s novou kartou
        this.card = card;
        if (card.getTile() != null)
        card.getTile().removeCard();
        // umistim jej do nove pozici
        card.setTile(this);
    }
    
    /**
     * @param absolute udává jestli je natrvalo karta umístěna nebo ne.
     */   
    public void setAbsolute(boolean absolute){
        this.absolute = absolute;
    } 
    
    /**
     * Metoda getAbsolute vrací návratovou hodnotu.
     * @return Hodnota absolute.
     */ 
    public boolean getAbsolute(){
        return this.absolute;
    } 
   
    /**
     * Metoda checkTile, zkontroluje, zda má toto políčko kartu nebo ne.
     * @return Hodnota check (true nebo false).
     */
    public boolean checkTile(Card card){
        if (card.getTile() == null){
            this.check = true;
        }else{
            this.check = false;
        }
        return this.check;
    }
    
    /**
     * Metoda removeCard vyprázdní danou kartu a čeká až ji Garbage Collector smaže.
     */
    public void removeCard() {
        card.setTile(null);
        card = null;
    }
    
    /**
     * Metoda getCard vrací návratovou hodnotu card.
     * @return Hodnota card.
     */
    public Card getCard() {
        return card;
    }
    
    /**
     * Ověření, zda jsou objekty na stejném místě. Metoda je doprovázena překrytím Override.
     * @return Hodnota true nebo false, kde se objekty rovnají nebo ne.
     */
    @Override 
    public boolean equals(Object obj) {
        if ((obj instanceof Tile)) {
            Tile a = (Tile) obj;
            return ((this.x == a.x)&&(this.y == a.y));
        }
        return false;
    }
    
    /**
     * Metoda hashCode doplňuje equals při jejím ověření řádným výpočetem parametru.
     */
    public int hashCode() {
        int param = 3;
        if(this!=null){
        param = param * x * y;
        }
        return param;
    }
    
    /**
     * Ověření, zda jsou objekty umístěny na hrací desce.
     * @return Hodnota true nebo false.
     */
    public boolean equals(){
        if((this.x<0)||(this.y<0)){
            return false;
        }else if ((this.x>1350)||(this.y>599)){
            return false;
        }        
        return true;
    }
}
