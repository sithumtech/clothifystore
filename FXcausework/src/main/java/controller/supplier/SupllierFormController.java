package controller.supplier;

import com.jfoenix.controls.JFXTextField;
import controller.employee.EmployeeController;
import controller.employee.EmployeeService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Employee;
import model.Supplier;

public class SupllierFormController {

    @FXML
    private JFXTextField supcomapny2;

    @FXML
    private JFXTextField supemail2;

    @FXML
    private JFXTextField supid2;

    @FXML
    private JFXTextField supitem2;

    @FXML
    private JFXTextField supname2;
    SuplierService service= SupplierController.getInstance();

    @FXML
    void addsupplierbtnOnAction(ActionEvent event) {
        Supplier supplier=new Supplier(
                supid2.getText(),
                supname2.getText(),
                supcomapny2.getText(),
                supitem2.getText(),
                supemail2.getText()

        );
        if(service.addSupplier(supplier)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Added!").show();


        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

    @FXML
    void deletesupplierbtnOnAction(ActionEvent event) {
        String id = supid2.getText();
        if (service.deleteSupplier(id)){
            new Alert(Alert.AlertType.INFORMATION,"Supplier Deleted").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

}
