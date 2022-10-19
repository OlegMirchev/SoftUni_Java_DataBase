package HibernateCodeFirst.HospitalDatabase;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "medicament")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "medicament", targetEntity = GP.class)
    private Set<GP> gp;

    public Medicament() {
        this.gp = new LinkedHashSet<>();
    }

    public Medicament(int id, String name) {
        this();
        this.id = id;
        this.setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.name = name;
    }

    public Set<GP> getGp() {
        return gp;
    }

    public void setGp(Set<GP> gp) {
        this.gp = gp;
    }
}
