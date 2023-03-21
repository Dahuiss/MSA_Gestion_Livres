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
 
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "livre_id", referencedColumnName = "id")
    private Livre livreEmprunte;
 
    //constructeurs, getters et setters
 
}
