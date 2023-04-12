package fr.dauphine.miageif.MSA.Library.service;

import fr.dauphine.miageif.MSA.Library.entity.Library;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
public class LibraryService {

    @Autowired
    private RestTemplate restTemplate;

    public Object getLivreByIsbn(String isbn) {
        String livreApiUrl = "http://localhost:8001/books/isbn/" + isbn;
        return restTemplate.getForObject(livreApiUrl, Object.class, isbn);
    }

    public Object getUserByID(String lecteur) {
        String userApiUrl = "http://localhost:8002/users/" + lecteur;
        return restTemplate.getForObject(userApiUrl, Object.class, lecteur);
    }

    public Library createLibraryEntry(String livreIsbn, String lecteur) {
        Object livre = getLivreByIsbn(livreIsbn);
        Object user = getUserByID(lecteur);

        if (livre != null && user != null) {
            LocalDate datePret = LocalDate.now();
            LocalDate dateRetour = null;
            return new Library(livreIsbn, lecteur, datePret, dateRetour);
        }

        return null;
    }
}

