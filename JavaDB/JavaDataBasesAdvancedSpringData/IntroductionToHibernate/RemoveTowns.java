package IntroductionToHibernate;

import IntroductionToHibernate.entities.Address;
import IntroductionToHibernate.entities.Employee;
import IntroductionToHibernate.entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        String city = scanner.nextLine();

        List<Address> resultList = entityManager.createQuery("SELECT a FROM Address a WHERE a.town.name LIKE :c_name",
                Address.class).setParameter("c_name", city).getResultList();

        int addressCount = 0;

        for (Address address : resultList) {
            addressCount = resultList.size();

            Town town = address.getTown();
            town = entityManager.find(Town.class, town.getId());

            for (Employee employee : address.getEmployees()) {
                employee = entityManager.find(Employee.class, employee.getId());

                if (employee != null) {
                    entityManager.remove(employee);
                }
            }
            entityManager.remove(address);

            if (town != null) {
                entityManager.remove(town);
            }

        }

        if (addressCount > 1) {
            System.out.printf("%d addresses in %s deleted", addressCount, city);
        } else if (addressCount == 1) {
            System.out.printf("%d address in %s deleted", addressCount, city);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
