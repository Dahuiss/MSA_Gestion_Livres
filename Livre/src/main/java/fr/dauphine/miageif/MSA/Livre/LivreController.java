package fr.dauphine.miageif.MSA.Livre;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/books")
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    @GetMapping("/books/id={livreId}")
    public Livre findById(@PathVariable long livreId) {
        Optional<Livre> livre = livreRepository.findById(livreId);
        if (livre.isPresent()) {
            return livre.get();
        }
        return null;
    }

    @GetMapping("/books/isbn={isbn}")
    public Livre findByIsbn(@PathVariable String isbn) {
        Optional<Livre> livre = livreRepository.findByIsbn(isbn);
        if (livre.isPresent()) {
            return livre.get();
        }
        return null;
    }

    @PostMapping("/books/isbn/{isbn}/auteur/{auteur}/editeur/{editeur}/edition/{edition}")
    public Livre addLivre
            (@PathVariable String isbn,
             @PathVariable String auteur,
             @PathVariable String editeur,
             @PathVariable String edition){

        Optional<Livre> tc = livreRepository.findByIsbn(isbn);
        if(tc == null){
            Livre newLivre = new Livre(isbn,auteur,editeur,edition);
            livreRepository.save(newLivre);
            return newLivre;

        }else throw new ResponseStatusException(HttpStatus.CONFLICT, "Change rate already existed!");

    }

    @DeleteMapping("/books/del/id/{id}")
    public void delete(@PathVariable long id) {
        Optional<Livre> auditAction = livreRepository.findById(id);
        livreRepository.delete(auditAction.orElse(null));
    }

    @PutMapping("/books/upd/id/{id}/isbn/{isbn}")
    public void updateLivre(@PathVariable long id, @PathVariable String isbn){
        Optional<Livre> updLivre = livreRepository.findById(id);
        updLivre.get().setIsbn(isbn);

        livreRepository.save(updLivre.orElse(null));
    }
}
