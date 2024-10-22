package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Admin {
    private String adminId;
    private String adminname;
    private String password;
    private String phone;
    private String email;
    private String role;


}
