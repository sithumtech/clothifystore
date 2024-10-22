package controller.product;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import controller.customer.CustomerController;
import controller.employee.EmployeeController;
import controller.order.OrderController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.CartTm;
import model.Order;
import model.OrderDetail;
import model.Product;
import service.ServiceFactory;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;



public class PlaceOrderFormcontroller implements Initializable {



    public JFXTextField productidtxt;
    public JFXTextField productnametxt;
    public JFXTextField productpricetxt;
    public JFXTextField productquantitytxt;
    public JFXTextField productsizetxt;
    public TableView table2;
    public TableColumn colid2;
    public TableColumn coname2;
    public TableColumn colunitprice2;
    public TableColumn coltotal2;
    public TableColumn colquantity2;
    public Label lblNetTotal;
    public JFXComboBox <String> cmbemployeeids;
    public JFXComboBox <String> cmbcustomerids;
    public Label lblDate;
    public TextField lblOrderIdtxt;
    public Label lblTime;
    @FXML
    private TableColumn<?, ?> pid;

    @FXML
    public TableColumn<?, ?> pnamee;

    @FXML
    private TableColumn<?, ?> pprize;

    @FXML
    private TableColumn<?, ?> pquntity;

    @FXML
    private TableView<Product> producttbl;

    @FXML
    private TableColumn<?, ?> psize;

    ProductService service=ProductController.getInstance();
    service.custom.CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDateAndTime();
          loadEmployeeId();
           loadCustomerId();


    }

    private void loadDateAndTime(){

        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");

        lblDate.setText(f.format(date));
//--------------------------------------------------------------

        Timeline timeline= new Timeline(new KeyFrame(Duration.ZERO, e->{
            LocalTime now = LocalTime.now();
            lblTime.setText(now.getHour()+":"+ now.getMinute()+":"+now.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }



    @FXML
    void btnLadiesOnAction(ActionEvent event) {

        pid.setCellValueFactory(new PropertyValueFactory<>("productId"));
        pnamee.setCellValueFactory(new PropertyValueFactory<>("name"));
        pprize.setCellValueFactory(new PropertyValueFactory<>("price"));
        pquntity.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        psize.setCellValueFactory(new PropertyValueFactory<>("size"));

        producttbl.setItems(service.getAllladiesProducts());

        producttbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

            if(newVal != null){
                addValueTpText(newVal);
            }
        });



    }
    private void addValueTpText(Product newVal) {
        productidtxt.setText(newVal.getProductId());
        productnametxt.setText(newVal.getName());
        productsizetxt.setText(newVal.getSize());
        productpricetxt.setText(String.valueOf(newVal.getPrice()));
//        productquantitytxt.setText(String.valueOf(newVal.getQuntity()));


    }


    public void btnGentsOnAction(ActionEvent actionEvent) {
        pid.setCellValueFactory(new PropertyValueFactory<>("productId"));
        pnamee.setCellValueFactory(new PropertyValueFactory<>("name"));
        pprize.setCellValueFactory(new PropertyValueFactory<>("price"));
        pquntity.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        psize.setCellValueFactory(new PropertyValueFactory<>("size"));

        producttbl.setItems(service.getAllGentsProducts());

        producttbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

            if(newVal != null){
                addValueTpText(newVal);
            }
        });

    }

    public void btnKdsOnAction(ActionEvent actionEvent) {
        pid.setCellValueFactory(new PropertyValueFactory<>("productId"));
        pnamee.setCellValueFactory(new PropertyValueFactory<>("name"));
        pprize.setCellValueFactory(new PropertyValueFactory<>("price"));
        pquntity.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        psize.setCellValueFactory(new PropertyValueFactory<>("size"));

        producttbl.setItems(service.getAllKidsProducts());

        producttbl.getSelectionModel().selectedItemProperty().addListener((observableValue, oldVal, newVal) -> {

            if(newVal != null){
                addValueTpText(newVal);
            }
        });
    }

    ObservableList<CartTm> cartTms = FXCollections.observableArrayList();
    public void btnAddtocart(ActionEvent actionEvent) {

        colid2.setCellValueFactory(new PropertyValueFactory<>("productid"));
        coname2.setCellValueFactory(new PropertyValueFactory<>("name"));
        colquantity2.setCellValueFactory(new PropertyValueFactory<>("quntity"));
        colunitprice2.setCellValueFactory(new PropertyValueFactory<>("unitprice"));
        coltotal2.setCellValueFactory(new PropertyValueFactory<>("total"));

        String productid = productidtxt.getText();
        String productname = productnametxt.getText();
        Integer quantity = Integer.valueOf(productquantitytxt.getText());
        Double unitprice = Double.valueOf(productpricetxt.getText());
        Double total = unitprice * quantity;

        Integer itemstock = Integer.valueOf(productquantitytxt.getText());

        if (itemstock < quantity){
            new Alert(Alert.AlertType.WARNING,"Invalid QTy").show();
        }else{
            cartTms.add(new CartTm(productid,productname,quantity,unitprice,total));
            table2.setItems(cartTms);
            calcTotal();

        }
        productquantitytxt.setText("");
    }

    private void calcTotal(){
        Double netTotal = 0.0;
        for (CartTm cartTm : cartTms){
            netTotal+= cartTm.getTotal();
        }
        lblNetTotal.setText(netTotal.toString());
    }

    private void loadEmployeeId(){
        List<String> allEmployeeIDs = EmployeeController.getInstance().getAllEmployeeIDs();

        ObservableList<String> objects = FXCollections.observableArrayList();
        allEmployeeIDs.forEach(id->{
            objects.add(id);
        });
        cmbemployeeids.setItems(objects);
    }
    protected void loadCustomerId(){
        service.custom.CustomerService customerService = ServiceFactory.getInstance().getServiceType(ServiceType.CUSTOMER);
        List<String> allCustomerIDs = CustomerController.getInstance().getAllCustomerIDs();


        ObservableList<String> objects = FXCollections.observableArrayList();
        allCustomerIDs.forEach(id->{
            objects.add(id);
        });
        cmbcustomerids.setItems(objects);
    }

    public void btncustomerManageOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/customermanagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();



    }

    public void refreshbtnonaction(ActionEvent actionEvent) {
        loadCustomerId();
        loadEmployeeId();
    }

    public void btnEmployeeManageOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/employeemanagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnSupplierManageOnAction(ActionEvent actionEvent) {
        Stage stage = new Stage();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/supplierManagement.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();
    }

    public void btnpaceorderOnAction(ActionEvent actionEvent) {
        String orderId = lblOrderIdtxt.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());
        String customerId = cmbcustomerids.getValue();
        String employeeid = cmbemployeeids.getValue();
        ArrayList<OrderDetail> orderDetails = new ArrayList<>();

        cartTms.forEach(obj->{
            orderDetails.add( new OrderDetail(lblOrderIdtxt.getText(),obj.getProductid(),obj.getQuntity(),0.0));
        });


        Order order = new Order(orderId, date, customerId,employeeid,orderDetails);

        try {
            new OrderController().placeOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println(order);

    }
}

