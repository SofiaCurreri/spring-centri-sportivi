package org.lessons.java.springcentrisportivi.controller;

import org.lessons.java.springcentrisportivi.model.CentroSportivo;
import org.lessons.java.springcentrisportivi.repository.CentroSportivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
}
