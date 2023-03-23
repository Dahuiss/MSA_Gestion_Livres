package fr.dauphine.miageif.MSA.Personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {
    @Autowired
    private PersonneService personneService;

    @GetMapping("/")
    public ResponseEntity<List<Personne>> getAllPersonnes() {
        List<Personne> personnes = personneService.getAllPersonnes();
        return new ResponseEntity<>(personnes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personne> getPersonneById(@PathVariable Long id) {
        Personne personne = personneService.getPersonneById(id);
        return new ResponseEntity<>(personne, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        Personne newPersonne = personneService.createPersonne(personne);
        return new ResponseEntity<>(newPersonne, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable Long id, @RequestBody Personne personne) {
        Personne updatedPersonne = personneService.updatePersonne(id, personne);
        return new ResponseEntity<>(updatedPersonne, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonne(@PathVariable Long id) {
        personneService.deletePersonne(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
