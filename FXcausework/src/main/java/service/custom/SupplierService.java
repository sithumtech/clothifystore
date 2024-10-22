package service.custom;

import model.Supplier;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    boolean deleteSupplier(String id);
}
