package IntroductionToHibernate;

import IntroductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery(
                        "SELECT e FROM Employee AS e WHERE e.department.name LIKE 'Research and Development'" +
                                " ORDER BY e.salary ASC, e.id ASC", Employee.class)
                .getResultList();

        for (Employee employee : resultList) {
            System.out.printf("%s %s from %s - $%.2f%n",
                    employee.getFirstName(), employee.getLastName(), employee.getDepartment().getName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
