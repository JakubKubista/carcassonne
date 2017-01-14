
/**
 * Třída CardException je potomek třídy RuntimeException, neboli námi vytvořená výjmka.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class CardException extends RuntimeException
{
        public CardException(String message){
        super(message);
    }
    
        public CardException(String message, Exception e){
        super(message, e);
    }
}
