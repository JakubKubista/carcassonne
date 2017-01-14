import greenfoot.*;  
/**
 * Třída PawnAdvance slouží jako skladiště, je to historie atributů figurek. PawnAdvance
 * byla nezbytnou třídou pro funkčnost savů, loadů a přidělování score.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class PawnAdvance  
{
    private Player player;
    private Location location;
    private int i = 1;
    private int locationNumber;
    private int playerNumber;
    private int tileNumber;
    
    /**
     * Constructor for objects of class PawnAdvance
     */
    public PawnAdvance()
    {
    }

    /**
     * Nastavení aktuálního hráče.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    /**
     * Zavoání aktuálního hráče.
     */
    public Player getPlayer() {
        return player;
    }
    
    /**
     * Nastavení aktuálního hráče.
     */
   public void setPlayerNumber(int playerNumber){
       this.playerNumber = playerNumber;
    }
    
   public int getPlayerNumber(){
       return playerNumber;
    }
    
   public int getI(){
       return i;
   }
   
   public void setI(int i){
       this.i = i;
   }
   
   public int getTileNumber(){
       return tileNumber;
   }
   
   public void setTileNumber(int tileNumber){
       this.tileNumber = tileNumber;
   }
   
    public Location getLocation() {
        return location;
    }
    
    public void setLocation(Location location) {
        this.location = location;
    }
}