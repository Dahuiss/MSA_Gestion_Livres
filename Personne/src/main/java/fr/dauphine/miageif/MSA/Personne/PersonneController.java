
@RestController
@RequestMapping("/personne")
public class PersonneController {
 
    @Autowired
    private PersonneService personneService;
 
    @GetMapping("")
    public List<Personne> findAll() {
        return personneService.findAll();
    }
 
    @GetMapping("/{id}")
    public Personne findById(@PathVariable Long id) {
        return personneService.findById(id);
    }
 
    @PostMapping("")
    public Personne save(@RequestBody Personne personne) {
        return personneService.save(personne);
    }
 
    @PutMapping("")
    public Personne update(@RequestBody Personne personne) {
        return personneService.update(personne);
    }
 
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        personneService.deleteById(id);
    }
 
}
