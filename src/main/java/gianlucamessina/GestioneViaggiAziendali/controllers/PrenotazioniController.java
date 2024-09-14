package gianlucamessina.GestioneViaggiAziendali.controllers;

import gianlucamessina.GestioneViaggiAziendali.entities.Prenotazione;
import gianlucamessina.GestioneViaggiAziendali.entities.Viaggio;
import gianlucamessina.GestioneViaggiAziendali.exceptions.BadRequestException;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewPrenotazioneDTO;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewViaggioDTO;
import gianlucamessina.GestioneViaggiAziendali.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {
    @Autowired
    private PrenotazioneService prenotazioneService;

    //GET DELLA LISTA DI PRENOTAZIOI (http://localhost:3001/prenotazioni)
    @GetMapping
    public Page<Prenotazione> findAll(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "15") int size,
                                      @RequestParam(defaultValue = "id") String sortBy){

        return this.prenotazioneService.findAll(page,size,sortBy);
    }

    //POST (http://localhost:3001/prenotazioni)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione save(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validation){
        if(validation.hasErrors()){
            String messages=validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: "+messages);
        }

        return this.prenotazioneService.save(body);
    }

    //GET FIND BY ID (http://localhost:3001/prenotazioni/{prenotazioneId})
    @GetMapping("/{prenotazioneId}")
    public Prenotazione findById(@PathVariable UUID prenotazioneId){
        return this.prenotazioneService.findById(prenotazioneId);
    }
}
