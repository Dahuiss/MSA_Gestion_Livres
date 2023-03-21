package fr.dauphine.miageif.MSA.Personne;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @Column(name = "nom")
    private String nom;
 
    @Column(name = "prenom")
    private String prenom;
 
    @Column(name = "email")
    private String email;

     @Column(name = "adresse")
    private String adresse;

     @Column(name = "genre")
    private String genre;

     @Column(name = "date_naissance")
    private Date date_naissance;

    // Date de naissance, Adresse et genre

    public Long getId() {
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    //constructeurs, getters et setters
 
}
