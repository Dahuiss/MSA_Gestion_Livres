package fr.dauphine.miageif.MSA.Livre.controller;

import fr.dauphine.miageif.MSA.Livre.entity.Livre;
import fr.dauphine.miageif.MSA.Livre.repository.LivreRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@Api(value = "Books API", description = "API for managing books")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/books")
    @ApiOperation(value = "Get all books", notes = "Retrieve a list of all books.")
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    @GetMapping("/books/id={livreId}")
    @ApiOperation(value = "Find book by ID", notes = "Retrieve a book by its ID.")
    public Livre findById(@PathVariable @ApiParam(value = "Book ID", required = true)  long livreId) {
        Optional<Livre> livre = livreRepository.findById(livreId);
        if (livre.isPresent()) {
            return livre.get();
        }
        return null;
    }

    @GetMapping("/books/isbn={isbn}")
    @ApiOperation(value = "Find book by ISBN", notes = "Retrieve a book by its ISBN.")
    public Livre findByIsbn(@PathVariable @ApiParam(value = "Book ISBN", required = true) String isbn) {
        Optional<Livre> livre = livreRepository.findByIsbn(isbn);
        if (livre.isPresent()) {
            return livre.get();
        }
        return null;
    }

    @GetMapping("/books/titre={titre}")
    @ApiOperation(value = "Find books by title", notes = "Retrieve a list of books by searching their titles.")
    public ResponseEntity<List<Livre>> findByTitre(@PathVariable @ApiParam(value = "Book title", required = true) String titre) {
        List<Livre> livres = livreRepository.findByTitreContainingIgnoreCase(titre);
        if (livres.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(livres);
    }


    @PostMapping("/books/add/isbn/{isbn}/auteur/{auteur}/titre/{titre}/editeur/{editeur}/edition/{edition}")
    @ApiOperation(value = "Add a new book", notes = "Create a new book with the provided information.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Book created successfully"),
            @ApiResponse(code = 409, message = "Book already exists")
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Livre addLivre(
            @PathVariable @ApiParam(value = "ISBN of the book", required = true) String isbn,
            @PathVariable @ApiParam(value = "Author of the book", required = true) String auteur,
            @PathVariable @ApiParam(value = "Titre of the book", required = true) String titre,
            @PathVariable @ApiParam(value = "Publisher of the book", required = true) String editeur,
            @PathVariable @ApiParam(value = "Edition of the book", required = true) String edition) {

        Optional<Livre> existingLivre = livreRepository.findByIsbn(isbn);
        if (existingLivre.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book with the given ISBN already exists!");
        }

        Livre newLivre = new Livre(isbn, auteur, titre, editeur, edition);
        livreRepository.save(newLivre);
        return newLivre;
    }


    @DeleteMapping("/books/del/id/{id}")
    @ApiOperation(value = "Delete a book by ID", notes = "Remove a book with the specified ID.")
    public void delete(@PathVariable @ApiParam(value = "Book ID", required = true) long id) {
        Optional<Livre> auditAction = livreRepository.findById(id);
        livreRepository.delete(auditAction.orElse(null));
    }

    @PutMapping("/books/upd/id/{id}/isbn/{isbn}")
    @ApiOperation(value = "Update a book's ISBN", notes = "Update the ISBN of a book with the specified ID.")
    public void updateLivre(@PathVariable @ApiParam(value = "Book ID", required = true) long id,
                            @PathVariable @ApiParam(value = "New ISBN", required = true) String isbn){
        Optional<Livre> updLivre = livreRepository.findById(id);
        updLivre.get().setIsbn(isbn);

        livreRepository.save(updLivre.orElse(null));
    }
}
