package fr.dauphine.miageif.MSA.Personne.controller;

import fr.dauphine.miageif.MSA.Personne.repository.PersonneRepository;
import fr.dauphine.miageif.MSA.Personne.entity.Personne;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonneController {
    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/personnes")
    public List<Personne> getAllPersonnes() {
        List<Personne> personnes = personneRepository.findAll();
        if (personnes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Il n'y a personne");
        }
        return personnes;
    }


    @GetMapping("/personnes/id/{id}")
    public Personne getPersonneById(@PathVariable Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if (personne.isPresent()) {
            return personne.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La personne avec l'ID " + id + " n'existe pas");
        }
    }

    @PostMapping("/personnes/nom/{nom}/prenom/{prenom}/adresse/{adresse}/date_naissance/{date_naissance}/genre/{genre}")
    public Personne createPersonne(
            @PathVariable String nom,
            @PathVariable String prenom,
            @PathVariable String adresse,
            @PathVariable String genre,
            @PathVariable("date_naissance") String date_naissance) throws ParseException {

        Date dateT = null;
        Date dateTplus = null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        dateT = f.parse(date_naissance);
        dateTplus = DateUtils.addDays(dateT, +1);

        Personne newPers = new Personne(nom, prenom, adresse, genre, dateTplus);
        Personne savedPers = personneRepository.save(newPers);
        if (savedPers == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Impossible de créer la personne");
        }
        return savedPers;
    }


    @PutMapping("/personnes/id/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id) {
        Optional<Personne> updatedPersonne = personneRepository.findById(id);
        updatedPersonne.get().setId(id);
        personneRepository.save(updatedPersonne.orElse(null));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/personnes/id/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        personneRepository.delete(personne.orElse(null));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
