package gianlucamessina.GestioneViaggiAziendali.payloads;

import java.time.LocalDate;
import java.util.UUID;

public record PrenotazioneRespDTO(UUID id,
                                  LocalDate dataRichiesta,
                                  String volo,
                                  String alloggio,
                                  String dipendenteId,
                                  String viaggioId) {
}
