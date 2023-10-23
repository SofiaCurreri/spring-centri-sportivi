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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

        //validazione personalizzata per unique constraint dato all' attributo indirizzo
        if (centroSportivoRepository.existsByIndirizzo(centroSportivoForm.getIndirizzo())) {
            bindingResult.rejectValue("indirizzo", "indirizzo.duplicate", "L' indirizzo inserito risulta essere già in uso");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("sportsList", sportRepository.findAll());
            return "editCreate";
        }

        centroSportivoForm.setCreatedAt(LocalDateTime.now());
        centroSportivoForm.setUpdatedAt(LocalDateTime.now());

        //se utente non inserisce un nome nel compilare form di creazione, verrà assegnato automaticamente 'SportPlus'
        if (centroSportivoForm.getNome() == null || centroSportivoForm.getNome().isEmpty()) {
            centroSportivoForm.setNome("SportPlus");
        }

        centroSportivoRepository.save(centroSportivoForm);

        return "redirect:/centri-sportivi";
    }

    //controller per modificare il centro sportivo
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Optional<CentroSportivo> result = centroSportivoRepository.findById(id);

        //controllo che esista il centro sportivo che si vuole modificare
        //se non esiste lancio eccezione
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non esiste un centro sportivo con id = " + id);
        }

        //se esiste passo centro sportivo con id = centroSportivoId al model così da popolare form dell' edit
        CentroSportivo centroSportivoToEdit = result.get();
        model.addAttribute("centroSportivo", centroSportivoToEdit);
        model.addAttribute("sportsList", sportRepository.findAll());

        return "editCreate";
    }

    //controller per salvare modifiche apportate al centro sportivo
    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("centroSportivo") CentroSportivo centroSportivoForm, BindingResult bindingResult, Model model) {
        Optional<CentroSportivo> result = centroSportivoRepository.findById(id);

        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non esiste un centro sportivo con id = " + id);
        }


        if (bindingResult.hasErrors()) {
            model.addAttribute("sportsList", sportRepository.findAll());
            return "editCreate";
        }

        CentroSportivo centroSportivoToEdit = result.get();

        //validazione personalizzata per unique constraint dato all' attributo indirizzo
        if (centroSportivoRepository.existsByIndirizzo(centroSportivoForm.getIndirizzo()) && !centroSportivoToEdit.getIndirizzo().equals(centroSportivoForm.getIndirizzo())) {
            bindingResult.rejectValue("indirizzo", "indirizzo.duplicate", "L' indirizzo inserito risulta essere già in uso");
        }

        //se utente cancella nome preesistente senza inserirne uno nuovo, verrà assegnato automaticamente 'SportPlus'
        if (centroSportivoToEdit.getNome() == null || centroSportivoToEdit.getNome().isEmpty()) {
            centroSportivoForm.setNome("SportPlus");
        }

        //setto elementi non gestibili dall' utente
        centroSportivoForm.setId(centroSportivoToEdit.getId());
        centroSportivoForm.setCreatedAt(centroSportivoToEdit.getCreatedAt());
        centroSportivoForm.setUpdatedAt(LocalDateTime.now());

        centroSportivoRepository.save(centroSportivoForm);
        return "redirect:/centri-sportivi/{id}";
    }

    //controller per eliminare centro sportivo
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        Optional<CentroSportivo> result = centroSportivoRepository.findById(id);

        //controllo che il centro sportivo che si vuole eliminare esista
        //se non esiste lancio eccezione
        if (result.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Non esiste un centro sportivo con id = " + id);
        }

        //se esiste lo estraggo con .get() per poi eliminarlo
        CentroSportivo centroSportivoToDelete = result.get();

        centroSportivoRepository.delete(centroSportivoToDelete);
        redirectAttributes.addFlashAttribute("messaggio", "Il centro sportivo " + centroSportivoToDelete.getNome() + " è stato eliminato!");
        return "redirect:/centri-sportivi";
    }

}
