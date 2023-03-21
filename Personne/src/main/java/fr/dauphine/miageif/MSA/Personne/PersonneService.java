@Service
public class PersonneService {
 
    @Autowired
    private PersonneRepository personneRepository;
    
    @Autowired
    private LivreRepository livreRepository;
 
    public List<Personne> findAll() {
        return personneRepository.findAll();
    }
 
    public Personne findById(Long id) {
        Optional<Personne> personneOptional = personneRepository.findById(id);
        if (personneOptional.isPresent()) {
            return personneOptional.get();
        }
        return null;
    }
 
    public Personne save(Personne personne) {
        Livre livreEmprunte = personne.getLivreEmprunte();
        if (livreEmprunte != null) {
            livreRepository.save(livreEmprunte);
        }
        return personneRepository.save(personne);
    }
 
    public Personne update(Personne personne) {
        Optional<Personne> personneOptional = personneRepository.findById(personne.getId());
        if (personneOptional.isPresent()) {
            Personne updatedPersonne = personneOptional.get();
            updatedPersonne.setNom(personne.getNom());
            updatedPersonne.setPrenom(personne.getPrenom());
            updatedPersonne.setEmail(personne.getEmail());
            updatedPersonne.setLivreEmprunte(personne.getLivreEmprunte());
            Livre livreEmprunte = updatedPersonne.getLivreEmprunte();
            if (livreEmprunte != null) {
                livreRepository.save(livreEmprunte);
            }
            return personneRepository.save(updatedPersonne);
        }
        return null;
    }
 
    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }
 
}
