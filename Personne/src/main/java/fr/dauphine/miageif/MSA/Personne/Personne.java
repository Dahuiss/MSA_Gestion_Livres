package fr.dauphine.miageif.MSA.Personne;

import javax.persistence.*;

@Entity
@Table
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
 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livre_id", referencedColumnName = "id")
    private Livre livreEmprunte;

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
