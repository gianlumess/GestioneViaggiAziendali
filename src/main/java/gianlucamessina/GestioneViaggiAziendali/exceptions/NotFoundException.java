package gianlucamessina.GestioneViaggiAziendali.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(UUID id){
        super("La risorsa con ID : "+id+" nonè stata trovata!");
    }
}
