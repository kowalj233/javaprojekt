package pl.project.pk.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ToggleGroup;

public class TopMenuButtonsController {

    private static final String STORE_BENEFITS_FXML = "/fxml/store/BenefitDeclare.fxml";
    private static final String STORE_EMPLOYEES_FXML = "/fxml/store/Employees.fxml";
    private static final String STORE_STATISTICS_FXML = "/fxml/store/Statistics.fxml";
    private static final String ADD_EMPLOYEES_FXML = "/fxml/form/AddEmployee.fxml";
    private static final String ADD_BENEFITS_FXML = "/fxml/form/AddBenefits.fxml";

    private MainController mainController;

    @FXML
    private ToggleGroup toggleGroups;

    @FXML
    public void openBenefits() {
        mainController.setCenter(STORE_BENEFITS_FXML);
    }

    @FXML
    public void openEmployees() {
        mainController.setCenter(STORE_EMPLOYEES_FXML);
    }

    @FXML
    public void openStatistic() {
        mainController.setCenter(STORE_STATISTICS_FXML);
    }

    @FXML
    public void addEmployee(){
        this.resetToggleButtons();
        mainController.setCenter(ADD_EMPLOYEES_FXML);
    }

    @FXML
    public void addBenefit(ActionEvent actionEvent) {
        this.resetToggleButtons();
        mainController.setCenter(ADD_BENEFITS_FXML);
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    private void resetToggleButtons(){
        if(toggleGroups.getSelectedToggle() != null){
            toggleGroups.getSelectedToggle().setSelected(false);
        }
    }


}
