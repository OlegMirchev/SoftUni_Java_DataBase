package IntroductionToHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<String> resultList = entityManager.createQuery(
                        "SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000", String.class)
                .getResultList();

        for (String employee : resultList) {
            System.out.println(employee);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
