package fr.dauphine.miageif.MSA.Personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonneController {
    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/personnes")
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneRepository.findAll();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @GetMapping("/personnes/id={id}")
    public Personne getPersonneById(@PathVariable Long id) {
        Optional<Personne> personne = personneRepository.findById(id);
        if ( personne.isPresent()){
            return personne.get();
        }
        return null;
    }

    @PostMapping("/personnes/id/{id}/nom/{nom}/prenom/{prenom}/adresse/{adresse}/date_naissance/{date_naissance}/genre/{genre}")
    public Personne createPersonne(@PathVariable Long id,
                                   @PathVariable String nom,
                                   @PathVariable String prenom,
                                   @PathVariable String adresse,
                                   @PathVariable String genre,
                                   @PathVariable Date date_naissance
                                                   ) {
        Optional<Personne> personne = personneRepository.findById(id);
        if(personne == null){
            Personne newPers = new Personne(id,nom,prenom,adresse,genre,date_naissance);
            personneRepository.save(newPers);
            return newPers;
        } else throw new ResponseStatusException(HttpStatus.CONFLICT,"Cette personne existe déjà :)");
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
