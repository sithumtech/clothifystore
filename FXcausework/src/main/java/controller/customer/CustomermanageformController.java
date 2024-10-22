package controller.customer;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import model.Customer;
import service.ServiceFactory;
import util.ServiceType;

public class CustomermanageformController {

    @FXML
    private JFXTextField cusemail2;

    @FXML
    private JFXTextField cusid2;

    @FXML
    private JFXTextField cusname2;

    @FXML
    private JFXTextField cusphone2;

//    CustomerService service=CustomerController.getInstance();
service.custom.CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @FXML
    void addCustomerbtnOnAction(ActionEvent event) {

        Customer customer=new Customer(
                cusid2.getText(),
                cusname2.getText(),
                cusphone2.getText(),
                cusemail2.getText()

        );
        if(customerService.addCustomer(customer)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Added!").show();


        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }

    }

    public void deleteCustomerbtnOnAction(ActionEvent actionEvent) {
        String id = cusid2.getText();
        if (customerService.deleteCustomer(id)){
            new Alert(Alert.AlertType.INFORMATION,"Customer Deleted").show();
        }else {
            new Alert(Alert.AlertType.ERROR).show();
        }
    }
}
