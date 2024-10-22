package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartTm {
    private  String productid;

    private String name;

    private Integer quntity;

    private Double unitprice;

    private Double total;

}
