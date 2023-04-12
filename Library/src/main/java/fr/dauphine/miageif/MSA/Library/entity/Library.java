package fr.dauphine.miageif.MSA.Library.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name="livreIsbn")
    private String livreIsbn;
    @Column(name="lecteur")
    private String lecteur;
    @Column(name="datePret")
    private LocalDate datePret;
    @Column(name="dateRetour")
    private LocalDate dateRetour;

    public Library() {
    }

    public Library(String livreIsbn, String lecteur, LocalDate datePret, LocalDate dateRetour) {
        this.livreIsbn = livreIsbn;
        this.lecteur = lecteur;
        this.datePret = datePret;
        this.dateRetour = dateRetour;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLivreIsbn() {
        return livreIsbn;
    }

    public void setLivreIsbn(String livreIsbn) {
        this.livreIsbn = livreIsbn;
    }

    public String getLecteur() {
        return lecteur;
    }

    public void setLecteur(String lecteur) {
        this.lecteur = lecteur;
    }

    public LocalDate getDatePret() {
        return datePret;
    }

    public void setDatePret(LocalDate datePret) {
        this.datePret = datePret;
    }

    public LocalDate getDateRetour() {
        return dateRetour;
    }

    public void setDateRetour(LocalDate dateRetour) {
        this.dateRetour = dateRetour;

    }

}