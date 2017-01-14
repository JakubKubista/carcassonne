/**
 * Třída ChooseFigure je implementací rozhrání Phase a zpřehledňuje nám hru udáním v jaké fázi právě jsme.
 * Tato třída se zaměřuje na čtvrtou a pátou fázi, kde vybereme místo pro figurku a umísťíme figurku.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class ChooseFigure implements  Phase 
{
    public int phaseNumber;
    public String phaseName;
    

    public ChooseFigure(){
    }
    /*
    public void choose(Tile tile,Card card)
    {
        if(tile == null){
           if(!(card.getAbsolute())){
           this.phaseNumber = 4; 
           this.phaseName = "4/5";
           }else{
           this.phaseNumber = 5; 
           this.phaseName = "5/5";
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
