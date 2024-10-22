package controller.employee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import model.Employee;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController implements EmployeeService{

    private static EmployeeController instance;

    private EmployeeController(){}

    public static EmployeeController getInstance(){
        return instance==null?instance=new EmployeeController():instance;
    }

    @Override
    public List<String> getAllEmployeeIDs() {
        ArrayList<String > employeeIdsList = new ArrayList<>();
        ObservableList<Employee> allEmployees = getAllEmployees();

        allEmployees.forEach(obj->{
            employeeIdsList.add(obj.getEmplyeeId());
        });

        return employeeIdsList;
    }

    @Override
    public ObservableList<Employee> getAllEmployees() {
        ObservableList<Employee> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM employee");
            while (resultSet.next()){
                observableList.add(
                        new Employee(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getString(3),
                                resultSet.getDouble(4),
                                resultSet.getString(5),
                                resultSet.getString(6)


                        )
                );
            }
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addEmployee(Employee employee) {
        String SQL="INSERT INTO employee values(?,?,?,?,?,?)";
        try {
            return   CrudUtil.execute(SQL,
                    employee.getEmplyeeId(),
                    employee.getName(),
                    employee.getEmail(),
                    employee.getSalary(),
                    employee.getCompany(),
                    employee.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteEmployee(String id) {
        try {
            String SQL="DELETE FROM employee WHERE emplyeeId ='" + id + "'";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
