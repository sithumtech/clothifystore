package controller.supplier;


import model.Supplier;
import util.CrudUtil;

import java.sql.SQLException;

public class SupplierController implements SuplierService {
    private static SupplierController instance;

    private SupplierController(){}

    public static SupplierController getInstance(){
        return instance==null?instance=new SupplierController():instance;
    }

    @Override
    public boolean addSupplier(Supplier supplier) {
        String SQL="INSERT INTO supplier values(?,?,?,?,?)";
        try {
            return   CrudUtil.execute(SQL,
                    supplier.getSupID(),
                    supplier.getName(),
                    supplier.getCompany(),
                    supplier.getItem(),
                    supplier.getEmail()

            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteSupplier(String id) {
        try {
            String SQL="DELETE FROM supplier WHERE supID ='" + id + "'";
            return CrudUtil.execute(SQL);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
