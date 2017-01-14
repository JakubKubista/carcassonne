/**
 * Třída FileNameException odpovídá vlastní výjmce.
 * 
 * @author Jakub Kubišta 
 * @version 1.0
 */
public class FileNameException extends Exception {
    public FileNameException () {
    }

    public FileNameException (String message) {
        super (message);
    }

    public FileNameException (Throwable cause) {
        super (cause);
    }

    public FileNameException (String message, Throwable cause) {
        super (message, cause);
    }
}
