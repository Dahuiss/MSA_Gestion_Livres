package fr.dauphine.miageif.MSA.Library.repository;

import fr.dauphine.miageif.MSA.Library.entity.Library;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {
    @Query("SELECT l FROM Library l WHERE l.id = ?1")
    Optional<Library> findById(Long id);

}
