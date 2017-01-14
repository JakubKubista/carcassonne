
/**
 * Rozhrání Phase nám předává metodu choose do tříd ChooseCard a ChooseFigure.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public interface Phase
{
    /**
     * Tato metoda vybere, které pole a která karta prochází aktuální fází. 
     * 1. Fáze: Pokračujeme kliknutím na deck pro výběr karty.
     * 2. Fáze: Pokračujeme vybráním pole pro aktuální kartu.
     * 3. Fáze: Pokračujeme umístěním karty na aktuální pole.
     * 4. Fáze: Pokračujeme vybráním karty pro aktuální figurku.
     * 5. Fáze: Pokračujeme umístěním figurky na aktuální kartu.
     */
    /*
    public void choose(Tile tile,Card card);
    */   
    public void setPhase(int phaseNumber);
    
    public int getPhaseNumber();
    
    public String getPhaseName();
}
