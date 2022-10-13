package IntroductionToHibernate;

import IntroductionToHibernate.entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class ChangeCasing {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        Query query = entityManager.createQuery("SELECT t FROM Town AS t", Town.class);
        List<Town> resultList = query.getResultList();

        for (Town town : resultList) {
            if (town.getName().length() > 5) {
                String upperTown = town.getName().toUpperCase();
                town.setName(upperTown);

                entityManager.persist(town);
            }
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
