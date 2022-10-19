package HibernateCodeFirst.HospitalDatabase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "visitation")
public class Visitation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDate date;

    @Lob
    private String comments;

    @OneToMany(mappedBy = "visitation", targetEntity = GP.class)
    private Set<GP> gp;

    public Visitation() {
        this.gp = new LinkedHashSet<>();
    }

    public Visitation(int id, LocalDate date, String comments) {
        this();
        this.id = id;
        this.setDate(date);
        this.setComments(comments);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == LocalDate.parse(" ")) {
            throw new IllegalArgumentException("field is empty");
        }

        this.date = date;
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
