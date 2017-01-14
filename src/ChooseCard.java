/**
 * Třída ChooseCard je implementací rozhrání Phase a zpřehledňuje nám hru udáním v jaké fázi právě jsme.
 * Tato třída se zaměřuje na první dv+e fáze, kde vybíráme kartu a poté místo pro kartu.
 * 
 * @author Jakub Kubišta 
 * @version 1.0)
 */
public class ChooseCard implements  Phase 
{
    public int phaseNumber;
    public String phaseName;
        
    public ChooseCard(){
           this.phaseNumber = 1; 
           this.phaseName = "1/4";
    }
    /*
    public void choose(Tile tile,Card card)
    {
        if((tile ==null)&&(card ==null)){
           this.phaseNumber = 1; 
           this.phaseName = "1/5";
        }
        
        if(card == null){
           if(!(tile.getAbsolute())){
           this.phaseNumber = 2; 
           this.phaseName = "2/5";
           }else{
           this.phaseNumber = 3; 
           this.phaseName = "3/5";
           }
        }       
    }
    */
    public void setPhase(int phaseNumber){
        this.phaseNumber = phaseNumber;
        if(phaseNumber==5){
          this.phaseName = "";  
          }else{
           this.phaseName = phaseNumber + "/5";
        }
    }
    
    public int getPhaseNumber(){
        return phaseNumber;
    }
    
    public String getPhaseName(){
        return phaseName;
    }
}
