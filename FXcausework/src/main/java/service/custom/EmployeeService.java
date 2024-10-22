package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import service.SuperService;

import java.util.List;

public interface EmployeeService extends SuperService {
    List<String> getAllEmployeeIDs();
    ObservableList<Employee> getAllEmployees();
    boolean addEmployee(Employee employee);
    boolean deleteEmployee(String id);
}
