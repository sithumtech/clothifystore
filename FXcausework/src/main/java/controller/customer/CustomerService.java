package controller.customer;

import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;

import java.util.List;

public interface CustomerService {
    List<String> getAllCustomerIDs();
    ObservableList<Customer> getAllCustomers();
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
