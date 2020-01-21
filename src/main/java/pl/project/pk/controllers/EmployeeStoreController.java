package pl.project.pk.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.mapper.EmployeeMapper;
import pl.project.pk.models.EmployeeModel;

public class EmployeeStoreController {

    @FXML
    private TableView<EmployeeMapper> employeeTableView;

    @FXML
    private TableColumn<EmployeeMapper, String> firstNameColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> lastNameColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> addressColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> netincomeColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> emailColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> phoneColumn;

    @FXML
    private TableColumn<EmployeeMapper, String> benefitColumn;

    private EmployeeModel employeeModel;


    public void initialize(){
        this.employeeModel = new EmployeeModel();

        try {
            this.employeeModel.init();

        } catch (ApplicationException e) {
            e.printStackTrace();
            //TODO klasa z wlasnymi wyjatkami
        }

        bindingsTableView();
    }

    private void bindingsTableView() {
        this.employeeTableView.setItems(this.employeeModel.getEmployeeMapperObservableList());

        this.firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        this.lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        this.addressColumn.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        this.netincomeColumn.setCellValueFactory(cellData -> cellData.getValue().netincomeProperty());
        this.emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        this.phoneColumn.setCellValueFactory(cellData -> cellData.getValue().phoneProperty());
        this.benefitColumn.setCellValueFactory(cellData -> cellData.getValue().usedBenefitProperty());

    }

}
