package IntroductionToHibernate;

import IntroductionToHibernate.entities.Employee;
import IntroductionToHibernate.entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class GetEmployeeWithProject {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        int id = Integer.parseInt(scanner.nextLine());

        List<Employee> epm = entityManager.createQuery("SELECT e FROM Employee AS e" +
                        " WHERE e.id = :e_id", Employee.class)
                .setParameter("e_id", id)
                .getResultList();

        for (Employee employee : epm) {
            System.out.printf("%s %s - %s%n", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());

            List<String> projects = new ArrayList<>();

            for (Project project : employee.getProjects()) {
                projects.add(project.getName());
            }

            Collections.sort(projects);

            projects.forEach(System.out::println);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
