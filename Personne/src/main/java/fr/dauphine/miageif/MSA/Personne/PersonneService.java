package fr.dauphine.miageif.MSA.Personne;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonneService {
    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> getAllPersonnes() {
        return personneRepository.findAll();
    }

    public Personne getPersonneById(Long id) {
        return personneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));
    }

    public Personne createPersonne(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne updatePersonne(Long id, Personne personne) {
        Personne existingPersonne = personneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));

        existingPersonne.setNom(personne.getNom());
        existingPersonne.setPrenom(personne.getPrenom());
        existingPersonne.setAdresse(personne.getAdresse());
        existingPersonne.setGenre(personne.getGenre());
        existingPersonne.setDate_naissance(personne.getDate_naissance());

        return personneRepository.save(existingPersonne);
    }

    public void deletePersonne(Long id) {
        Personne existingPersonne = personneRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Personne introuvable"));

        personneRepository.delete(existingPersonne);
    }
}
