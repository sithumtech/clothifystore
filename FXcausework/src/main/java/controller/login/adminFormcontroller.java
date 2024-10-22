package controller.login;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import util.CrudUtil;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class adminFormcontroller {

    @FXML
    private JFXTextField adminemailtxt;

    @FXML
    private JFXTextField adminpasswordtxt;

    @FXML
    void btnadminloginOnaction(ActionEvent event) {

            // Get input values from text fields
            String email = adminemailtxt.getText();
            String password = adminpasswordtxt.getText();

            // Check if the text fields are not empty
            if (!email.isEmpty() && !password.isEmpty()) {
                try {
                    // SQL query to check if the email and password exist in the database
                    String sql = "SELECT * FROM adminuser WHERE email = ? AND adminpassword = ?";

                    // Execute the query using the CrudUtil class
                    ResultSet resultSet = CrudUtil.execute(sql, email, password);

                    // Check if the result set has any records
                    if (resultSet.next()) {
                        // If a record is found, it means login is successful
                        Stage stage = new Stage();
                        try {
                            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/placeorder.fxml"))));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        stage.show();




                    } else {
                        // If no record is found, login failed
                      new Alert(Alert.AlertType.ERROR,"Invalid email or password").show();
                    }

                } catch (SQLException e) {
                    // Handle any SQL exceptions that may occur
                    e.printStackTrace();
                }
            } else {
                // If either field is empty, prompt the user to fill in both fields

                new Alert(Alert.AlertType.ERROR,"Please fill in both email and password").show();
            }
    }




}
