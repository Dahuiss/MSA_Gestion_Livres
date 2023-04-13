package fr.dauphine.miageif.MSA.Library;

import fr.dauphine.miageif.MSA.Library.controller.LibraryController;
import fr.dauphine.miageif.MSA.Library.entity.Library;
import fr.dauphine.miageif.MSA.Library.service.LibraryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LibraryControllerTest {

    private LibraryController libraryController;

    @MockBean
    private LibraryService libraryService;

    @BeforeEach
    public void setUp() {
        libraryController = new LibraryController(libraryService);
    }

    @Test
    public void testCreateLibraryEntry() {
        String livreIsbn = "001";
        String userId = "1";
        LocalDate datePret = LocalDate.now();

        Library expectedLibrary = new Library(livreIsbn, userId, datePret, null);
        when(libraryService.createLibraryEntry(livreIsbn, userId)).thenReturn(expectedLibrary);

        Library actualLibrary = libraryController.createLibraryEntry(livreIsbn, userId);

        assertNotNull(actualLibrary);
        assertEquals(expectedLibrary.getLivreIsbn(), actualLibrary.getLivreIsbn());
        assertEquals(expectedLibrary.getLecteur(), actualLibrary.getLecteur());
        assertEquals(expectedLibrary.getDatePret(), actualLibrary.getDatePret());
        assertEquals(expectedLibrary.getDateRetour(), actualLibrary.getDateRetour());
    }
}
