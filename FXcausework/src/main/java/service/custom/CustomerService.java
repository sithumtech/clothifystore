package service.custom;

import javafx.collections.ObservableList;
import model.Customer;
import service.SuperService;

import java.util.List;

public interface CustomerService extends SuperService {
    List<String> getAllCustomerIDs();
    ObservableList<Customer> getAllCustomers();
    boolean addCustomer(Customer customer);
    boolean deleteCustomer(String id);
}
