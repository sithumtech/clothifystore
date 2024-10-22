package repository.custom.impl;

import entity.CustomerEntity;
import repository.custom.CustomerDao;
import util.CrudUtil;

import java.sql.SQLException;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customer) {
        String SQL="INSERT INTO customer values(?,?,?,?)";
        try {
            return   CrudUtil.execute(SQL,
                    customer.getCusID(),
                    customer.getName(),
                    customer.getPhonenumber(),
                    customer.getEmail()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(String id) {
        System.out.println(id);
        try {
            String SQL="DELETE FROM customer WHERE cusId ='" + id + "'";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
