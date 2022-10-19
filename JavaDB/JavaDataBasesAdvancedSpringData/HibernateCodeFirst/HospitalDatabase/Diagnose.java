package HibernateCodeFirst.HospitalDatabase;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "diagnose")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true ,nullable = false)
    private String name;

    @Lob
    private String comments;

    @OneToMany(mappedBy = "diagnose", targetEntity = GP.class)
    private Set<GP> gp;

    public Diagnose() {
        this.gp = new LinkedHashSet<>();
    }

    public Diagnose(int id, String name, String comments) {
        this();
        this.id = id;
        this.setName(name);
        this.setComments(comments);
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        if (comments.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.comments = comments;
    }

    public Set<GP> getGp() {
        return gp;
    }

    public void setGp(Set<GP> gp) {
        this.gp = gp;
    }
}
