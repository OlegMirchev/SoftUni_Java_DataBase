package IntroductionToHibernate;

import IntroductionToHibernate.entities.Department;
import IntroductionToHibernate.entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_Name");
        EntityManager entityManager = emf.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> resultList = entityManager.createQuery(
                "SELECT e FROM Employee e GROUP BY e.department.id", Employee.class).getResultList();

        for (Employee employee : resultList) {
            Department department = employee.getDepartment();
            List<BigDecimal> listSalary = department.getEmployees().stream().map(Employee::getSalary).collect(Collectors.toList());
            BigDecimal maxSalaryOfDepartment = Collections.max(listSalary);

            if (maxSalaryOfDepartment.intValue() >= 30000 && maxSalaryOfDepartment.intValue() <= 70000) {
                continue;
            }

            System.out.printf("%s %.2f%n", department.getName(), maxSalaryOfDepartment);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
