package org.lessons.java.springcentrisportivi.controller;

import jakarta.validation.Valid;
import org.lessons.java.springcentrisportivi.model.CentroSportivo;
import org.lessons.java.springcentrisportivi.repository.CentroSportivoRepository;
import org.lessons.java.springcentrisportivi.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/centri-sportivi")
public class CentroSportivoController {

    @Autowired
    private CentroSportivoRepository centroSportivoRepository;

    @Autowired
    private SportRepository sportRepository;


    //controller per mostrare la lista dei centri sportivi
    @GetMapping
    public String index(Model model) {
        List<CentroSportivo> lista = centroSportivoRepository.findAll();
        if (lista.isEmpty()) {
            model.addAttribute("messaggio", "Non ci sono centri sportivi, mi dispiace");
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

    //controller che restituisce pagina con form di creazione
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("centroSportivo", new CentroSportivo());
        model.addAttribute("sportsList", sportRepository.findAll());
        return "editCreate";
    }

    //controller per creare e salvare il nuovo centro sportivo
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("centroSportivo") CentroSportivo centroSportivoForm, BindingResult bindingResult, Model model) {
        if (centroSportivoRepository.existsByIndirizzo(centroSportivoForm.getIndirizzo())) {
            bindingResult.rejectValue("indirizzo", "indirizzo.duplicate", "L' indirizzo inserito risulta essere già in uso");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("sportsList", sportRepository.findAll());
            return "editCreate";
        }

        centroSportivoForm.setCreatedAt(LocalDateTime.now());
        centroSportivoForm.setUpdatedAt(LocalDateTime.now());

        if (centroSportivoForm.getNome() == null || centroSportivoForm.getNome().isEmpty()) {
            centroSportivoForm.setNome("SportPlus");
        }

        centroSportivoRepository.save(centroSportivoForm);
        return "redirect:/centri-sportivi";
    }

}
