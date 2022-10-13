package IntroductionToHibernate;

import IntroductionToHibernate.entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Address> resultList = entityManager.createQuery("SELECT a FROM Address AS a" +
                        " ORDER BY a.employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : resultList) {
            System.out.printf("%s, %s - %d employees%n",
                    address.getText(),
                            address.getTown() == null
                            ? "null"
                            : address.getTown().getName(),
                    address.getEmployees().size());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
