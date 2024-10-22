package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@ToString
@AllArgsConstructor
@Data
public class Product {
    private String productId;
    private String name;
    private Double price;
    private Integer Quntity;
    private String size;


}
