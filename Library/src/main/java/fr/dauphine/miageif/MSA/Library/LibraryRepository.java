package fr.dauphine.miageif.MSA.Library;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query("SELECT p FROM Personne p WHERE p.id = ?1")
    Optional<Library> findById(Long id);
}
