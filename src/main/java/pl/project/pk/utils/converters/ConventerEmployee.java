package pl.project.pk.utils.converters;


import pl.project.pk.database.models.Employee;
import pl.project.pk.mapper.EmployeeMapper;

public class ConventerEmployee {

    public static Employee convertToEmployee(EmployeeMapper employeeMapper){
        Employee employee = new Employee();
        employee.setId(employee.getId());
        employee.setFirstName(employeeMapper.getFirstName());
        employee.setLastname(employeeMapper.getLastname());
        employee.setAddress(employeeMapper.getAddress());
        employee.setNetIncome(employeeMapper.getNetincome());
        employee.setEmail(employeeMapper.getEmail());
        employee.setPhone(employeeMapper.getPhone());

        employee.setUsedBenefit(employeeMapper.getUsedBenefit());

        return employee;
    }

    public static EmployeeMapper convertToEmployeeMapper(Employee employee){
        EmployeeMapper employeeMapper = new EmployeeMapper();
        employeeMapper.setId(employee.getId());
        employeeMapper.setFirstName(employee.getFirstName());
        employeeMapper.setLastname(employee.getLastname());
        employeeMapper.setAddress(employee.getAddress());
        employeeMapper.setNetincome(employee.getNetIncome());
        employeeMapper.setEmail(employee.getEmail());
        employeeMapper.setPhone(employee.getPhone());

        employeeMapper.setUsedBenefit(employee.getUsedBenefit());

        return employeeMapper;
    }
}
