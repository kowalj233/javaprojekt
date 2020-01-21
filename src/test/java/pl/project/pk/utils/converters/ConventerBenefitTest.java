package pl.project.pk.utils.converters;

import org.junit.Assert;
import org.junit.Test;
import pl.project.pk.database.models.Employee;
import pl.project.pk.database.models.Benefit;
import pl.project.pk.mapper.BenefitMapper;

public class ConventerBenefitTest {
    @Test
    public void converToBenefitMapper() throws Exception {

        Employee employee = new Employee();
        employee.setId(1);
        employee.setFirstName("Test");
        employee.setLastname("Test 2");
        employee.setPhone("32131231");
        employee.setNetIncome("20000");

        Benefit benefit = new Benefit();
        benefit.setId(1);
        benefit.setCategory("ThirdGroup");
        benefit.setEmployee(employee);
        benefit.setAmount((long) 2000);

        BenefitMapper benefitMapperTest = new BenefitMapper();
        benefitMapperTest.setId(1);
        benefitMapperTest.setCategory("ThirdGroup");
        benefitMapperTest.setEmployeeMapper(ConventerEmployee.convertToEmployeeMapper(employee));
        benefitMapperTest.setAmount("2000");

        BenefitMapper benefitMapper = ConventerBenefit.converToBenefitMapper(benefit);

        Assert.assertEquals(benefitMapper.getId(), benefitMapperTest.getId());

    }

}