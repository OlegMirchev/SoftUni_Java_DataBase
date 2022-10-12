package IntroductionToHibernate;

import IntroductionToHibernate.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        String lastName = scanner.nextLine();

        Address address = new Address();
        address.setText("Vitoshka 15");
        entityManager.persist(address);


        entityManager.createQuery("UPDATE Employee AS e" +
                        " SET e.address = :address_text" +
                        " WHERE e.lastName = :l_name")
                .setParameter("l_name", lastName)
                .setParameter("address_text", address)
                .executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
