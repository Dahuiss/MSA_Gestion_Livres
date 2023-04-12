package fr.dauphine.miageif.MSA.Library.controller;

import fr.dauphine.miageif.MSA.Library.entity.Library;
import fr.dauphine.miageif.MSA.Library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    //imaginons que nous avon une seule exemplaire pour chaque livre
    //Possible de gerer le stock dans les versions suivantes
    @PostMapping("/library/add/livreIsbn/{livreIsbn}/lecteur/{lecteur}")
    public Library createLibraryEntry(
            @RequestParam String livreIsbn,
            @RequestParam String lecteur) {
        return libraryService.createLibraryEntry(livreIsbn, lecteur);
    }
}
