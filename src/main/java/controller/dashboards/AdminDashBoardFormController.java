package controller.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class AdminDashBoardFormController {

    @FXML
    private AnchorPane ancpAdminDashBoard;

    @FXML
    void btnAdminManagementOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/AdminManagementForm.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        ancpAdminDashBoard.getChildren().clear();
        ancpAdminDashBoard.getChildren().add(load);
    }

    @FXML
    void btnDoctorManagementOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/DoctorManagement.fxml");

        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        ancpAdminDashBoard.getChildren().clear();
        ancpAdminDashBoard.getChildren().add(load);
    }

    @FXML
    void btnPatientManagementOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/PatientManagement.fxml");
        assert resource != null;
        Parent load = FXMLLoader.load(resource);

        ancpAdminDashBoard.getChildren().clear();
        ancpAdminDashBoard.getChildren().add(load);

    }

    @FXML
    void btnReportAndAnalyticsOnAction(ActionEvent event) {

    }

    @FXML
    void btnResourcesmanagementOnAction(ActionEvent event) {

    }

}
