package service.custom.impl;

import javafx.collections.ObservableList;
import model.Employee;
import service.custom.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public List<String> getAllEmployeeIDs() {
        return List.of();
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public boolean addEmployee(Employee employee) {
        return false;
    }

    @Override
    public boolean deleteEmployee(String id) {
        return false;
    }
}
