package gianlucamessina.GestioneViaggiAziendali.controllers;

import gianlucamessina.GestioneViaggiAziendali.entities.Viaggio;
import gianlucamessina.GestioneViaggiAziendali.exceptions.BadRequestException;
import gianlucamessina.GestioneViaggiAziendali.payloads.NewViaggioDTO;
import gianlucamessina.GestioneViaggiAziendali.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {
    @Autowired
    private ViaggioService viaggioService;

    //GET DELLA LISTA DI VIAGGI (http://localhost:3001/viaggi)
    @GetMapping
    public Page<Viaggio> findAll(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "15") int size,
                                 @RequestParam(defaultValue = "id") String sortBy){

        return this.viaggioService.findAll(page,size,sortBy);
    }

    //POST (http://localhost:3001/viaggi)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio save(@RequestBody @Validated NewViaggioDTO body, BindingResult validation){
        if(validation.hasErrors()){
            String messages=validation.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()).collect(Collectors.joining(". "));

            throw new BadRequestException("ci sono stati errori nel payload: "+messages);
        }

        return this.viaggioService.save(body);
    }

    //GET FIND BY ID (http://localhost:3001/viaggi/{viaggiId})
    @GetMapping("/{viaggioId}")
    public Viaggio findById(@PathVariable UUID viaggioId){
        return this.viaggioService.findById(viaggioId);
    }
}
