import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

/**
 * Třída Label nám zpřehledňuje aplikaci několika druhy textů, nápověd a obrázků.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Label extends Actor {
    
    private String text;
    private int size;
    private Color color;
    private Color background;
    private int number;
    private int align = 0;
    private int origX = 0;
    private String imagine;
    /**
     * Konstruktor Label nám nastaví text, velikost, barvu písma a pozadí.
     */
    public Label(String text, int size, Color color, Color background) {
        this.size = size;
        this.color = color;
        this.background = background;
        this.setText(text);
    }
    
    /**
     * Tento (druhý) konstruktor udává místo textu obrázky příslušným labelům.
     * @param imagine hodnota textu, o který obrázek se jedná.
     */
    public Label(String imagine) {
        if(imagine=="CLICKHERE"){setImage("images/CLICKHERE.png");}
        if(imagine=="CLICKHERE2"){setImage("images/CLICKHERE2.png");}
        if(imagine=="PRESSSPACE"){setImage("images/PRESSSPACE.png");}
        if(imagine=="PRESSENTER"){setImage("images/PRESSENTER.png");}
        if(imagine=="PRESSSHIFT"){setImage("images/PRESSSHIFT.png");}
        if(imagine=="PRESSWAY"){setImage("images/PRESSWAY.png");}
        if(imagine=="CLEARLABEL"){setImage("images/CLEARLABEL.png");} 
        if(imagine=="WRONG"){setImage("images/WRONG.png");} 
        if(imagine=="PLACED"){setImage("images/PLACED.png");} 
        if(imagine=="NOPAWNS"){setImage("images/NOPAWNS.png");} 
        if(imagine=="ACTUAL"){setImage("images/actual2.png");} 
    }
    
        public Label() {
            setImage("images/CLEARLABEL.png");
    }
        
    /**
     * Tato metoda umožňuje vytvářet nový obraz pro GreenfootImage za pomocí textu a dalších
     * atributů. Je ošetřena metodou try catch, kdyby se náhodou zadal špatný label, což nám
     * kompilátor v klidu přeloží bez chyby. Nakonec nastaví lokaci labelu a textu, který za 
     * labelem pokračuje.
     */
    public void setText(String text) {
        try{
        this.text = text;
        this.setImage(new GreenfootImage(text, size, color, background));
        }catch(IllegalArgumentException e){
            System.out.println("Nastala vyjimka (Chybně zadaný label):" + e.toString());
            e.printStackTrace();
        }
        if(getWorld() != null) {
            int w = getImage().getWidth();
            setLocation(origX + ((w / 2) * align), getY());
        }
    }
    
    public void setImagine(String imagine) {
        try{
        this.setImage("images/" + imagine + ".png");
        }catch(IllegalArgumentException e){
            System.out.println("Nastala vyjimka (Chybně zadaný label):" + e.toString());
            e.printStackTrace();
        }
    }  
    
    public String getImagine(){
        return this.imagine;
    }
    
    public void setAlign(int a) {
        align = a;
    }
    
    public void setOrigX(int x) {
        origX = x;
    }
    
    public Label getLabel(){
        return this;
    }
    
}