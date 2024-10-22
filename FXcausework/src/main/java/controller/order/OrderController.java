package controller.order;

import controller.product.ProductController;
import db.DBConnection;
import javafx.scene.control.Alert;
import model.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderController {
    public boolean placeOrder(Order order) throws SQLException {
        System.out.println(order);
        Connection connection = DBConnection.getInstance().getConnection();
        try{
            connection.setAutoCommit(false);
            String SQL ="INSERT INTO orderss VALUES(?,?,?,?)";
            PreparedStatement psTm = connection.prepareStatement(SQL);
            psTm.setObject(1,order.getOrderId());
            psTm.setObject(2,order.getDate());
            psTm.setObject(3,order.getCustomerId());
            psTm.setObject(4,order.getEmployeeID());
            boolean isOrderAdd = psTm.executeUpdate() > 0;
            if (isOrderAdd){
                new OrderDetailController().addOrderDetail(order.getOrderdetails());
                if(isOrderAdd){
                    boolean isUpdateStock= ProductController.getInstance().updateStock(order.getOrderdetails());
                    if (isUpdateStock){
                        connection.commit();
                        new Alert(Alert.AlertType.INFORMATION,"Order Placed!").show();
                        return true;
                    }


                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }



    }
}
