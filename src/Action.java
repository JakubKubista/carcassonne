import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Abstraktní třída Action je předek tříd NextPlayerAction, MoveFigure a MoveCard. Neboli těmto
 * třídám dědí metody move() a isEnabled(). Zároveň zahrnuje většinu pohybů ve hře.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public abstract class Action extends Actor  
{
    
    public abstract boolean isEnabled();
    
}