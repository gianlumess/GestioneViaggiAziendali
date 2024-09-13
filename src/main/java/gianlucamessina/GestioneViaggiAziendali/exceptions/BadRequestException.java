package gianlucamessina.GestioneViaggiAziendali.exceptions;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message){
        super(message);
    }
}
