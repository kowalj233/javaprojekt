package pl.project.pk.mapper;


import javafx.beans.property.*;

public class EmployeeMapper {

    private IntegerProperty id  = new SimpleIntegerProperty();
    private StringProperty firstName = new SimpleStringProperty();
    private StringProperty lastname = new SimpleStringProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty netincome = new SimpleStringProperty();
    private StringProperty email = new SimpleStringProperty();
    private StringProperty phone = new SimpleStringProperty();

    private StringProperty usedBenefit = new SimpleStringProperty();

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastname() {
        return lastname.get();
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname.set(lastname);
    }

    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getNetincome() {
        return netincome.get();
    }

    public StringProperty netincomeProperty() {
        return netincome;
    }

    public void setNetincome(String netincome) {
        this.netincome.set(netincome);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getPhone() {
        return phone.get();
    }

    public StringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getUsedBenefit() {
        return usedBenefit.get();
    }

    public StringProperty usedBenefitProperty() {
        return usedBenefit;
    }

    public void setUsedBenefit(String usedBenefit) {
        this.usedBenefit.set(usedBenefit);
    }

    @Override
    public String toString() {
        return firstName.getValue() + " " + lastname.getValue();
    }
}
