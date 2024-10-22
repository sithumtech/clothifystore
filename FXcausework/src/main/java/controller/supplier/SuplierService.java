package controller.supplier;

import model.Employee;
import model.Supplier;

public interface SuplierService {
    boolean addSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
}
