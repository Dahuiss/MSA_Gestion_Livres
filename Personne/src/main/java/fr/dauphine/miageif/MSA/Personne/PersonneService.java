package fr.dauphine.miageif.MSA.Personne;

import fr.dauphine.miageif.MSA.Personne.Personne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {
 
    @Autowired
    private PersonneRepository personneRepository;

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }
 
    public Personne findById(Long id) {
        Optional<Personne> personneOptional = personneRepository.findById(id);
        if (personneOptional.isPresent()) {
            return personneOptional.get();
        }
        return null;
    }
 
    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }

    public Personne update(Personne personne) {
        Optional<Personne> personneOptional = personneRepository.findById(personne.getId());
        if (personneOptional.isPresent()) {
            Personne updatedPersonne = personneOptional.get();
            updatedPersonne.setNom(personne.getNom());
            updatedPersonne.setNom(personne.getPrenom());
            updatedPersonne.setEmail(personne.getEmail());
            return personneRepository.save(updatedPersonne);
        }
        return null;
    }
 
    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }

}
