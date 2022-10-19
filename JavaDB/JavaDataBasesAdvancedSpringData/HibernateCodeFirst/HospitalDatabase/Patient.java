package HibernateCodeFirst.HospitalDatabase;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String address;

    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate birthdayOfDate;

    private String picture;

    @Column(name = "medical_insurance", nullable = false)
    private boolean isMedicalInsurance;

    @OneToMany(mappedBy = "patient", targetEntity = GP.class)
    private Set<GP> gp;

    public Patient() {
        this.gp = new LinkedHashSet<>();
    }

    public Patient(int id, String firstName, String lastName, String address, String email, LocalDate birthdayOfDate, String picture, boolean isMedicalInsurance) {
        this();
        this.id = id;
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setAddress(address);
        this.setEmail(email);
        this.setBirthdayOfDate(birthdayOfDate);
        this.setPicture(picture);
        this.isMedicalInsurance = isMedicalInsurance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.email = email;
    }

    public LocalDate getBirthdayOfDate() {
        return birthdayOfDate;
    }

    public void setBirthdayOfDate(LocalDate birthdayOfDate) {
        if (birthdayOfDate == LocalDate.parse(" ")) {
            throw new IllegalArgumentException("field is empty");
        }

        this.birthdayOfDate = birthdayOfDate;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        if (picture.trim().isEmpty()) {
            throw new IllegalArgumentException("field is empty");
        }

        this.picture = picture;
    }

    public boolean isMedicalInsurance() {
        return isMedicalInsurance;
    }

    public void setMedicalInsurance(boolean medicalInsurance) {
        isMedicalInsurance = medicalInsurance;
    }

    public Set<GP> getGp() {
        return gp;
    }

    public void setGp(Set<GP> gp) {
        this.gp = gp;
    }
}
