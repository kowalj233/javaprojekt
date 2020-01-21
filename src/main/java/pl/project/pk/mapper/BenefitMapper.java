package pl.project.pk.mapper;


import javafx.beans.property.*;

public class BenefitMapper {

    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty category = new SimpleStringProperty();
    private ObjectProperty<EmployeeMapper> employeeMapper = new SimpleObjectProperty<>();
    private StringProperty amount = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public EmployeeMapper getEmployeeMapper() {
        return employeeMapper.get();
    }

    public ObjectProperty<EmployeeMapper> employeeMapperProperty() {
        return employeeMapper;
    }

    public void setEmployeeMapper(EmployeeMapper employeeMapper) {
        this.employeeMapper.set(employeeMapper);
    }

    public String getAmount() {
        return amount.get();
    }

    public StringProperty amountProperty() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }
}
