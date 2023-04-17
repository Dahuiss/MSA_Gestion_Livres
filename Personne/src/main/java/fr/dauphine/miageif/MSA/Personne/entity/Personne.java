package fr.dauphine.miageif.MSA.Personne.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "genre")
    private String genre;

    @Column(name = "date_naissance", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date_naissance;

    public Personne() {

    }

    public Personne(String nom, String prenom, String adresse, String genre, Date date_naissance) {
        super();
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.genre = genre;
        this.date_naissance = date_naissance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }
}
