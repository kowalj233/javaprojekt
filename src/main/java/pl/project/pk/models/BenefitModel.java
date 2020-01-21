package pl.project.pk.models;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.database.dao.EmployeeDao;
import pl.project.pk.database.dao.BenefitDao;
import pl.project.pk.database.dbutils.DbManager;
import pl.project.pk.database.models.Employee;
import pl.project.pk.database.models.Benefit;
import pl.project.pk.mapper.EmployeeMapper;
import pl.project.pk.mapper.BenefitMapper;
import pl.project.pk.utils.converters.ConventerEmployee;
import pl.project.pk.utils.converters.ConventerBenefit;

import java.util.List;

public class BenefitModel {

    private ObservableList<EmployeeMapper> employeeMapperObservableList = FXCollections.observableArrayList();
    private ObservableList<BenefitMapper> benefitMapperObservableList = FXCollections.observableArrayList();

    public void initStore() throws ApplicationException {
        BenefitDao benefitDao = new BenefitDao(DbManager.getConnectionSource());
        List<Benefit> benefits = benefitDao.queryForAll(Benefit.class);
        benefits.forEach(benefit -> {
            BenefitMapper benefitMapper = ConventerBenefit.converToBenefitMapper(benefit);
            this.benefitMapperObservableList.add(benefitMapper);
        });

        DbManager.closeConnectionDB();
    }

    public void init() throws ApplicationException {
        EmployeeDao employeeDao = new EmployeeDao(DbManager.getConnectionSource());
        List<Employee> employees = employeeDao.queryForAll(Employee.class);
        BenefitDao benefitDao = new BenefitDao(DbManager.getConnectionSource());

        this.employeeMapperObservableList.clear();
        employees.forEach(employee -> {
            EmployeeMapper employeeMapper = ConventerEmployee.convertToEmployeeMapper(employee);
            this.employeeMapperObservableList.add(employeeMapper);
        });
        DbManager.closeConnectionDB();
    }


    public ObservableList<EmployeeMapper> getEmployeeMapperObservableList() {
        return employeeMapperObservableList;
    }

    public void setEmployeeMapperObservableList(ObservableList<EmployeeMapper> employeeMapperObservableList) {
        this.employeeMapperObservableList = employeeMapperObservableList;
    }

    public void saveBenefitInDataBase(EmployeeMapper employeeMapper, String typeMutal, String amout) throws ApplicationException {
        Long amoutLong = Long.parseLong(amout);

        EmployeeDao employeeDao = new EmployeeDao(DbManager.getConnectionSource());
        Employee employee = employeeDao.findById(Employee.class, employeeMapper.getId());

        BenefitDao benefitDao = new BenefitDao(DbManager.getConnectionSource());
        Benefit benefit = new Benefit();

        benefit.setEmployee(employee);
        benefit.setCategory(typeMutal);
        benefit.setAmount(amoutLong);

        benefitDao.createOrUpdate(benefit);
        DbManager.closeConnectionDB();

    }

    public ObservableList<BenefitMapper> getBenefitMapperObservableList() {
        return benefitMapperObservableList;
    }

    public void setBenefitMapperObservableList(ObservableList<BenefitMapper> benefitMapperObservableList) {
        this.benefitMapperObservableList = benefitMapperObservableList;
    }
}
