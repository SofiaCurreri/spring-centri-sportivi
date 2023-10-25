package org.lessons.java.springcentrisportivi.controller;

import jakarta.validation.Valid;
import org.lessons.java.springcentrisportivi.model.CentroSportivo;
import org.lessons.java.springcentrisportivi.model.Membro;
import org.lessons.java.springcentrisportivi.repository.CentroSportivoRepository;
import org.lessons.java.springcentrisportivi.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/membri")
public class MembroController {

    @Autowired
    CentroSportivoRepository centroSportivoRepository;

    @Autowired
    MembroRepository membroRepository;


    //controller per creare nuovo membro, restituisce form di creazione
    @GetMapping("/create")
    public String create(@RequestParam("centroSportivoId") Integer centroSportivoId, Model model) {
        Membro membro = new Membro();

        //controllo che centro sportivo con id passato esista nel mio db
        Optional<CentroSportivo> centroSportivo = centroSportivoRepository.findById(centroSportivoId);
        //se non c' è tiro un' eccezione
        if (centroSportivo.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non risulta esserci un centro sportivo con id = " + centroSportivoId);
        }


        //se c' è setto attributo centroSportivo con il centroSportivo di cui sopra
        membro.setCentroSportivo(centroSportivo.get());

//        List<Sport> sports = centroSportivo.get().getSportDisponibili();

//        model.addAttribute("sports", sports);
        model.addAttribute("membro", membro);
        return "editCreateMembro";
    }

    //controller per validare e salvare membro che si vuole creare
    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("membro") Membro membroForm, BindingResult bindingResult, Model model) {

        //validazione personalizzata per unique constraint dato all' attributo email
        if (membroRepository.existsByEmail(membroForm.getEmail())) {
            bindingResult.rejectValue("email", "email.duplicate", "L' email inserita risulta essere già in uso");
        }

        if (bindingResult.hasErrors()) {
            return "editCreateMembro";
        }

        membroForm.setCreatedAt(LocalDateTime.now());
        membroForm.setUpdatedAt(LocalDateTime.now());

        membroRepository.save(membroForm);
        return "redirect:/centri-sportivi/" + membroForm.getCentroSportivo().getId();
    }

    //controller per modificare membro
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<Membro> membro = membroRepository.findById(id);
        if (membro.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non risulta esserci un membro con id = " + id);
        }

        Membro membroToEdit = membro.get();
        model.addAttribute("membro", membroToEdit);
        return "editCreateMembro";
    }

    //controller per salvare modifiche apportate
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("membro") Membro membroForm, BindingResult bindingResult) {
        Optional<Membro> membro = membroRepository.findById(id);

        if (membro.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non risulta esserci un membro con id = " + id);
        }

        Membro membroToEdit = membro.get();

        //validazione personalizzata per unique constraint dato all' attributo email
        if (membroRepository.existsByEmail(membroForm.getEmail()) && !membroToEdit.getEmail().equals(membroForm.getEmail())) {
            bindingResult.rejectValue("email", "email.duplicate", "L' email inserita risulta essere già in uso");
        }

        if (bindingResult.hasErrors()) {
            return "editCreateMembro";
        }

        membroForm.setId(membroToEdit.getId());
        membroForm.setCreatedAt(membroToEdit.getCreatedAt());
        membroForm.setUpdatedAt(LocalDateTime.now());

        membroRepository.save(membroForm);
        return "redirect:/centri-sportivi/" + membroForm.getCentroSportivo().getId();
    }

}
