package controller.dashboards;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;

public class PatientDashBoardFormController {

    @FXML
    private AnchorPane ancpPation;

    @FXML
    void btnViewAppointmentOnAction(ActionEvent event) throws IOException {
        URL resource = this.getClass().getResource("/view/PatientAppointmentView.fxml");
        assert resource!=null;
        Parent load = FXMLLoader.load(resource);

        ancpPation.getChildren().clear();
        ancpPation.getChildren().add(load);

    }

}
