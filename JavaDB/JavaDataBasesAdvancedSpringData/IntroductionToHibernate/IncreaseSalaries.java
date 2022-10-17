package IntroductionToHibernate;

import IntroductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery("SELECT e FROM Employee e" +
                        " WHERE e.department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')",
                        Employee.class).getResultList();

        for (Employee employee : resultList) {
            BigDecimal salary = employee.getSalary();

            BigDecimal increaseSalary = salary.multiply(BigDecimal.valueOf(1.12));

            employee.setSalary(increaseSalary);

            entityManager.persist(employee);

            System.out.printf("%s %s ($%.2f)%n", employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
