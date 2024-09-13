package gianlucamessina.GestioneViaggiAziendali.controllers;

import gianlucamessina.GestioneViaggiAziendali.entities.Dipendente;
import gianlucamessina.GestioneViaggiAziendali.exceptions.BadRequestException;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewDipendenteDTO;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewDipendenteRespDTO;
import gianlucamessina.GestioneViaggiAziendali.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendenteService dipendenteService;

    //GET DELLA LISTA DI DIPENDENTI (http://localhost:3001/dipendenti)
    @GetMapping
    public Page<Dipendente> findAll(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "15") int size,
                                    @RequestParam(defaultValue = "id") String sortBy){

        return this.dipendenteService.findAll(page,size,sortBy);
    }

    //POST (http://localhost:3001/dipendenti)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validation){
        // @Validated serve per 'attivare' le regole di validazione descritte nel DTO
        // BindingResult permette di capire se ci sono stati errori e quali

        if(validation.hasErrors()){
            String messages=validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: "+messages);
        }

        return this.dipendenteService.save(body);
    }
}
