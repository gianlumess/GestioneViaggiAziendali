package gianlucamessina.GestioneViaggiAziendali.payloads;

import java.time.LocalDateTime;

public record ErrorsResponseDTO(String message, LocalDateTime timeStamp) {
}
