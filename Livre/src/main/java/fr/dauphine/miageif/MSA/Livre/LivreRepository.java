package fr.dauphine.miageif.MSA.Livre;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {
    @Query("select l from Livre l where l.isbn = ?1")
    Optional<Livre> findByIsbn(String isbn);
}
