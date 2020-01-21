package pl.project.pk.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.mapper.EmployeeMapper;
import pl.project.pk.mapper.BenefitMapper;
import pl.project.pk.models.BenefitModel;
import pl.project.pk.utils.ModalUtils;

public class BenefitStoreController {

    @FXML
    public TableView<BenefitMapper> benefitTableView;

    @FXML
    public TableColumn<BenefitMapper, EmployeeMapper> firstNameEmployee;

    @FXML
    public TableColumn<BenefitMapper, String> benefitCategory;

    @FXML
    public TableColumn<BenefitMapper, String> benefitAmout;

    private BenefitModel benefitModel;

    @FXML
    void initialize(){
        this.benefitModel = new BenefitModel();
        try {
            this.benefitModel.initStore();

        } catch (ApplicationException e) {
            System.out.println("dasdaads");
            ModalUtils.modalError(e.getMessage());
        }

        this.bindingToTable();
    }

    private void bindingToTable() {
        this.benefitTableView.setItems(this.benefitModel.getBenefitMapperObservableList());
        this.firstNameEmployee.setCellValueFactory(cellData -> cellData.getValue().employeeMapperProperty());
        this.benefitCategory.setCellValueFactory(cellData -> cellData.getValue().categoryProperty());
        this.benefitAmout.setCellValueFactory(cellData -> cellData.getValue().amountProperty());
    }
}
