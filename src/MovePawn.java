import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
import static java.lang.String.format;
import java.io.*;
/**
 * Třída MoveFigure je potomkem třídy Action a najdeme zde veškerou implementaci umožňující pohyb
 * figurky.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class MovePawn extends Action
{
    private static final String FORMAT_PHASE_LABEL = "%s";
    
    private int i = 0;   
    private int pawnNumber;
    private int playerNumber;
    private int actualPlayer;
    
    private boolean wait;
    private boolean a;
    private boolean c;
    private boolean placement = false;
    private boolean newAdded = false;
    
    private Pocket pocket;
    private Phase phase1;
    private Label helpLabel;
    private Label helpBorderLabel2 = new Label();
    private Label phaseLabel;
    private Game game;
    private Score score;
    
    private int[] churches = new int[10];
    private String[] playerNames = new String[6];
    private Pawn[][] pawn = new Pawn[5][7];
    private PawnAdvance[][] pawnAdvance = new PawnAdvance[5][7];
    private GreenfootSound sound1;
    
     /**
     * Metoda isEnabled je povolení, neboli vrací true.
     */
        public boolean isEnabled() {
        return true;
    }
    /**
     * Předání pěšáku od hráče pro mechanismus pohybu pěšáků - šetření kódu.
     */
    private void setPawns(Game game){
        pawn = game.getPlayer().getPawns();
        playerNumber = game.getCurrent();
    }
    /**
     * Volání návratové hodnoty, což je uvedený pěšák.
     */
    public Pawn getPawn(int i){
        return pawn[playerNumber][i];
    }
    /**
     * Předání parametrů do pomocné třídy pawnAdvance, jelikož Pawny mažeme v průběhu fázi, musíme někde skladovat jejich atributy.
     */
    public void setPawn(Pawn pawn, int j, int i){
        pawnAdvance[j][i] = new PawnAdvance();
        this.pawnAdvance[j][i].setI(pawn.getI());
        this.pawnAdvance[j][i].setPlayerNumber(pawn.getPlayerNumber());
        this.pawnAdvance[j][i].setTileNumber(pawn.getTileNumber());
        this.pawnAdvance[j][i].setLocation(pawn.getLocation());
    }
        
    public String[] getNames(){
        return playerNames;
    }
    
    public PawnAdvance getPawnAdvance(int j, int i){
        return pawnAdvance[j][i];
    }
    
    public void setWait(boolean wait){
        this.wait = wait;
    }
    /**
     * Rozsáhlá metoda loadPawn slouží k načítání, nachází se záměrně v MovePawn, jelikož je odtud snadné předat všechny atributy. Je řešena za pomoci
     * třídy BufferedReader, která slouží ke čtení textových souborů. Za pomocí metody split si rozdělíme uvedeným oddělovačem String celého souboru
     * na jednotlivé stringy. Ty už pak předáváme dalším třídám a atributům a umísťujeme tím postupně figurky, tak jak jsou uvedeny v souboru.
     * 
     */
    public void loadPawn(MoveLocation movelocation, MoveCard movecard,String fileName) throws FileNameException{
        String words = null;
        BufferedReader input = null;
            playerNumber = 0;
            actualPlayer = 0;
          try{
            input = new BufferedReader(new FileReader(fileName)); 
            words = input.readLine();
            
            while(words != null){
                
                String[] wordSplits = words.split("\\;");
                int playerNumber = Integer.valueOf(wordSplits[0]);
                int actualPlayer = Integer.valueOf(wordSplits[1]);
                int pawnNumber = Integer.valueOf(wordSplits[2]);
                int tileNumber = Integer.valueOf(wordSplits[3]);  
                int locationNumber = Integer.valueOf(wordSplits[4]);
                int locationType = Integer.valueOf(wordSplits[5]);
                int playerScore = Integer.valueOf(wordSplits[6]);
                int k = 7;
                 for(int h = 1;h<=(playerNumber+1) ; h++){
                     this.playerNames[h] = (wordSplits[k]);
                     k++;
                 }
                if(c==false){
                    game.createPlayers(playerNumber, pocket);
                    c=true;
                }
                game.setCurrent(actualPlayer);
                pocket.setCurrentPlayer(game.getCurrent(),score);    
                game.setPlayer(actualPlayer);
   
                pocket.setNumber(pawnNumber);
                game.getPlayer().addFiguresForPlayer(pocket);
                game.setPawnsAdded(actualPlayer);
                setPawns(game);
                pawn[actualPlayer][pocket.getNumber()].setTileNumber(tileNumber);
                setPawns(game);
                Location location = movelocation.getLocation(tileNumber,locationNumber);
                    location.putPawn(pawn[actualPlayer][pawnNumber]);
                    pawn[actualPlayer][pocket.getNumber()].setLocationNumber(locationNumber);
                         if(movelocation.getLocationAbsolute(tileNumber, locationNumber)==1){
                            pawn[actualPlayer][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                         }else{
                            pawn[actualPlayer][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                            }
                    setPawn(pawn[actualPlayer][pocket.getNumber()],actualPlayer,pocket.getNumber());
                    pocket.setAdded(false);
                    location.setAbsolute(true);
                    pocket.putPawn();    
                    newAdded = false;
                    score.setTotalScore(playerScore);
                game.setScore(score);     
                game.getPlayer().delFiguresOfPlayer(pocket);    
                game.delPlayers(pocket); 
                this.pawnNumber = pawnNumber;
                this.playerNumber = playerNumber;
                this.actualPlayer = actualPlayer;    
                words = input.readLine();
           }
           for(int i = 0; i<=playerNumber; i++){
                if(!(game.getPlayer(i).getPlayerPawnsAdded())){
                    pocket.setNumber(i,6);
                    game.getPlayer(i).addFiguresForPlayer(pocket);
                    game.setPawnsAdded(i);
                    game.getPlayer(i).delFiguresOfPlayer(pocket);    
                }    
            }
                game.delPlayers(pocket); 
            if(phase1.getPhaseNumber()<5){
                if(actualPlayer!=playerNumber){
                    this.actualPlayer += 1; 
                }else{
                    this.actualPlayer = 0; 
                }
            }
                game.setCurrent(actualPlayer);
                game.setPlayer(actualPlayer);
                pocket.setCurrentPlayer(game.getCurrent(),score);
              
          input.close();
       }catch(IOException e){
                throw new FileNameException("Nebyl nalezen soubor " + fileName);
      }
    }
    public void setSound(GreenfootSound sound1){
        this.sound1 = sound1;
    }
    public void setLabels(Pocket pocket,Phase phase1,Label helpLabel,Label phaseLabel,Game game,Score score){
        this.pocket = pocket;
        this.phase1 = phase1;
        this.score = score;
        this.phaseLabel = phaseLabel;
        this.game = game;
        this.helpLabel = helpLabel;
    }
    public Label getLabel(){
     return helpLabel;
    }
    /**
     * Metoda, navazující na MoveCard, která obsahuje veškerý mechanismus pohybu Pawnu. Zároveň nám předává informace o počtu zbývajících pěšáků,
     * nápovědy apod. Důležitý je především mechanismus putPawn, který nám daného pěšáka doopravdy umístí. Také v této metodě
     * vytvářáme jednotlivé pěšáky. Tedy taháme je z balíčku figurek. Nakonec figurku umístíme a můžeme předat kolo dalšímu hráči. 
     * Mechanizmus tedy funguje na skoro stejném principu jako MoveCard.
     * 
     */
    public void move(MoveLocation movelocation, int tileNumber, Card card, Tile[] baseTile) {
        setPawns(game);
        phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
        Location location = movelocation.getLocation(tileNumber,i);
        if(!(this.placement)){
        if(!wait){
            helpLabel.setImagine("CLICKHERE2"); 
            helpBorderLabel2.setImagine("CLICKDECK");
            helpBorderLabel2.setLocation(1287, 489);
        }
        if (Greenfoot.isKeyDown("shift")){
              phase1.setPhase(5);
              phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
              newAdded = false;
              this.placement = false;
        }
        if ((Greenfoot.mouseClicked(pocket.getPocket()))
        ||Greenfoot.mouseClicked(pawn[playerNumber][0])
        ||Greenfoot.mouseClicked(pawn[playerNumber][1])
        ||Greenfoot.mouseClicked(pawn[playerNumber][2])
        ||Greenfoot.mouseClicked(pawn[playerNumber][3])
        ||Greenfoot.mouseClicked(pawn[playerNumber][4])
        ||Greenfoot.mouseClicked(pawn[playerNumber][5])
        ||Greenfoot.mouseClicked(pawn[playerNumber][6])
        ||Greenfoot.mouseClicked(helpBorderLabel2)){
              helpBorderLabel2.setImage(new GreenfootImage("", 0, null, null));
              if(pocket.getNumber()>=0){ 
                Greenfoot.playSound("sounds/putCard.wav");
                this.i = 8;
                 phase1.setPhase(4);
                 phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
                 location = movelocation.getLocation(tileNumber,this.i);  
                 if(pawn[playerNumber][pocket.getNumber()]==null){
                     System.out.println(" NENI PAWN");
                 }
                 location.putPawn(pawn[playerNumber][pocket.getNumber()]);
                 pawn[playerNumber][pocket.getNumber()].setLocationNumber(this.i);
                 if(movelocation.getLocationAbsolute(tileNumber, this.i)==1){
                    pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                 }else{
                    pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                    }
                 this.placement = true;
                 helpLabel.setImagine("PRESSWAY");
              }else{
                  Greenfoot.playSound("sounds/putpawn.wav");
                  helpLabel.setImagine("NOPAWNS");
                  wait = true;
                  phase1.setPhase(5);
                  phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
                  newAdded = false;
                  this.placement = false;
              }
        }
        }else{
            if (Greenfoot.isKeyDown("left")){
                this.i -= 1;
                if(((this.i >=0)&&(movelocation.getLocation(tileNumber,this.i)!=null))){
                    location = movelocation.getLocation(tileNumber,this.i);  
                    location.putPawn(pawn[playerNumber][pocket.getNumber()]);
                    pawn[playerNumber][pocket.getNumber()].setLocationNumber(this.i);
                         if(movelocation.getLocationAbsolute(tileNumber, this.i)==1){
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                         }else{
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                            }
                    helpLabel.setImagine("PRESSSHIFT");
                }else{this.i += 1;}  
            }
            if (Greenfoot.isKeyDown("right")){
                this.i += 1;
                if(((this.i <=8)&&(movelocation.getLocation(tileNumber,this.i)!=null))){
                    location = movelocation.getLocation(tileNumber,this.i); 
                    location.putPawn(pawn[playerNumber][pocket.getNumber()]);; 
                    pawn[playerNumber][pocket.getNumber()].setLocationNumber(this.i);
                         if(movelocation.getLocationAbsolute(tileNumber, this.i)==1){
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                         }else{
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                            }
                    helpLabel.setImagine("PRESSSHIFT");
                }else{this.i -= 1;}
            }
            if (Greenfoot.isKeyDown("up")){
                this.i -= 3;
                if(((this.i >=0)&&(movelocation.getLocation(tileNumber,this.i)!=null))){
                    location = movelocation.getLocation(tileNumber,this.i); 
                    location.putPawn(pawn[playerNumber][pocket.getNumber()]);
                    pawn[playerNumber][pocket.getNumber()].setLocationNumber(this.i);
                         if(movelocation.getLocationAbsolute(tileNumber, this.i)==1){
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                         }else{
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                            }
                    helpLabel.setImagine("PRESSSHIFT");
                }else{this.i += 3;} 
            }
            if (Greenfoot.isKeyDown("down")){
                this.i += 3;
                if(((this.i <=8)&&(movelocation.getLocation(tileNumber,this.i)!=null))){
                    location = movelocation.getLocation(tileNumber,this.i); 
                    location.putPawn(pawn[playerNumber][pocket.getNumber()]);
                    pawn[playerNumber][pocket.getNumber()].setLocationNumber(this.i);
                         if(movelocation.getLocationAbsolute(tileNumber, this.i)==1){
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"lie");
                         }else{
                            pawn[playerNumber][pocket.getNumber()].setImagine(game.getPlayer().getPlayerName(),"stand");
                            }
                    helpLabel.setImagine("PRESSSHIFT");
                }else{this.i -= 3;}  
            }
            if (Greenfoot.isKeyDown("shift")){
            if(((movelocation.getLocationAbsolute(tileNumber,this.i))!=5)
            ||(!((movelocation.getLocationAbsolute(tileNumber,this.i))==2)&&(this.i==5)&&((movelocation.getLocationAbsolute(tileNumber+1,3))==2))
            ||(!((movelocation.getLocationAbsolute(tileNumber,this.i))==2)&&(this.i==3)&&((movelocation.getLocationAbsolute(tileNumber-1,5))==2)))
            {
                pawn[playerNumber][pocket.getNumber()].setI(pocket.getNumber());
                pawn[playerNumber][pocket.getNumber()].setTileNumber(tileNumber);
                setPawn(pawn[playerNumber][pocket.getNumber()],playerNumber,pocket.getNumber());
                pocket.setAdded(false);
                location.setAbsolute(true);
                helpLabel.setImagine("PLACED");
                this.placement = false; 
                pocket.putPawn();
                newAdded = false;
                Greenfoot.playSound("sounds/nextPlayer.wav");
                if((movelocation.getLocationAbsolute(tileNumber,this.i))==4){
                    int l = 0;
                    do{
                        if(churches[l]==0){
                            churches[l] = tileNumber;
                        }else{
                            l++;
                        }
                    }while(churches[l]==0);
                    if(baseTile[tileNumber+1].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber-1].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber+13].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber-13].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber+12].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber+14].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber-14].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                    if(baseTile[tileNumber-12].getAbsolute()){
                        score.setScoreActualMonastery(tileNumber);
                    }
                }
                if((movelocation.getLocationAbsolute(tileNumber,this.i))==2){
                    if((movelocation.getLocationAbsolute(tileNumber,1))==(movelocation.getLocationAbsolute(tileNumber-13,7))){
                        score.setScoreActualPath(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,7))==(movelocation.getLocationAbsolute(tileNumber+13,1))){
                        score.setScoreActualPath(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,3))==(movelocation.getLocationAbsolute(tileNumber-1,5))){
                        score.setScoreActualPath(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,5))==(movelocation.getLocationAbsolute(tileNumber+1,3))){
                        score.setScoreActualPath(tileNumber);
                    }
                }
                if((movelocation.getLocationAbsolute(tileNumber,this.i))==3){
                    if((movelocation.getLocationAbsolute(tileNumber,1))==(movelocation.getLocationAbsolute(tileNumber-13,7))){
                        score.setScoreActualTown(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,7))==(movelocation.getLocationAbsolute(tileNumber+13,1))){
                        score.setScoreActualTown(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,3))==(movelocation.getLocationAbsolute(tileNumber-1,5))){
                        score.setScoreActualTown(tileNumber);
                    }
                    if((movelocation.getLocationAbsolute(tileNumber,5))==(movelocation.getLocationAbsolute(tileNumber+1,3))){
                        score.setScoreActualTown(tileNumber);
                    }
                }
                score.addScoreActualMonastery(tileNumber);
                score.addScoreActualPath(tileNumber);
                score.addScoreActualTown(tileNumber);
                game.setScore(score);
                phase1.setPhase(5);
                phaseLabel.setText(format(FORMAT_PHASE_LABEL, phase1.getPhaseName()));
            }else{
                helpLabel.setImagine("WRONG");
                Greenfoot.playSound("sounds/putpawn.wav");
                }
            }
          }
        
    }
    
    /**
     * Metoda act je neustále běžící metoda, která opakovaně zpracovává příkazy.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
