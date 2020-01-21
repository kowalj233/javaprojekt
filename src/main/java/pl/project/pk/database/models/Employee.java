package pl.project.pk.database.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.util.Date;

@DatabaseTable(tableName = "EMPLOYEES")
public class Employee implements BaseModel {

    public Employee() {

    }

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "first_name", canBeNull = false)
    private String firstName;

    @DatabaseField(columnName = "last_name", canBeNull = false)
    private String lastname;

    @DatabaseField(columnName = "address", canBeNull = false)
    private String address;

    @DatabaseField(columnName = "netincome", canBeNull = false)
    private String netincome;

    @DatabaseField(columnName = "email", canBeNull = false)
    private String email;

    @DatabaseField(columnName = "phone", canBeNull = false)
    private String phone;

    @DatabaseField(columnName = "created_at")
    private Date createdAt;

    @DatabaseField(columnName = "updated_at")
    private Date updatedAt;

    @DatabaseField(columnName = "used_benefit")
    private String usedBenefit;


    /* getter & setter */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {return address; }

    public void setAddress(String address) { this.address = address;}

    public String getNetIncome() {
        return netincome;
    }

    public void setNetIncome(String netincome) {
        this.netincome = netincome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }


    public String getUsedBenefit() {
        return usedBenefit;
    }

    public void setUsedBenefit(String usedBenefit) {
        this.usedBenefit = usedBenefit;
    }
}
