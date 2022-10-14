package IntroductionToHibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        String[] input = scanner.nextLine().split("\\s+");

        try {
            entityManager.createQuery("SELECT concat(e.firstName, ' ', e.lastName) FROM Employee AS e WHERE " +
                                    " e.firstName = :f_name" +
                                    " AND e.lastName = :l_name", String.class)
                    .setParameter("f_name", input[0])
                    .setParameter("l_name", input[1])
                    .getSingleResult();

            System.out.println("Yes");
        } catch (NoResultException e) {
            System.out.println("No");

        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
