package controller.product;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.OrderDetail;
import model.Product;
import util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ProductController implements ProductService{
    private static ProductController instance;
    private ProductController(){}
    public static ProductController getInstance(){
        return instance==null?instance=new ProductController():instance;
    }

    @Override
    public ObservableList<Product> getAllladiesProducts() {
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM ladies_products");
            while (resultSet.next()){
                observableList.add(
                        new Product(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4),
                                resultSet.getString(5)

                        )
                );
            }
           
            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Product> getAllGentsProducts() {
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM gents_products");
            while (resultSet.next()){
                observableList.add(
                        new Product(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4),
                                resultSet.getString(5)

                        )
                );
            }

            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Product> getAllKidsProducts() {
        ObservableList<Product> observableList = FXCollections.observableArrayList();
        try {
            ResultSet resultSet = CrudUtil.execute("SELECT * FROM kids_products");
            while (resultSet.next()){
                observableList.add(
                        new Product(
                                resultSet.getString(1),
                                resultSet.getString(2),
                                resultSet.getDouble(3),
                                resultSet.getInt(4),
                                resultSet.getString(5)

                        )
                );
            }

            return observableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateStock(List<OrderDetail> orderdetails) {
        for (OrderDetail orderdetail:orderdetails){
            boolean isUpdateStock = updateStock(orderdetail);
            if(!isUpdateStock){
                return false;
            }
        }
        return true;
    }
    public boolean updateStock(OrderDetail orderdetail){
        String SQL="Update ladies_products SET quntity=quntity-? WHERE productID=?";
        try {
            return  CrudUtil.execute(SQL,orderdetail.getQty(),orderdetail.getItemCode());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
