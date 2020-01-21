package pl.project.pk.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.database.dao.EmployeeDao;
import pl.project.pk.database.dbutils.DbManager;
import pl.project.pk.database.models.BaseModel;
import pl.project.pk.database.models.Employee;
import pl.project.pk.mapper.EmployeeMapper;
import pl.project.pk.models.EmployeeModel;
import pl.project.pk.models.BenefitModel;
import pl.project.pk.utils.FxmlUtils;
import pl.project.pk.utils.ModalUtils;
import pl.project.pk.utils.converters.ConventerEmployee;
import pl.project.pk.utils.factory.BenefitFactoryMethod;
import pl.project.pk.utils.benefit.BenefitBase;

import java.util.ResourceBundle;

public class BenefitCreateController {

    @FXML
    public ComboBox <EmployeeMapper> employeeList;

    @FXML
    public TextField typeMutual;

    @FXML
    public TextField amount;

    @FXML
    public TextField employeeNetIncome;

    @FXML
    public TextField employeeUsedBenefit;

    @FXML
    public Button saveButton;

    private EmployeeModel employeeModel;
    private BenefitModel benefitModel;

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    @FXML
    private void initialize() throws ApplicationException {
        this.employeeModel = new EmployeeModel();
        this.benefitModel = new BenefitModel();
        try {
            this.employeeModel.init();
            this.benefitModel.init();
        } catch (ApplicationException e) {
            e.printStackTrace();
        };

        this.employeeList.setItems(employeeModel.getEmployeeMapperObservableList());
    }


    public void saveButton(ActionEvent actionEvent) throws ApplicationException {
        if (this.isValid()) {
            this.benefitModel.saveBenefitInDataBase(
                    this.employeeList.getSelectionModel().getSelectedItem(),
                    this.typeMutual.getText(),
                    this.amount.getText()
            );

            Integer amountMutal = 0;
            Integer amountBenefit = 0;

            //zapisuję do bazy

            if (this.employeeUsedBenefit.getText().length() != 0 ){
                amountBenefit = Integer.parseInt(this.employeeUsedBenefit.getText());
            }

            if (this.amount.getText().length() != 0) {
                amountMutal = Integer.parseInt(this.amount.getText());
            }

            Integer diff = 0;
            diff =amountBenefit - amountMutal;

            this.employeeList.getSelectionModel().getSelectedItem().setUsedBenefit(String.valueOf(diff));

            Integer idToUpdate = this.employeeList.getSelectionModel().getSelectedItem().getId();

            EmployeeDao employeeDao = new EmployeeDao(DbManager.getConnectionSource());



            EmployeeMapper emp = this.employeeList.getSelectionModel().getSelectedItem();
            Employee employee = ConventerEmployee.convertToEmployee(emp);
            employee.setId(idToUpdate);
            //employeeDao.deteleById(Employee.class,idToUpdate);
            employeeDao.updateId(employee, idToUpdate);
            employeeDao.update(employee);
            DbManager.closeConnectionDB();


        }
        this.clearAll();
    }

    private void clearAll() {
        this.employeeList.getSelectionModel().clearSelection();
        this.typeMutual.clear();
        this.amount.clear();
        this.employeeNetIncome.clear();
    }

    public void onActionComboBox(ActionEvent actionEvent) {
        if(this.employeeList.getSelectionModel().getSelectedItem() != null){
            this.employeeModel.setEmployeeMapperObjectProperty(this.employeeList.getSelectionModel().getSelectedItem());
            Integer netIncome = Integer.parseInt(this.employeeList.getSelectionModel().getSelectedItem().getNetincome());
            BenefitBase benefit = null;

            Integer usedBenefit = Integer.parseInt(this.employeeList.getSelectionModel().getSelectedItem().getUsedBenefit());

        /* FactoryMethod, Decorator desing pattern */
            BenefitFactoryMethod benefitFactoryMethod = new BenefitFactoryMethod();

            if(netIncome > 0 && netIncome <= 2500) {
                benefit =  benefitFactoryMethod.makeFirstGroup();
            }

            if(netIncome > 2500 && netIncome <= 5000) {
                benefit = benefitFactoryMethod.makeSecondGroup();
            }

            if(netIncome > 5000) {
                benefit = benefitFactoryMethod.makeThirdGroup();
            }

            this.employeeNetIncome.setText( Integer.toString(netIncome) );
            this.typeMutual.setText(benefit.benefitType());
            this.employeeUsedBenefit.setText(Integer.toString(usedBenefit));
        }
    }

    private boolean isValid(){
        Integer employeeAmout = 0;
        Integer amountMutal = 0;
        Integer amountBenefit = 0;

        if (this.employeeNetIncome.getText().length() != 0 ){
            employeeAmout = Integer.parseInt(this.employeeNetIncome.getText());
        }

        if (this.employeeUsedBenefit.getText().length() != 0 ){
            amountBenefit = Integer.parseInt(this.employeeUsedBenefit.getText());
        }

        if (this.amount.getText().length() != 0) {
            amountMutal = Integer.parseInt(this.amount.getText());
        }

        if (employeeAmout == 0){
            ModalUtils.modalError(bundle.getString("error.employee.none"));
            return false;
        }

        if (amountMutal == 0){
            ModalUtils.modalError(bundle.getString("error.amountNull"));
            return false;
        }

        //TODO

        if (amountMutal > amountBenefit){
            ModalUtils.modalError(bundle.getString("error.amountBad"));
            return false;
        }

        return true;
    }
}
