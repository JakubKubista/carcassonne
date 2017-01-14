import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * Třída Loading je třída, který existuje pro spestření designu a říká nám kdy se hra načítá.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */

public class Loading extends World
{

    private boolean loading;
    /**
     * Constructor for objects of class ChoiseMenu.
     * 
     */
    public Loading()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1348, 650, 1); 
    }
    public void loadBoard(Board board){
              board.load();
              Greenfoot.setWorld(board);     
         /*
          if(!loading){
              try {
               Thread.sleep(20000);
               }catch (Exception e){
                   System.out.println("Nastala vyjimka (Chybna v posunu karty):" + e.toString());
                   e.printStackTrace();
               }
               Thread t = new Thread();
               t.start();
               loading = true;
         }else{
              board.load();
              Greenfoot.setWorld(board);       
          }
          */
    }    
    Loading getLoading(){
        return this;
    }
}
