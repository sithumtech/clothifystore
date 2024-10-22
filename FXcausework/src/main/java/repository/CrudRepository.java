package repository;

public interface CrudRepository<CustomerEntity,String> extends SuperDao {
    boolean save(CustomerEntity entity);
    boolean delete(String id);

}
