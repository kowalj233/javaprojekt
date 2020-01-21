package pl.project.pk.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "BENEFITS")
public class Benefit implements BaseModel {

    public Benefit() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "employee_id", foreign = true, foreignAutoRefresh = true, foreignAutoCreate = true, canBeNull = false)
    private Employee employee;

    @DatabaseField(columnName = "category", canBeNull = false)
    private String category;

    @DatabaseField(columnName = "amount", canBeNull = false)
    private Long amount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
