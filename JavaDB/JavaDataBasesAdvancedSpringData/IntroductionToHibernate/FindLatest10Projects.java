package IntroductionToHibernate;

import IntroductionToHibernate.entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FindLatest10Projects {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Project> resultList = entityManager.createQuery("SELECT p FROM Project as p" +
                        " WHERE p.endDate is NULL" +
                        " ORDER BY p.name ASC", Project.class)
                .setMaxResults(10).getResultList();

//        Project name: All-Purpose Bike Stand
//        Project Description: Research, design and development of …
//        Project Start Date:2005-09-01 00:00:00.0
//        Project End Date: null

        for (Project project : resultList) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.s");
            LocalDateTime time = LocalDateTime.parse(project.getStartDate().toString());
            LocalDateTime colonTime = LocalDateTime.of(time.getYear(), time.getMonth(), time.getDayOfMonth(), time.getHour(), time.getMinute(), time.getSecond());

            String formatTime = formatter.format(ZonedDateTime.of(colonTime, ZoneId.of("UTC-3")));

            System.out.printf("Project name: %s%n", project.getName());
            System.out.printf("Project Description: %s%n", project.getDescription());
            System.out.printf("Project Start Date:%s%n", formatTime);
            System.out.println("Project End Date: null");
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
