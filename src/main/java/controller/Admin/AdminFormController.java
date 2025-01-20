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
import java.util.List;
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
        txtId.setText(genarateAdminID());
        loadTable();
    }

    private String genarateAdminID(){
        String lastAdminID = adminController.getLastAdminID();
        if (lastAdminID==null){
            return "A001";
        }
        int numericPart = Integer.parseInt(lastAdminID.substring(1));
        int newNumericPart = numericPart + 1;

        return String.format("A%03d",newNumericPart);
    }

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            boolean b = adminController.addAdmin(new Admin(genarateAdminID(),txtPassword.getText(), txtName.getText(), txtEmail.getText()));
            
            if (b)  new Alert(Alert.AlertType.INFORMATION, "Admin added successful!!").show();
            else new Alert(Alert.AlertType.ERROR, "Admin not add!!").show();

            loadTable();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "An error occurred: " + e.getMessage()).show();
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            boolean b = adminController.deleteAdmin(txtEmail.getText());
            if (b) new Alert(Alert.AlertType.INFORMATION,"Admin deleted!");
            else new Alert(Alert.AlertType.ERROR,"Admin not delete!");

            loadTable();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"An Error occur "+e.getMessage());
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            Admin admin = adminController.searchAdmin(txtEmail.getText());
            txtId.setText(admin.getAdminID());
            txtEmail.setText(admin.getAdminEmail());
            txtName.setText(admin.getAdminName());
            txtPassword.setText(admin.getAdminPassword());
            new Alert(Alert.AlertType.INFORMATION,"Admin found!");

            loadTable();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"An error occur "+e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            boolean b = adminController.updateAdmin(new Admin(txtId.getText(), txtEmail.getText(), txtName.getText(), txtPassword.getText()));
            if (b) new Alert(Alert.AlertType.INFORMATION,"Admin update successful!");
            else new Alert(Alert.AlertType.ERROR, "Admin not update!");

            loadTable();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,"An error occur "+e.getMessage());
        }
    }
    private void loadTable(){
        clmID.setCellValueFactory(new PropertyValueFactory<>("admin_id"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        clmPassword.setCellValueFactory(new PropertyValueFactory<>("password"));

        ObservableList<Admin> adminObservbleList = FXCollections.observableArrayList();
        List<Admin> adminList = adminController.getAllAdmin();
        adminList.forEach(admin -> {
            adminObservbleList.add(admin);
        });
        tblAdminTable.setItems(adminObservbleList);
    }
}
