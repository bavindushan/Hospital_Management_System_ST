package controller.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class DoctorDashBoardFormController {

    @FXML
    private AnchorPane ancpDoctorDashBoard;

    @FXML
    void btnAppointmentAndAvailabilityOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/AppointmentAndAvailabilityManagement.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        ancpDoctorDashBoard.getChildren().clear();
        ancpDoctorDashBoard.getChildren().add(load);
    }

    @FXML
    void btnPrescriptionManagementOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/PrescriptionManagement.fxml");
        assert resource != null;

        Parent load = FXMLLoader.load(resource);

        ancpDoctorDashBoard.getChildren().clear();
        ancpDoctorDashBoard.getChildren().add(load);
    }

}
