package org.lessons.java.springcentrisportivi.controller;

import jakarta.validation.Valid;
import org.lessons.java.springcentrisportivi.model.Sport;
import org.lessons.java.springcentrisportivi.repository.SportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sports")
public class SportController {

    @Autowired
    SportRepository sportRepository;

    //controller per mostrare lista di tutti gli sport che offre la catena di centri sportivi e per creare/modificare uno sport
    @GetMapping
    public String index(Model model, @RequestParam("edit") Optional<Integer> sportId) {
        List<Sport> sports = sportRepository.findAll();

        //se lista sport vuota, passo un messaggio di avviso
        if (sports.isEmpty()) {
            model.addAttribute("messaggio", "Non ci sono sport, mi dispiace");
        }

        //se lista non vuota, la passo al model
        model.addAttribute("sports", sports);

        //creo oggetto di tipo Sport come appoggio per i passaggi successivi
        Sport sportObj;

        //se ricevo parametro sportId
        if (sportId.isPresent()) {

            //lo uso per cercare nel mio db uno sport con id = sportId
            Optional<Sport> sport = sportRepository.findById(sportId.get());

            //se sport con id = sportId è presente nel mio db, valorizzo sportObj con lo sport trovato
            if (sport.isPresent()) {
                sportObj = sport.get();
            } else {
                //se non è presente uno sport con id = sportId, valorizzo sportObj con un nuovo Sport
                sportObj = new Sport();
            }
        } else {
            //se non ricevo parametro, valorizzo sportObj con un nuovo Sport
            sportObj = new Sport();
        }

        model.addAttribute("sportObj", sportObj);
        return "indexSports";
    }

    //controller per salvare creazione/modifica sport
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("sportObj") Sport sportForm, BindingResult bindingResult, Model model) {

        //validazione personalizzata per unique constraint dato all' attributo nome
        if (sportRepository.existsByNome(sportForm.getNome())) {
            bindingResult.rejectValue("nome", "nome.duplicate", "Il nome inserito risulta essere già in uso");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("sports", sportRepository.findAll());
            return "indexSports";
        }

        sportRepository.save(sportForm);
        return "redirect:/sports";
    }
}
