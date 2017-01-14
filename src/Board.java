import static java.lang.String.format;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JInternalFrame;
import java.util.Random.*;
import java.io.*;

/**
 * Třída Board je jádrem celé aplikace. Probíhá zde inicializace herní desky, polí a rozmístění
 * všech objektů. 
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class Board extends World
{
    private static final String FORMAT_PLAYER_LABEL = "Player: %s";
    private static final String FORMAT_SCORE_LABEL = "Score: %s";
    private static final String FORMAT_CARDLEFT_LABEL = "Left: %s";
    private static final String FORMAT_PHASE_LABEL = "%s";
    private Game game = new Game();
    private int numberOfPlayers;
    private String[] playerNames = new String[6];
    private Tile[] baseTile;
    private Phase phase1 = new ChooseCard();
    private Phase phase2 = new ChooseFigure();
    private Label playerLabel;
    private Label actualLabel;
    private Label scoreLabel;
    private Label cardLeftLabel;
    private Label phaseLabel;
    private Label helpLabel;

    private Deck deck;
    private Card card;
    private Score score;
    private Pocket pocket;
    private NextPlayerAction nextPlayerAction;
    
    private MoveCard movecard;
    private MoveLocation movelocation;
    private MovePawn movepawn;
    private CallMenu callMenu;
    
    private PauseMenu pauseMenu = new PauseMenu();
    private PlayerTurn playerTurn;
    
    private GreenfootSound sound1;
   
    private int status;
    
    private int sound;
    private int i = 0;
    private boolean add;
    private boolean a;
    private boolean playerTurns;
    private boolean placement = false;

    /**
     * Konstruktor board nastaví jednotlivé Tily na lokace po mapě a vytvoří objekty pod názvy
     * deck, labely, pockety, Score, moveAction, zároveň zavolá metodu prepare().
     */
    public Board(){
        // Create a new world with 1348x650 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        settings();
        load();
        playerTurn.setPlayerNames(playerNames);
    }
    
    public Board(int numberOfPlayers,String[] playerNames){
        // Create a new world with 1348x650 cells with a cell size of 1x1 pixels.        
        super(1348, 650, 1); 
        this.playerNames = playerNames;
        this.numberOfPlayers = numberOfPlayers;
        
        settings();
        
        pocket.setCurrentPlayer(game.getCurrent(), score);
        game.createPlayers(numberOfPlayers,pocket);
        game.delPlayers(pocket);
        game.startingPlayer(pocket);
    }
    
    public Board(int numberOfPlayers,String[] playerNames, int status){
        // Create a new world with 1348x650 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
        this.status = status;
        this.playerNames = playerNames;
        this.numberOfPlayers = numberOfPlayers;
        
        settings();
        
        pocket.setCurrentPlayer(game.getCurrent(), score);
        game.createPlayers(numberOfPlayers,pocket);
        game.delPlayers(pocket);
        game.startingPlayer(pocket);
    }
    
    public void setSound(GreenfootSound sound1){
        this.sound1 = sound1;
        this.sound1.stop();
        this.sound1 = new GreenfootSound("sounds/AITHEME1.MP3");
        this.sound1.play();
       
    }
    
    public void settings()
    { 
        baseTile = new Tile[91];
        
        baseTile[0] = new Tile(49,  49);        
        baseTile[1] = new Tile(141,  49);
        baseTile[2] = new Tile(233,  49);
        baseTile[3] = new Tile(325,  49);
        baseTile[4] = new Tile(417,  49);       
        baseTile[5] = new Tile(509,  49);
        baseTile[6] = new Tile(601,  49);
        baseTile[7] = new Tile(693,  49);
        baseTile[8] = new Tile(785,  49);
        baseTile[9] = new Tile(877,  49);
        baseTile[10] = new Tile(969,  49);
        baseTile[11] = new Tile(1061,  49);
        baseTile[12] = new Tile(1153,  49);
        
        baseTile[13] = new Tile(49,  141);
        baseTile[14] = new Tile(141,  141);
        baseTile[15] = new Tile(233,  141);
        baseTile[16] = new Tile(325,  141);
        baseTile[17] = new Tile(417,  141);       
        baseTile[18] = new Tile(509,  141);
        baseTile[19] = new Tile(601,  141);
        baseTile[20] = new Tile(693,  141);
        baseTile[21] = new Tile(785,  141);
        baseTile[22] = new Tile(877,  141);
        baseTile[23] = new Tile(969,  141);
        baseTile[24] = new Tile(1061,  141);
        baseTile[25] = new Tile(1153,  141);
          
        baseTile[26] = new Tile(49,  233);
        baseTile[27] = new Tile(141,  233);
        baseTile[28] = new Tile(233,  233);
        baseTile[29] = new Tile(325,  233);
        baseTile[30] = new Tile(417,  233);       
        baseTile[31] = new Tile(509,  233);
        baseTile[32] = new Tile(601,  233);
        baseTile[33] = new Tile(693,  233);
        baseTile[34] = new Tile(785,  233);
        baseTile[35] = new Tile(877,  233);
        baseTile[36] = new Tile(969,  233);
        baseTile[37] = new Tile(1061,  233);
        baseTile[38] = new Tile(1153,  233);
           
        baseTile[39] = new Tile(49,  325);
        baseTile[40] = new Tile(141,  325);
        baseTile[41] = new Tile(233,  325);
        baseTile[42] = new Tile(325,  325);
        baseTile[43] = new Tile(417,  325);       
        baseTile[44] = new Tile(509,  325);
        baseTile[45] = new Tile(601,  325);
        baseTile[46] = new Tile(693,  325);
        baseTile[47] = new Tile(785,  325);
        baseTile[48] = new Tile(877,  325);
        baseTile[49] = new Tile(969,  325);
        baseTile[50] = new Tile(1061,  325);
        baseTile[51] = new Tile(1153,  325);
    
        baseTile[52] = new Tile(49,  417);
        baseTile[53] = new Tile(141,  417);
        baseTile[54] = new Tile(233,  417);
        baseTile[55] = new Tile(325,  417);
        baseTile[56] = new Tile(417,  417);       
        baseTile[57] = new Tile(509,  417);
        baseTile[58] = new Tile(601,  417);
        baseTile[59] = new Tile(693,  417);
        baseTile[60] = new Tile(785,  417);
        baseTile[61] = new Tile(877,  417);
        baseTile[62] = new Tile(969,  417);
        baseTile[63] = new Tile(1061,  417);
        baseTile[64] = new Tile(1153,  417);
     
        baseTile[65] = new Tile(49,  509);
        baseTile[66] = new Tile(141,  509);
        baseTile[67] = new Tile(233,  509);
        baseTile[68] = new Tile(325,  509);
        baseTile[69] = new Tile(417,  509);       
        baseTile[70] = new Tile(509,  509);
        baseTile[71] = new Tile(601,  509);
        baseTile[72] = new Tile(693,  509);
        baseTile[73] = new Tile(785,  509);
        baseTile[74] = new Tile(877,  509);
        baseTile[75] = new Tile(969,  509);
        baseTile[76] = new Tile(1061,  509);
        baseTile[77] = new Tile(1153,  509);
     
        baseTile[78] = new Tile(49,  601);
        baseTile[79] = new Tile(141,  601);
        baseTile[80] = new Tile(233,  601);
        baseTile[81] = new Tile(325,  601);
        baseTile[82] = new Tile(417,  601);       
        baseTile[83] = new Tile(509,  601);
        baseTile[84] = new Tile(601,  601);
        baseTile[85] = new Tile(693,  601);
        baseTile[86] = new Tile(785,  601);
        baseTile[87] = new Tile(877,  601);
        baseTile[88] = new Tile(969,  601);
        baseTile[89] = new Tile(1061,  601);
        baseTile[90] = new Tile(1153,  601);
        
        actualLabel = new Label("actual2"); 
        addObject(actualLabel, 1285, 279);    
        
        helpLabel = new Label(); 
        addObject(helpLabel, 1285, 378);
                
        movelocation = new MoveLocation();
        addObject(movelocation,  1287, 265);
        
        movepawn = new MovePawn();
        addObject(movepawn,  1287, 265);
        
        movecard = new MoveCard(phase1);
        addObject(movecard,  1287, 265);
        
        score = new Score(game.getCurrent());
        
        pocket = new Pocket(numberOfPlayers);
        addObject(pocket, 1287, 489);
               
        scoreLabel = new Label(format(FORMAT_SCORE_LABEL, ""), 25, Color.black, null);
        addObject(scoreLabel, 1265, 110);
        scoreLabel.setOrigX(1245);
        scoreLabel.setAlign(1);
        
        callMenu = new CallMenu();
        addObject(callMenu, 1285, 60);
        
        deck = new Deck();
        addObject(deck, 1287, 265);
        
        nextPlayerAction = new NextPlayerAction();
        addObject(nextPlayerAction, 1290, 606);

        cardLeftLabel = new Label(format(FORMAT_CARDLEFT_LABEL, ""), 20, Color.black, null);
        addObject(cardLeftLabel, 1285, 279);
        cardLeftLabel.setOrigX(1260);
        cardLeftLabel.setAlign(1);
        
        phaseLabel = new Label(format(FORMAT_PHASE_LABEL, ""), 20, Color.yellow, null);
        addObject(phaseLabel, 1288, 606);
        phaseLabel.setOrigX(1277);
        phaseLabel.setAlign(1);
                
        /*        
        yellowScore = new Score(deck, Player.yellow);
        greenScore = new Score(deck, Player.green);
        redScore = new Score(deck, Player.red);
        blueScore = new Score(deck, Player.blue);
        blackScore = new Score(deck, Player.black);
        */
        prepare();
               
        playerTurn = new PlayerTurn(this,playerNames); 
        
        pauseMenu.setBoard(this);
    }
    
    public void load(){ 
        try{
            this.playerNames = movepawn.getNames();
            movecard.setLabels(actualLabel,cardLeftLabel,helpLabel,phaseLabel);
            if(movecard.getLabel() == helpLabel){
                 movecard.loadCard(deck,baseTile,movelocation,"saves/card.txt");
            }else{
                load();
            }
            add = movecard.getPhaseAdd();
            movepawn.setLabels(pocket,phase1, helpLabel, phaseLabel, game, score);
            if(movepawn.getLabel() == helpLabel){
            movepawn.loadPawn(movelocation,movecard,"saves/pawn.txt");
            }else{
                load();
            }
        }catch(FileNameException e){
            e.printStackTrace();
            System.out.println("Zkontroluj zadani nazvu souboru v boardu");
        }
    }
    
    /**
     * Metoda act je stále se opakující meta, která umožnuje neustálý běh programu.
     * Odkazuje se v ní na veškeré akce, jenž se týkají pohybu. Všechny pohyby jsou šetrně za pomoci
     * podmínek zajištěny, aby nedošlo k různým nechtěným chybám. Je doprovázena řadou nápovědných
     * labelů, aby se hráč dokázal lépe orientovat.
     */ 
    public void act(){        
        pocket.setCurrentPlayer(game.getCurrent(),score);
        scoreLabel.setText(format(FORMAT_SCORE_LABEL, score.getTotalScore()));
        if(phase1.getPhaseNumber()<3){
          if(playerTurns == false){
              playerTurns = true;
              playerTurn.setPlayer(game.getCurrent());
              Greenfoot.setWorld(playerTurn);
          }
          nextPlayerAction.setImagine(1);
          movecard.setLabels(actualLabel,cardLeftLabel,helpLabel,phaseLabel);
          if(movecard.getLabel() == helpLabel){
          movecard.move(deck,pocket,baseTile,movelocation,movepawn,phase1);
            }else{
                act();
            }
          add = true;
        }
        if(((phase1.getPhaseNumber()<5)&&(phase1.getPhaseNumber()>=3))&&(movecard.getFigureRound())){
            if(add){
                game.getPlayer().addFiguresForPlayer(pocket);
                add = false;
                movepawn.setWait(false);
            }
            movepawn.setLabels(pocket,phase1, helpLabel,phaseLabel, game, score);
            if(movepawn.getLabel() == helpLabel){
            movepawn.move(movelocation,movecard.getActualTileNumber(),movecard.getActualCard(),baseTile);
            }else{
                act();
            }
        }
        if(phase1.getPhaseNumber()==5){
            nextPlayerAction.setImagine(2);
            game.getPlayer().delFiguresOfPlayer(pocket);
            if((Greenfoot.mouseClicked(nextPlayerAction.getNextPlayerAction()))) {
                Greenfoot.playSound("sounds/menuClick.wav");
                game.delPlayers(pocket);
                phase1.setPhase(1);
                game.nextPlayerTurn();
                helpLabel.setImagine("CLICKHERE");
                playerTurns = false;
             }
        }
        if((Greenfoot.mouseClicked(callMenu.getCallMenu()))) {
                Greenfoot.playSound("sounds/menuClick.wav");
                pauseMenu.setBoard(this);
                pauseMenu.setForSave(movecard,movepawn,game,phase1.getPhaseNumber(),playerNames);
                pauseMenu.setSound(sound1);
                Greenfoot.setWorld(pauseMenu);  
            }
        if(!sound1.isPlaying()){
        sound1.stop();
        if(sound == 0){
            sound1 = new GreenfootSound("sounds/GRASS.MP3");
            sound1.play();
            sound = 1;
        }
        else if(sound == 1){
            sound1 = new GreenfootSound("sounds/MAINMENU.MP3");
            sound1.play();
            sound = 0;
        }
        }
    }
   
    /**
     * Připraví program pro zahájení dalších akcí, tedy ověří nám za pomoci metody Equals()
     * v Tile ověří, že žádné pole (Tile) se neshodují.
     */
    private void prepare()
    {
        for(int k=0;k<91;k++){
            for(int j=0;j<91;j++){
                if(k!=j){
                    if((baseTile[k].equals(baseTile[j]))){
                        System.out.println("Shoda dvou tilů c."+ k +" a c."+ j + "!");   
                    }
                }
            }
        }
    }
    
    public Board getBoard(){
        return this;
    }
    
    public MoveCard getMoveCard(){
        return movecard;
    }
}
