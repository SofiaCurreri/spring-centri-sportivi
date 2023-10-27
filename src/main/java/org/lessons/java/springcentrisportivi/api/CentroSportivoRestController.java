package org.lessons.java.springcentrisportivi.api;

import org.lessons.java.springcentrisportivi.model.CentroSportivo;
import org.lessons.java.springcentrisportivi.repository.CentroSportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/centri-sportivi")
public class CentroSportivoRestController {

    @Autowired
    CentroSportivoRepository centroSportivoRepository;

    //servizio per avere lista centri sportivi
    @GetMapping
    public List<CentroSportivo> index() {
        return centroSportivoRepository.findAll();
    }

    //servizio per dettaglio singolo centro sportivo
    @GetMapping("/{id}")
    public CentroSportivo get(@PathVariable Integer id) {
        Optional<CentroSportivo> centroSportivo = centroSportivoRepository.findById(id);
        if (centroSportivo.isPresent()) {
            return centroSportivo.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non risulta esserci un centro sportivo con id = " + id);
        }
    }
    
}
