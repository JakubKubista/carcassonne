import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Ve třídě Deck je pole čísel, přičemž každé číslo odpovídá právě jedné kartě,
 * zároveň nám umožňuje rozpoznat, která karta je trvale umístěná a která ne. Její obrázek značí
 * odkud bereme karty.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Deck extends Actor
{
    private Card card;
    private boolean[] used = new boolean[72];
    private int number;
    private boolean added;
    
    /**
     * Konstruktor nastaví číslo první karty na -1, jelikož začíná aplikace zvýšením čísla a tedy
     * poté začíná od 0.
     */
    public Deck() 
    {
        this.number = -1;
        this.added = false; 
    }
    
    /**
     * Metoda umožňuje nastavit kartu, funguje jako klasický set
     */
    
    public void putCard(Card card)
    {
        this.card = card; 
    }
    
    /**
     * Metoda nám zvýši kartu o 1 a také nám nastaví, že byla přidána do decku.
     */
    public void incNumber() {
        this.number += 1;
        this.added = true;
    }  
    
    /**
     * @return deck vrací rub karet.
     */
    public Deck getDeck() {
        return this;
    } 
    
    public boolean getAdded() {
        return this.added;
    }  
    
    public void setAdded(boolean added){
        this.added = added;
    }
    
    public int getNumber() {
        return this.number;
    } 
    
    public void setNumber(int i) {
        this.number = i;
    }
    
    public void setUsed(int i){
         this.used[i]=true;
    } 
    
    public boolean getUsed(int i){
         return this.used[i];
    } 
}
