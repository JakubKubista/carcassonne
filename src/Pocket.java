import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ve třídě Pocket neboli kapse je pole čísel, přičemž každé číslo odpovídá právě jedné figurce,
 * zároveň nám umožňuje rozpoznat, která figurka je trvale umístěná a která ne. Její obrázek značí
 * odkud bereme figurky.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Pocket extends Actor
{
    private int[] number = new int[5];
    private Pawn pawn;
    private boolean added;
    private int currentPlayer;
    
    /**
     * Konstruktor nastaví číslo první figurky na -1, jelikož začíná aplikace zvýšením čísla a tedy
     * poté začíná od 0.
     */
    public Pocket(int numberOfPlayers) 
    {
        for(int i = 0; i<=numberOfPlayers ; i++){
        this.number[i] = 6;
        }
        this.added = false; 
    }
    
    public void setCurrentPlayer(int currentPlayer, Score score){
        this.currentPlayer = currentPlayer;
        score.setCurrentPlayer(currentPlayer);
    }
    
    /**
     * Metoda umožňuje nastavit kartu, funguje jako klasický set
     */
    public void putPawn(Pawn pawn)
    {
        this.pawn = pawn; 
    } 
    
    /**
     * Metoda nám zvýši pěšáka o 1 a také nám nastaví, že byla vrácena do kapsy.
     */
    public void takePawn() {
        this.number[currentPlayer] += 1;
        this.added = true;
    }  
    
    /**
     * Metoda nám sníží pěšáka o 1 a také nám nastaví, že byla odebrána z kapsy.
     */
    public void putPawn() {
        this.number[currentPlayer] -= 1;
        this.added = false;
    }  

    /**
     * @return Pocket vrací kapsu pěšáku.
     */
    public Pocket getPocket() {
        return this;
    } 
    
    public boolean getAdded() {
        return this.added;
    }  
    
    public void setAdded(boolean added){
        this.added = added;
    }
    
    public int getNumber() {
        return this.number[currentPlayer];
    } 
    
    public void setNumber(int i) {
        this.number[currentPlayer] = i;
    }
    
    public void setNumber(int i,int j) {
        this.number[i] = j;
    }
    
    
   public void addPlayer(Player player){
              getWorld().addObject(player, 1289, 161);
    }
    
   public void addFiguresForPlayer(Pawn[][] pawn, int i, int number){
       int x = 1255;
       int y = 488;
       for(int j = 0; j<=i ; j++){
               getWorld().addObject(pawn[number][j], x, y);
               x+=21;
			   if(j==3){
			   		y+=30; 
					x = 1265;
			 	}
        }
    }
    
   public void delFiguresOfPlayer(Pawn[][] pawn, int i, int number){
       for(int j = 0; j<=i ; j++){
           pawn[number][j].setImage(new GreenfootImage("", 0, null, null));
        }
    }
    
   public void delPlayers(Player[] player, int i){
       for(int j = 0; j<=i ; j++){
           player[j].setImage(new GreenfootImage("", 0, null, null));
       }
    }
    
   public void startingPlayer(Player[] player, int i){
           player[i].setPlayerImage(player[i].getPlayerName());
    }
}
