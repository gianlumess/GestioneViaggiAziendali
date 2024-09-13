package gianlucamessina.GestioneViaggiAziendali.services;

import gianlucamessina.GestioneViaggiAziendali.entities.Dipendente;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewDipendenteDTO;
import gianlucamessina.GestioneViaggiAziendali.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
    /*public Dipendente save(NewDipendenteDTO body){
        this.dipendentiRepository.findByEmail(body.email()).ifPresent(dipendente -> {

        });
    }*/
}
