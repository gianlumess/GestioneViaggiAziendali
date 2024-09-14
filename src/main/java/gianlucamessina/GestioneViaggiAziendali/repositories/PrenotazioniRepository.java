package gianlucamessina.GestioneViaggiAziendali.repositories;

import gianlucamessina.GestioneViaggiAziendali.entities.Dipendente;
import gianlucamessina.GestioneViaggiAziendali.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PrenotazioniRepository extends JpaRepository<Prenotazione, UUID> {

    Optional<Prenotazione> findByDipendenteAndViaggioData(Dipendente dipendente, LocalDate dataViaggio);
}
