package fr.dauphine.miageif.MSA.Library.service;

import fr.dauphine.miageif.MSA.Library.entity.Library;
import fr.dauphine.miageif.MSA.Library.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Object getLivreByIsbn(String isbn) {
        String url = "http://localhost:8001/books/isbn/" + isbn;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class, isbn);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Handle the case when the book is not found
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book with ISBN " + isbn + " not found");
            } else {
                // Handle other HTTP errors
                throw e;
            }
        }
    }

    public Object getUserById(String userId) {
        String url = "http://localhost:8002/personnes/id/" + userId;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class, userId);
            return response.getBody();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                // Handle the case when the user is not found
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + userId + " not found");
            } else {
                // Handle other HTTP errors
                throw e;
            }
        }
    }

    public Library createLibraryEntry(String livreIsbn, String lecteur) {

        Object livre = getLivreByIsbn(livreIsbn);
        Object user = getUserById(lecteur);

        if (livre != null && user != null) {
            Library library = new Library();
            library.setLivreIsbn(livreIsbn);
            library.setLecteur(lecteur);
            library.setDatePret(LocalDate.now());
            library.setDateRetour(null);

            return libraryRepository.save(library);
        }
        return null;
    }
}

