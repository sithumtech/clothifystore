package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@AllArgsConstructor
@ToString
public class Supplier {
    private String supID;
    private String name;
    private String company;
    private String item;
    private String email;
}
