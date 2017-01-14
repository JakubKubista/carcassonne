import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
import static java.lang.String.format;
import java.io.*;

/**
 * Třída MoveFigure je potomkem třídy Action a najdeme zde veškerou implementaci umožňující pohyb
 * s kartami.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class MoveCard extends Action
{
    private static final String FORMAT_GAMEOVER_LABEL = "GAME OVER";
    private static final String FORMAT_CARDLEFT_LABEL = "Left: %s";
    private static final String FORMAT_PHASE_LABEL = "%s";
    private Card[] card = new Card[72];
    private Card actualCard;
    private Pocket pocket;
    private Phase phase;
    private Label actualLabel;
    private Label cardLeftLabel;
    private Label helpLabel;
//    private Label helpBorderLabel = new Label(); 
    private Label phaseLabel;
    private Label gameOverLabel;
    private int i = 0;
    private boolean phaseAdd;
    private boolean figureRound;
    private boolean a;
    private boolean cardIsOn;
    private boolean placement = false;
    private GreenfootSound sound1;
    
    public MoveCard(Phase phase){
        this.phase = phase;
       // addObject(helpBorderLabel, 1287, 265);      
    }
     /**
     * Metoda isEnabled je povolení, neboli vrací true.
     */    
    public boolean isEnabled() {
        return true;
    }
    /**
     * Tato metoda nastaví aktuální kartu podle decku.
     */
    public void setActualCard(Card[] card, Deck deck){
        this.actualCard = card[deck.getNumber()];
    }
    /**
     * Zavolání aktuální karty.
     */
    public Card getActualCard(){
        return actualCard;
    }
    /**
     * Zavolání karty podle ID.
     */
    public Card getCard(int i){
        return card[i];
    }
    /**
     * Zavolání aktuálního Tilu
     */
    public int getActualTileNumber(){
        return i;
    }
    
    public void setActualTileNumber(int i){
        this.i = i;
    }
   
    public void setPhaseAdd(boolean phaseAdd){
        this.phaseAdd = phaseAdd;
    }
    
    public boolean getPhaseAdd(){
        return phaseAdd;
    }
    
    public boolean getFigureRound(){
        return figureRound;
    }
    /**
     * Nastavení aktuální fáze pro přehlednost.
     */
    public void setFigureRound(Tile tile){
        figureRound = tile.getAbsolute();
    }
    /**
     * Nastavení aktuální fáze pro přehlednost.
     */
    public void setFigureRound(){
        figureRound = true;
    }
    
    /**
     * Rozsáhlá metoda loadCard slouží k načítání, nachází se záměrně v MoveCard, jelikož je odtud snadné předat všechny atributy. Je řešena za pomoci
     * třídy BufferedReader, která slouží ke čtení textových souborů. Za pomocí metody split si rozdělíme uvedeným oddělovačem String celého souboru
     * na jednotlivé stringy. Ty už pak předáváme dalším třídám a atributům a umísťujeme tím postupně karty, tak jak jsou uvedeny v souboru.
     * 
     */
    public void loadCard(Deck deck,Tile[] baseTile,MoveLocation movelocation,String fileName) throws FileNameException{
        String words = null;
        BufferedReader input = null;
        try{
            input = new BufferedReader(new FileReader(fileName));
            words = input.readLine();
            while(words != null){
                String[] wordSplits = words.split("\\;");
                
                int number = Integer.valueOf(wordSplits[0]);
                int image = Integer.valueOf(wordSplits[1]);
                int i = Integer.valueOf(wordSplits[2]);
                int a = Integer.valueOf(wordSplits[3]);  
                int x = Integer.valueOf(wordSplits[4]);
                int y = Integer.valueOf(wordSplits[5]);
                int phaseNumber = Integer.valueOf(wordSplits[6]);

                phase.setPhase(phaseNumber);
                
                deck.setNumber(number);
                Tile tile = baseTile[i];
                card[number] = new Card(number,x,y,image,movelocation);
                getWorld().addObject(card[number],x, y);
                
                card[number].setTileNumber(i);
                card[number].setRealNumber(image);
                
                movelocation.setLocation(i,card[number].getRealNumber());
                card[number].radius(a);
                movelocation.setLocationAbsolute(i,card[number].getRealNumber(),tile);
                tile.putCard(card[number]);

                deck.setAdded(false);
                tile.setAbsolute(true);
                deck.setUsed(card[number].getRealNumber());
                setActualCard(card,deck);
                this.i = i;
                               
                words = input.readLine();
                
            }
            cardLeftLabel.setText(format(FORMAT_CARDLEFT_LABEL, (71-deck.getNumber())));
            phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase.getPhaseName()));
            if(phase.getPhaseNumber()<3){
                helpLabel.setImagine("CLICKHERE");
                setPhaseAdd(false);
            }
            if((phase.getPhaseNumber()>=3)&&(phase.getPhaseNumber()<5)){
                helpLabel.setImagine("CLICKHERE2"); 
                setFigureRound();
                setPhaseAdd(true);
            }
            input.close();
            }catch(IOException e){
                throw new FileNameException("Nebyl nalezen soubor " + fileName);
            }
    } 
    public void setSound(GreenfootSound sound1){
        this.sound1 = sound1;
    }
    public void setLabels(Label actualLabel,Label cardLeftLabel,Label helpLabel,Label phaseLabel){
        this.actualLabel = actualLabel;
        this.cardLeftLabel = cardLeftLabel;
        this.helpLabel = helpLabel;
        this.phaseLabel = phaseLabel;
    }
    public Label getLabel(){
     return helpLabel;
    }
    /**
     * Klíčová metoda která obsahuje veškerý mechanismus pohybu karty. Zároveň nám předává informace o počtu zbývajících karet,
     * nápovědy apod. Důležitý je především mechanismus putCard, který nám danou kartu doopravdy umístí. Také v této metodě
     * vytvářáme jednotlivé karty. Tedy taháme je z balíčku karet. Nakonec karty umístíme, tím že se odkážeme na MoveLocation.
     * 
     */
    public void move(Deck deck,Pocket pocket,Tile[] baseTile, MoveLocation movelocation, MovePawn movepawn, Phase phase1){  
        cardLeftLabel.setText(format(FORMAT_CARDLEFT_LABEL, (71-deck.getNumber())));
        phase1.setPhase(1);
        phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
        Tile tile = baseTile[i];
        a=false;  
        if(deck.getNumber()==-1){
        helpLabel.setImagine("CLICKHERE");
        }
        if(!cardIsOn){
        if((Greenfoot.mouseClicked(deck.getDeck()))||(Greenfoot.mouseClicked(cardLeftLabel))) {
            Greenfoot.playSound("sounds/menuClick.wav");
            if(this.placement){
            a=true;    
            }
            if(!a){
            if(deck.getAdded()==false){
                deck.incNumber();
                helpLabel.setImagine("CLICKHERE");    
                }
                if(deck.getNumber()<72){
                        card[deck.getNumber()] = new Card(deck.getNumber(),deck,movelocation);
                        getWorld().addObject(card[deck.getNumber()],  1285, 279);
                        cardIsOn = true;
                }else{
                 gameOverLabel = new Label(format(FORMAT_GAMEOVER_LABEL, ""), 150, Color.RED, null);
                 getWorld().addObject(gameOverLabel, 550, 320);
                 Greenfoot.playSound("sounds/WinScenario.wav");
                }
            }
        }
       }else{
        if ((Greenfoot.mouseClicked(card[deck.getNumber()]))&&(this.placement == false)){
            this.i = 45;
             if(deck.getNumber()==0){
                Greenfoot.playSound("sounds/nextPlayer.wav"); 
                tile = baseTile[this.i];
                movelocation.setLocation(i,card[deck.getNumber()].getRealNumber());
                movelocation.setLocationAbsolute(i,card[deck.getNumber()].getRealNumber(),tile);
                tile.putCard(card[deck.getNumber()]);
                card[deck.getNumber()].setTileNumber(this.i);
                deck.setAdded(false);
                tile.setAbsolute(true);
                setFigureRound(tile);
                this.placement = false;                
                helpLabel.setImagine("PLACED");
                setActualCard(card,deck);
                cardIsOn = false;
                phase1.setPhase(3);
                phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
            }else{
             Greenfoot.playSound("sounds/putCard.wav");
             phase1.setPhase(2);
             phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
             Random ran = new Random();
             int x = ran.nextInt(8) + 0; 
             if(x==0){this.i = 44;}
             if(x==1){this.i = 46;}
             if(x==2){this.i = 58;}
             if(x==4){this.i = 57;}
             if(x==5){this.i = 59;}
             if(x==6){this.i = 32;}
             if(x==7){this.i = 31;}
             if(x==8){this.i = 33;}
             
             tile = baseTile[this.i];
             movelocation.setLocation(i,card[deck.getNumber()].getRealNumber());
             tile.putCard(card[deck.getNumber()]);
             card[deck.getNumber()].setTileNumber(this.i);
             this.placement = true;
             helpLabel.setImagine("PRESSWAY");
             actualLabel.setLocation(tile.getX(),  tile.getY());
            }
            }
        if(this.placement == true){
            if (Greenfoot.isKeyDown("left")){
                this.i -= 1;
                if(((this.i >=0)&&(baseTile[this.i]!=null))){
                    tile = baseTile[this.i];
                    movelocation.moveLeftLocation(i,card[deck.getNumber()].getRealNumber());
                    tile.putCard(card[deck.getNumber()]);  
                    card[deck.getNumber()].setTileNumber(this.i);
                    helpLabel.setImagine("PRESSENTER");
                    actualLabel.setLocation(tile.getX(),  tile.getY());
                }else{this.i += 1;}  
            }
            if (Greenfoot.isKeyDown("right")){
                this.i += 1;
                if(((this.i <=90)&&(baseTile[this.i]!=null))){
                    tile = baseTile[this.i];
                    movelocation.moveRightLocation(i,card[deck.getNumber()].getRealNumber());
                    tile.putCard(card[deck.getNumber()]); 
                    card[deck.getNumber()].setTileNumber(this.i);
                    helpLabel.setImagine("PRESSENTER");
                    actualLabel.setLocation(tile.getX(),  tile.getY());
                }else{this.i -= 1;}
            }
            if (Greenfoot.isKeyDown("up")){
                this.i -= 13;
                if(((this.i >=0)&&(baseTile[this.i]!=null))){
                    tile = baseTile[this.i];
                    movelocation.moveUpLocation(i,card[deck.getNumber()].getRealNumber());
                    tile.putCard(card[deck.getNumber()]); 
                    card[deck.getNumber()].setTileNumber(this.i);
                    helpLabel.setImagine("PRESSENTER");
                    actualLabel.setLocation(tile.getX(),  tile.getY());
                }else{this.i += 13;} 
            }
            if (Greenfoot.isKeyDown("down")){
                this.i += 13;
                if(((this.i <=90)&&(baseTile[this.i]!=null))){
                    tile = baseTile[this.i];
                    movelocation.moveDownLocation(i,card[deck.getNumber()].getRealNumber());
                    tile.putCard(card[deck.getNumber()]); 
                    card[deck.getNumber()].setTileNumber(this.i);
                    helpLabel.setImagine("PRESSENTER");
                    actualLabel.setLocation(tile.getX(),  tile.getY());
                }else{this.i -= 13;}  
            }
            if (Greenfoot.isKeyDown("enter")){
              if(!tile.getAbsolute()){
               if(movelocation.tryLocation(this.i,card,deck)){ 
                Greenfoot.playSound("sounds/nextPlayer.wav");
                deck.setAdded(false);
                tile.setAbsolute(true);
                setFigureRound(tile);
                this.placement = false;    
                actualLabel.setLocation(1285, 279);
                movelocation.setLocationAbsolute(i,card[deck.getNumber()].getRealNumber(),tile);
                helpLabel.setImagine("CLICKHERE2");
                setActualCard(card,deck);
                cardIsOn = false;
                phase1.setPhase(3);
                phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
                }else{
                //JOptionPane.showMessageDialog(new JInternalFrame(),"This tile is full.","Error message",JOptionPane.INFORMATION_MESSAGE);
                helpLabel.setImagine("WRONG");
                Greenfoot.playSound("sounds/putpawn.wav");
                }
              }else{
               helpLabel.setImagine("FULL"); 
               Greenfoot.playSound("sounds/putpawn.wav");
              }
            }
        }     
      }
   }
}
