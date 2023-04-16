package fr.dauphine.miageif.MSA.Personne.repository;

import fr.dauphine.miageif.MSA.Personne.entity.Personne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {
    @Query("SELECT p FROM Personne p WHERE p.id = ?1")
    Optional<Personne> findById(Long id);

}