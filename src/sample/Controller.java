package sample;

import Admin.AdminContrcller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private loginModel loginModel = new loginModel();
    @FXML
    private Label dbStatus;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (this.loginModel.isDatabaseConnection()){
            this.dbStatus.setText("Connected to DB");
        } else {
            this.dbStatus.setText("Not connected to DB");
        }


    }//initialize

    @FXML
    public void Login(ActionEvent event){
        try{
            if (this.loginModel.isLogin(this.username.getText(),
                    this.password.getText())){
                Stage stage = (Stage) this.btnLogin.getScene().getWindow();
                stage.close();

                adminLogin();

            } else {
                JOptionPane.showMessageDialog(null,"Your username or " +
                "password is invalid.");
            }
        }catch (Exception localException){
            localException.printStackTrace();
        }
    }//Login
    private void adminLogin(){
        try{
            Stage adminStage = new Stage();
            FXMLLoader adminLoader = new FXMLLoader();
            Pane adminRoot = adminLoader.load(getClass().getResource("" + "/Admin/AdminDashBoard.fxml").openStream());

            AdminContrcller adminContrcller = (AdminContrcller) adminLoader.getController();
            Scene scene = new Scene(adminRoot);
            adminStage.setScene(scene);
            adminStage.setTitle("Admin Dashboard");
            adminStage.setResizable(false);
            adminStage.show();





        } catch (IOException ex){
            ex.printStackTrace();
        }
//        JOptionPane.showMessageDialog(null,
//                "Welcome to Our System.");
    }//adminLogin

}
