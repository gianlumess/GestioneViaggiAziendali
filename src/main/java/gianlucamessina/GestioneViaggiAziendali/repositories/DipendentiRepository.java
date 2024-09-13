package gianlucamessina.GestioneViaggiAziendali.repositories;

import gianlucamessina.GestioneViaggiAziendali.entities.Dipendente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface DipendentiRepository extends JpaRepository<Dipendente, UUID> {

        Optional<Dipendente> findByUsername(String username);
        Optional<Dipendente> findByEmail(String email);
}
