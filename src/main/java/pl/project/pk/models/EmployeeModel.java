package pl.project.pk.models;


import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.database.dao.EmployeeDao;
import pl.project.pk.database.dbutils.DbManager;
import pl.project.pk.database.models.Employee;
import pl.project.pk.mapper.EmployeeMapper;
import pl.project.pk.utils.converters.ConventerEmployee;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class EmployeeModel {

    public static final String FIELD_NAME_FIRST_NAME = "firstName";
    public static final String FIELD_NAME_LAST_NAME = "lastName";
    public static final String FIELD_NAME_ADDRESS = "address";
    public static final String FIELD_NAME_NET_INCOME = "netincome";
    public static final String FIELD_NAME_EMAIL = "email";
    public static final String FIELD_NAME_PHONE = "phone";
    public static final String FIELD_USED_BENEFIT ="usedbenefit";

    private ObjectProperty<EmployeeMapper> employeeMapperObjectProperty = new SimpleObjectProperty<>(new EmployeeMapper());
    private ObjectProperty<EmployeeMapper> employeeMapperObjectPropertyEdit = new SimpleObjectProperty<>(new EmployeeMapper());

    private ObservableList<EmployeeMapper> employeeMapperObservableList = FXCollections.observableArrayList();


    public void init() throws ApplicationException {
        EmployeeDao employeeDao = new EmployeeDao(DbManager.getConnectionSource());
        List<Employee> employeeList = employeeDao.queryForAll(Employee.class);

        this.employeeMapperObservableList.clear();
        employeeList.forEach(employee -> {
            EmployeeMapper employeeMapper = ConventerEmployee.convertToEmployeeMapper(employee);
            this.employeeMapperObservableList.add(employeeMapper);
        });
        DbManager.closeConnectionDB();
    }


    public void saveEmployeeInDataBase(Map<String, String> data ) throws ApplicationException {
        EmployeeDao employeeDao = new EmployeeDao(DbManager.getConnectionSource());
        Employee employee = new Employee();

        employee.setFirstName(data.get(FIELD_NAME_FIRST_NAME));
        employee.setLastname(data.get(FIELD_NAME_LAST_NAME));
        employee.setAddress(data.get(FIELD_NAME_ADDRESS));
        employee.setNetIncome(data.get(FIELD_NAME_NET_INCOME));
        employee.setEmail(data.get(FIELD_NAME_EMAIL));
        employee.setPhone(data.get(FIELD_NAME_PHONE));

        employee.setUsedBenefit(data.get(FIELD_USED_BENEFIT));

        employee.setCreatedAt(new Date());
        employee.setUpdatedAt(new Date());

        //Save employees
        employeeDao.createOrUpdate(employee);
        DbManager.closeConnectionDB();
        init();
    }

    public EmployeeMapper getEmployeeMapperObjectProperty() {
        return employeeMapperObjectProperty.get();
    }

    public ObjectProperty<EmployeeMapper> employeeMapperObjectPropertyProperty() {
        return employeeMapperObjectProperty;
    }

    public void setEmployeeMapperObjectProperty(EmployeeMapper employeeMapperObjectProperty) {
        this.employeeMapperObjectProperty.set(employeeMapperObjectProperty);
    }

    public EmployeeMapper getEmployeeMapperObjectPropertyEdit() {
        return employeeMapperObjectPropertyEdit.get();
    }

    public ObjectProperty<EmployeeMapper> employeeMapperObjectPropertyEditProperty() {
        return employeeMapperObjectPropertyEdit;
    }

    public void setEmployeeMapperObjectPropertyEdit(EmployeeMapper employeeMapperObjectPropertyEdit) {
        this.employeeMapperObjectPropertyEdit.set(employeeMapperObjectPropertyEdit);
    }

    public ObservableList<EmployeeMapper> getEmployeeMapperObservableList() {
        return employeeMapperObservableList;
    }

    public void setEmployeeMapperObservableList(ObservableList<EmployeeMapper> employeeMapperObservableList) {
        this.employeeMapperObservableList = employeeMapperObservableList;
    }
}
