package controller.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class ReceptionistDashBoardFormController {

    @FXML
    private AnchorPane ancpReceptionistDashBoard;

    @FXML
    void btnAppointmentsOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/AppointmenetsManager.fxml");

        assert resource != null;
        Parent load = FXMLLoader.load(resource);
        ancpReceptionistDashBoard.getChildren().clear();
        ancpReceptionistDashBoard.getChildren().add(load);
    }

    @FXML
    void btnBillingandPaymentsOnAction(ActionEvent event) {

    }

    @FXML
    void btnPatientManagementOnAction(ActionEvent event) {

    }

}
