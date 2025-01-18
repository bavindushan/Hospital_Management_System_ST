package controller.Admin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Admin;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AdminFormController implements Initializable {

    @FXML
    private TableColumn clmEmail;

    @FXML
    private TableColumn clmID;

    @FXML
    private TableColumn clmName;

    @FXML
    private TableColumn clmPassword;

    @FXML
    private TableView tblAdminTable;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    AdminController adminController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminController = new AdminController();
        loadTable();
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean b = adminController.addAdmin(new Admin(txtId.getText(),txtPassword.getText(), txtName.getText(), txtEmail.getText()));
            
            if (b)  new Alert(Alert.AlertType.CONFIRMATION, "Admin added successful!!");
            else new Alert(Alert.AlertType.ERROR, "Admin not add!!");

        } catch (SQLException e) {
            System.out.println("An Error Occur!!"+e.getMessage());
        }
    }
    private String genarateAdminID(){
        String lastAdminID = adminController.getLastAdminID();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    private void loadTable(){
        clmID.setCellValueFactory(new PropertyValueFactory<>("admin_id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        ObservableList<Admin> adminObservbleList = FXCollections.observableArrayList();

    }
}
