package fr.dauphine.miageif.MSA.Livre;

import javax.persistence.*;

@Entity
@Table(name = "livre")
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="isbn")
    private String isbn;

    @Column(name="auteur")
    private String auteur;

    @Column(name="editeur")
    private String editeur;

    @Column(name="edition")
    private String edition;

    public Livre(String isbn, String auteur, String editeur, String edition) {
        this.isbn = isbn;
        this.auteur = auteur;
        this.editeur = editeur;
        this.edition = edition;
    }

    public Livre() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
