package fr.dauphine.miageif.MSA.Library.controller;

import fr.dauphine.miageif.MSA.Library.entity.Library;
import fr.dauphine.miageif.MSA.Library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    //imaginons que nous avon une seule exemplaire pour chaque livre
    //Possible de gerer le stock dans les versions suivantes
    @PostMapping("/library/add/livreIsbn/{livreIsbn}/lecteur/{lecteur}")
    public Library createLibraryEntry(
            @PathVariable String livreIsbn,
            @PathVariable String lecteur) {
        return libraryService.createLibraryEntry(livreIsbn, lecteur);
    }
    @Autowired
    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

}
