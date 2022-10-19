package HibernateCodeFirst.HospitalDatabase;

import javax.persistence.*;

@Entity(name = "gp")
public class GP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "visitation_id", referencedColumnName = "id")
    private Visitation visitation;

    @ManyToOne
    @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    private Diagnose diagnose;

    @ManyToOne
    @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    private Medicament medicament;

    public GP() {

    }

    public GP(int id, Patient patient, Visitation visitation, Diagnose diagnose, Medicament medicament) {
        this.id = id;
        this.patient = patient;
        this.visitation = visitation;
        this.diagnose = diagnose;
        this.medicament = medicament;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Visitation getVisitation() {
        return visitation;
    }

    public void setVisitation(Visitation visitation) {
        this.visitation = visitation;
    }

    public Diagnose getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(Diagnose diagnose) {
        this.diagnose = diagnose;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }
}
