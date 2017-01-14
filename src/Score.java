import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Object.*;
/**
 * Třída Score udává veškeré získané body ve hře za jednotlivá města, louky, cesty a kláštery. Ve
 * finále tato metoda vše za pomoci hashCode ověří správnost celkového výpočtu aktuálních i
 * finálních bodů hráčů.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Score
{
    public int totalScore[] = new int[5];
    public int fieldScore;
    public int[] townScore = new int[91];
    public int[] pathScore = new int[91];
    public int[] monasteryScore = new int[91];
    private int finishedTownScore;
    private int finishedMonasteryScore;
    private int finishedPathScore;
    
    private int current;
    
    private Deck deck;
    private Player player;
    /**
     * Konstruktor nám při vytvoření vyžádá pro jakého hráče a deck bude skóre přiřazeno.
     */
    public Score(int current) {
        this.current = current;
    }
    
    public void setCurrentPlayer(int currentPlayer){
    this.current = currentPlayer;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void incTownScore(int i){
        townScore[i]+=townScore[i]+2;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void incMonasteryScore(int i){
        monasteryScore[i]+=monasteryScore[i]+1;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void incPathScore(int i){
        pathScore[i]+=pathScore[i]+1;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void setScoreActualTown(int i,int j){
        townScore[i]+=townScore[i]+j;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void setScoreActualPath(int i){
        pathScore[i]+=1;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void addScoreActualPath(int i){
        totalScore[current]+=pathScore[i];
        pathScore[i]=0;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void setScoreActualMonastery(int i){
        monasteryScore[i]+=1;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void addScoreActualMonastery(int i){
        totalScore[current]+=monasteryScore[i];
        monasteryScore[i] = 0;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void setScoreActualTown(int i){
        townScore[i]+=2;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void addScoreActualTown(int i){
        totalScore[current]+=townScore[i];
        townScore[i] = 0;
    }
    
    /**
     * Metoda zvývší score atributu podle pravidel.
     */
    public void setScoreActualPath(int i,int j){
        pathScore[i]+=pathScore[i]+j;
    }
    
    /**
     * @return totalScore celkové aktuální body ve hře.
     */
    public int getTotalScore(){
        return this.totalScore[current];
    }
    
    /**
     * @return totalScore celkové aktuální body ve hře.
     */
    public int getTotalScore(int i){
        return this.totalScore[i];
    }
    
    /**
     * Nastaví aktuální body. 
     */
    public void setTotalScore(int i){
        this.totalScore[current] = i;
    }
    
    /**
     * Nastaví aktuální body dokončeného města na body města z pole a zároveň město z pole vymaže. 
     */
    public void setFinishedTownScore(int i){
        this.finishedTownScore = townScore[i];
        townScore[i]=0;
    }
    
    public void setFinishedMonasteryScore(int i){
        this.finishedMonasteryScore = monasteryScore[i];
        monasteryScore[i]=0;
    }

    public void setFinishedPathScore(int i){
        this.finishedPathScore = pathScore[i];
        pathScore[i]=0;
    }
    
    /**
     * Metoda nám zjistí, zda aktuální výpočet skóre v dané lokalitě není roven nule, pokud nemáme
     * žadný aktuální vysledek skóre, metoda nemá důvod volat hashCode a propočítávat celkové skóre.
     */
    public boolean equals(){
        if((this.finishedTownScore == 0)&&(this.finishedMonasteryScore == 0)&&(this.finishedPathScore == 0)){
            return true;
        }
        return false;
    }
 
    @Override  
    /**
     * Metoda hashCode doplňuje equals při jejím ověření řádným výpočetem parametru.
     */
    public int hashCode() {
        int param = 3;
        if(this!=null){
        param = param * finishedTownScore * finishedMonasteryScore * finishedPathScore;
        }
        return param;
    }
   
    /**
     * Tato metoda nám spočítá buď aktuální skóre nebo finální skóre, kdyź jsme dosáhli poslední karty.
     */
    /*
    public int calculate() {
        if (this.deck.getNumber()==71) {
            totalScore += fieldScore;
           // hashCode = totalScore;
        }else
        {
            totalScore += finishedTownScore;
            totalScore += finishedMonasteryScore;
            totalScore += finishedPathScore;
            finishedTownScore = 0;            
            finishedMonasteryScore = 0;            
            finishedPathScore = 0;
            //hashCode = totalScore;  
       //}
        return totalScore;
    } 
    */
}
