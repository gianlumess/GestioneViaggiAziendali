package gianlucamessina.GestioneViaggiAziendali.payloads;

import gianlucamessina.GestioneViaggiAziendali.enums.StatoViaggio;
import jakarta.validation.constraints.NotNull;

public record NewViaggioStatoDTO(@NotNull(message = "devi passare un valore tra 'IN_PROGRAMMA' e 'COMPLETATO', non può essere vuoto!")
                                 StatoViaggio statoViaggio) {
}
