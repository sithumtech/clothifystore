package controller.employee;

import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.customer.CustomerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Customer;
import model.Employee;

public class EmployeeManagementController {

    @FXML
    private JFXTextField emId2;

    @FXML
    private JFXTextField emcomapny2;

    @FXML
    private JFXTextField ememail2;

    @FXML
    private JFXTextField emname2;

    @FXML
    private JFXTextField empassword;

    @FXML
    private JFXTextField emsalary2;

    EmployeeService service= EmployeeController.getInstance();


    @FXML
    void addemployeebtnOnAction(ActionEvent event) {
        Employee employee=new Employee(
                emId2.getText(),
                emname2.getText(),
                ememail2.getText(),
                Double.parseDouble(emsalary2.getText()),
                emcomapny2.getText(),
                empassword.getText()

        );
        if(service.addEmployee(employee)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Added!").show();


        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

    @FXML
    void deleteemployeebtnOnAction(ActionEvent event) {
        String id = emId2.getText();
        if (service.deleteEmployee(id)){
            new Alert(Alert.AlertType.INFORMATION,"Employee Deleted").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

}
