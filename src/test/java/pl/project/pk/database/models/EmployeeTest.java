package pl.project.pk.database.models;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;


public class EmployeeTest {
    @Test
    public void setFirstName() throws Exception {
        String firstName = "Krzysiek";

        Employee employee = new Employee();
        employee.setFirstName(firstName);

        Assert.assertEquals(firstName, employee.getFirstName());

    }

    @Test
    public void setLastname() throws Exception {
        String lastName = "Kasprzak";

        Employee employee = new Employee();
        employee.setLastname(lastName);

        Assert.assertEquals(lastName, employee.getLastname());
    }

}