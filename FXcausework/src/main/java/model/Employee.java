package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {
    private String emplyeeId;
    private String name;
    private String email;
    private Double salary;
    private String company;
    private String password;

}
