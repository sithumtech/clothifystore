package controller.customer;

import controller.employee.EmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController implements CustomerService {
    private static CustomerController instance;

    private CustomerController(){}

    public static CustomerController getInstance(){
        return instance==null?instance=new CustomerController():instance;
    }

    @Override
    public List<String> getAllCustomerIDs() {
        ArrayList<String > CustomerIdsList = new ArrayList<>();
        ObservableList<Customer> allCustomers = getAllCustomers();

        allCustomers.forEach(obj->{
            CustomerIdsList.add(obj.getCusID());
        });

        return CustomerIdsList;
    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        ObservableList<Customer> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer");
            while (resultSet.next()){
                observableList.add(
                        new Customer(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getString(4)


                        )
                );
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addCustomer(Customer customer) {
//        String SQL="INSERT INTO customer values(?,?,?,?)";
//        try {
//            return   CrudUtil.execute(SQL,
//                    customer.getCusID(),
//                    customer.getName(),
//                    customer.getPhonenumber(),
//                    customer.getEmail()
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;


    }

    @Override
    public boolean deleteCustomer(String id) {
//        try {
//            String SQL="DELETE FROM customer WHERE cusId ='" + id + "'";
//            return CrudUtil.execute(SQL);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return false;
    }
}
