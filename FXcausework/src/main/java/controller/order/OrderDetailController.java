package controller.order;

import model.OrderDetail;
import util.CrudUtil;

import java.sql.SQLException;
import java.util.List;

public class OrderDetailController {
    public boolean addOrderDetail(List<OrderDetail> orderdetails) {
        for(OrderDetail orderdetail: orderdetails) {
            boolean isOrderDetailAdd = addOrderDetail(orderdetail);
            if (!isOrderDetailAdd) {
                return false;
            }
        }
        return true;
    }
    public boolean addOrderDetail(OrderDetail orderdetail){
        String SQL="INSERT INTO orderdetail VALUES(?,?,?,?)";
        try {
            return CrudUtil.execute(SQL,
                    orderdetail.getOrderId(),
                    orderdetail.getItemCode(),
                    orderdetail.getQty(),
                    orderdetail.getDiscount()
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
