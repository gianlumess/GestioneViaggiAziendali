package gianlucamessina.GestioneViaggiAziendali.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "dipendenti")
public class Dipendente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String username;
    private String nome;
    private String cognome;
    private String email;
    @Column(name = "foto_profilo")
    private String fotoProfilo;

    public Dipendente(String username, String nome, String cognome, String email, String fotoProfilo) {
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.fotoProfilo = fotoProfilo;
    }
}
