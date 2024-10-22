package controller.employee;

import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;

import java.util.List;

public interface EmployeeService {
    List<String> getAllEmployeeIDs();
    ObservableList<Employee> getAllEmployees();
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(String id);

}
