package IntroductionToHibernate;

import IntroductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        String input = scanner.nextLine();

        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e WHERE e.firstName LIKE :input" +
                        " ORDER BY e.id",
                        Employee.class).setParameter("input", input + "%").getResultList();

        for (Employee employee : resultList) {
            System.out.printf("%s %s - %s - ($%.2f)%n",
                    employee.getFirstName(), employee.getLastName(), employee.getJobTitle(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
