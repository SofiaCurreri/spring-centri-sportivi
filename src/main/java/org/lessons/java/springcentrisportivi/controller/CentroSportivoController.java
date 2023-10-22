package org.lessons.java.springcentrisportivi.controller;

import org.lessons.java.springcentrisportivi.model.CentroSportivo;
import org.lessons.java.springcentrisportivi.repository.CentroSportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/centri-sportivi")
public class CentroSportivoController {

    @Autowired
    private CentroSportivoRepository centroSportivoRepository;


    //controller per mostrare la lista dei centri sportivi
    @GetMapping
    public String index(Model model) {
        List<CentroSportivo> lista = centroSportivoRepository.findAll();
        if (lista.isEmpty()) {
            model.addAttribute("messaggio", "Non ci sono centri sportivi, mi dispiace :(");
        }
        model.addAttribute("lista", lista);
        return "index";
    }

    //controller che mostra la pagina di dettaglio del centro sportivo richiesto
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer centroSportivoId, Model model) {
        Optional<CentroSportivo> centroSportivo = centroSportivoRepository.findById(centroSportivoId);
        if (centroSportivo.isPresent()) {
//            List<CentroSportivoSport> css = centroSportivo.get().getCentroSportivoSports();

            model.addAttribute("centroSportivo", centroSportivo.get());
            return "show";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non risulta esserci un centro sportivo con id = " + centroSportivoId);
        }
    }


}
