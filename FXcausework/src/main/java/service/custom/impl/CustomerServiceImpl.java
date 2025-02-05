package service.custom.impl;

import entity.CustomerEntity;
import javafx.collections.ObservableList;
import model.Customer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerService;
import util.DaoType;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public List<String> getAllCustomerIDs() {
        return List.of();

    }

    @Override
    public ObservableList<Customer> getAllCustomers() {
        return null;
    }

    @Override
    public boolean addCustomer(Customer customer) {
        CustomerEntity entity=new ModelMapper().map(customer,CustomerEntity.class);
        CustomerDao repository = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return repository.save(entity);
    }

    @Override
    public boolean deleteCustomer(String id) {
        CustomerDao repository = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return repository.delete(id);

    }
}
