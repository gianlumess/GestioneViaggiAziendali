package gianlucamessina.GestioneViaggiAziendali.services;

import gianlucamessina.GestioneViaggiAziendali.entities.Dipendente;
import gianlucamessina.GestioneViaggiAziendali.exceptions.BadRequestException;
import gianlucamessina.GestioneViaggiAziendali.exceptions.NotFoundException;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewDipendenteDTO;
import gianlucamessina.GestioneViaggiAziendali.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DipendenteService {
    @Autowired
    private DipendentiRepository dipendentiRepository;

    //FIND ALL CON PAGINAZIONE
    public Page<Dipendente> findAll(int page,int size,String sortBy){
        //controllo se viene richiesta una pagina superiore a quelle presenti effettivamente
        if(page>150)page=150;

        Pageable pageable= PageRequest.of(page,size, Sort.by(sortBy));

        return this.dipendentiRepository.findAll(pageable);
    }

    //SAVE DI UN NUOVO DIPENDENTE
    public Dipendente save(NewDipendenteDTO body){
        this.dipendentiRepository.findByEmail(body.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email: "+body.email()+" è già in uso!");
        });

        this.dipendentiRepository.findByUsername(body.username()).ifPresent(dipendente -> {
            throw new BadRequestException("L'username: "+body.username()+" è già in uso!");
        });

        Dipendente dipendente=new Dipendente(body.username(), body.nome(), body.cognome(),body.email(),
                "https://ui-avatars.com/api/?name="+body.nome()+"+"+body.cognome() );

        return this.dipendentiRepository.save(dipendente);
    }

    //FIND BY ID
    public Dipendente findById(UUID dipendenteId){
        return this.dipendentiRepository.findById(dipendenteId).orElseThrow(()->new NotFoundException(dipendenteId));
    }

    //FIND BY ID AND UPDATE
    public Dipendente findByIdAndUpdate(UUID dipendenteId,NewDipendenteDTO body){
        //controllo che l'utente che voglio modificare esista
        Dipendente newDipendente= this.findById(dipendenteId);

        this.dipendentiRepository.findByEmail(body.email()).ifPresent(dipendente -> {
            throw new BadRequestException("L'email: "+body.email()+" è già in uso!");
        });

        this.dipendentiRepository.findByUsername(body.username()).ifPresent(dipendente -> {
            throw new BadRequestException("L'username: "+body.username()+" è già in uso!");
        });

        newDipendente.setUsername(body.username());
        newDipendente.setNome(body.nome());
        newDipendente.setCognome(body.cognome());
        newDipendente.setEmail(body.email());

        return this.dipendentiRepository.save(newDipendente);
    }

    public void findByIdAndDelete(UUID dipendenteId){
        Dipendente found=this.findById(dipendenteId);
        this.dipendentiRepository.delete(found);
    }
}
