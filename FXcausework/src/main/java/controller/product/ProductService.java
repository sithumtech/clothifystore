package controller.product;

import javafx.collections.ObservableList;
import model.OrderDetail;
import model.Product;

import java.util.List;

public interface ProductService {
    ObservableList<Product> getAllladiesProducts();
    ObservableList<Product> getAllGentsProducts();
    ObservableList<Product> getAllKidsProducts();
    boolean updateStock(List<OrderDetail> orderdetails);
}
