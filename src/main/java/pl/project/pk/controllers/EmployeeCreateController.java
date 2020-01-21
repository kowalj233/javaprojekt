package pl.project.pk.controllers;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.omg.CORBA.portable.ApplicationException;
import pl.project.pk.models.EmployeeModel;
import pl.project.pk.utils.FxmlUtils;
import pl.project.pk.utils.ModalUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class EmployeeCreateController {

    /* widok dodawania nowego employeea */
    @FXML
    public TextField firstName;

    @FXML
    public TextField lastName;

    @FXML
    public TextField address;

    @FXML
    public TextField netincome;

    @FXML
    public TextField email;

    @FXML
    public TextField phone;

    @FXML
    public Button saveEmployeeButton;

    private EmployeeModel employeeModel;

    private static ResourceBundle bundle = FxmlUtils.getResourceBundle();

    @FXML
    public void initialize() throws ApplicationException {
        this.employeeModel = new EmployeeModel();
        this.employeeModel.init();
        this.initBindings();

        netincome.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = netincome.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    if(!(ch >= '0' && ch <= '9' )){

                        netincome.setText(netincome.getText().substring(0,netincome.getText().length()-1));
                    }
                }
            }
        });

        phone.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = phone.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    if(!(ch >= '0' && ch <= '9' )){

                        phone.setText(phone.getText().substring(0,phone.getText().length()-1));
                    }
                }
            }
        });

        firstName.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = firstName.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    if(!((ch >= 'a' && ch <= 'z' ) || (ch >= 'A' && ch <= 'Z' ))){

                        firstName.setText(firstName.getText().substring(0,firstName.getText().length()-1));
                    }
                }
            }
        });

        lastName.lengthProperty().addListener(new ChangeListener<Number>(){

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                if(newValue.intValue() > oldValue.intValue()){
                    char ch = lastName.getText().charAt(oldValue.intValue());
                    System.out.println("Length:"+ oldValue+"  "+ newValue +" "+ch);

                    if(!((ch >= 'a' && ch <= 'z' ) || (ch >= 'A' && ch <= 'Z' ))){

                        lastName.setText(lastName.getText().substring(0,lastName.getText().length()-1));
                    }
                }
            }
        });
    }

    private void initBindings() {
        this.saveEmployeeButton.disableProperty().bind(firstName.textProperty().isEmpty());
        this.saveEmployeeButton.disableProperty().bind(lastName.textProperty().isEmpty());
        this.saveEmployeeButton.disableProperty().bind(address.textProperty().isEmpty());
        this.saveEmployeeButton.disableProperty().bind(netincome.textProperty().isEmpty());
        this.saveEmployeeButton.disableProperty().bind(email.textProperty().isEmpty());
        this.saveEmployeeButton.disableProperty().bind(phone.textProperty().isEmpty());
    }

    private void clearAllTextField(){
        firstName.clear();
        lastName.clear();
        address.clear();
        netincome.clear();
        email.clear();
        phone.clear();
    }




    //TODO zrobić walidację danych
    public void saveEmployee(ActionEvent actionEvent) throws ApplicationException {
        Map<String, String> dataForm = new HashMap<String, String>();
        dataForm.put(EmployeeModel.FIELD_NAME_FIRST_NAME,firstName.getText());
        dataForm.put(EmployeeModel.FIELD_NAME_LAST_NAME,lastName.getText());
        dataForm.put(EmployeeModel.FIELD_NAME_ADDRESS,address.getText());
        dataForm.put(EmployeeModel.FIELD_NAME_NET_INCOME, netincome.getText());
        dataForm.put(EmployeeModel.FIELD_NAME_EMAIL,email.getText());
        dataForm.put(EmployeeModel.FIELD_NAME_PHONE,phone.getText());
        dataForm.put(EmployeeModel.FIELD_USED_BENEFIT,getBenefitAmount(netincome.getText()) );

        employeeModel.saveEmployeeInDataBase(dataForm);
        this.clearAllTextField();
    }

    private String getBenefitAmount(String netincomeS) {
        Integer netIncome = 0;

        try{
            netIncome = Integer.parseInt(netincomeS);

            if (netIncome <= 2500)
                return "300";

            if (netIncome > 2500 && netIncome <= 5000)
                return "250";

            if (netIncome >  5000)
                return "200";
        }
        catch (Exception e)
        {
            return "0";
        }




        return "0";
    }



    private boolean isValid(){

        Integer netincome = 0;

        if (this.firstName.getText().length() == 0 ){
            try {
                netincome = Integer.parseInt(this.netincome.getText());
            }
            catch (Exception e)
            {
                ModalUtils.modalError(bundle.getString("error.amountBad"));
                return false;
            }
        }

        if (netincome <= 0){
            ModalUtils.modalError(bundle.getString("error.amountNull"));
            return false;
        }


        if (this.firstName.getText().length() == 0 ){
            ModalUtils.modalError(bundle.getString("error.employee.none"));
            return false;
        }

        if (this.netincome.getText().length() != 0) {
            ModalUtils.modalError(bundle.getString("error.employee.none"));
            return false;
        }


        if (netincome <= 0){
            ModalUtils.modalError(bundle.getString("error.amountNull"));
            return false;
        }


        return true;
    }
}
