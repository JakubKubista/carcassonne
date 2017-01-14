import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


/**
 * Třída Location odpovídá vždy jen jedné z lokací a charakterizuje její stav a polohu.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */

public class Location extends Actor
{
    private final int type;
    private final int number;
    private final int x;
    private final int y;
    private boolean absolute;
    private Pawn pawn;   
    
    public Location(int type, int x, int y, int number){
        this.x = x;
        this.y = y;
        this.type = type;
        this.number = number;
        this.setImage(new GreenfootImage("", 0, null, null));
    }
    
    /**
     * Metoda getType vrací návratovou hodnotu.
     * @return Hodnota type.
     */ 
    public int getType()
    {
        return this.type;
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
     * Metoda getNumber vrací návratovou hodnotu.
     * @return Hodnota number.
     */ 
    public int getNumber()
    {
        return this.number;
    }
    
    /**
     * Metoda isFree vrací návratovou hodnotu..
     * @return Hodnota vyprázdní figurku.
     */ 
    public boolean isFree() {
        return pawn == null;
    }   
    
     /**
     * Tato metoda je zavolána z pohybu karty. Zvolíme kartu, kterou chceme umístit a zavolá v dané
     * kartě metodu pro dokončení celé akce.
     * @param card udává, kterou kartu chceme na políčko umístit.
     */    
    public void putPawn(Pawn pawn) {
        // od ted pracuji s novym pawn
        this.pawn = pawn;
        if (pawn.getLocation() != null)
        pawn.getLocation().removePawn();
        // umistim jej do nove pozici
        pawn.setLocation(this);
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
     * Metoda removeCard vyprázdní danou kartu a čeká až ji Garbage Collector smaže.
     */
    public void removePawn() {
        pawn.setLocation(null);
        pawn = null;
    }
    
    /**
     * Metoda getCard vrací návratovou hodnotu card.
     * @return Hodnota card.
     */
    public Pawn getPawn() {
        return pawn;
    }
    
    /**
     * Ověření, zda jsou objekty na stejném místě. Metoda je doprovázena překrytím Override.
     * @return Hodnota true nebo false, kde se objekty rovnají nebo ne.
     */
    @Override 
    public boolean equals(Object obj) {
        if ((obj instanceof Location)) {
            Location a = (Location) obj;
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
   
}
