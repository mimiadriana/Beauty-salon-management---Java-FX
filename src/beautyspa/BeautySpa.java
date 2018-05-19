/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautyspa;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Mihaela
 */
public class BeautySpa extends Application {

    LoginController ctrUnu;
    ReservationController ctrDoi;
    ServiceController ctrTrei;
    Stage fereastra;

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {

            fereastra = primaryStage;
            fereastra.setTitle("BeautySpa");
            Loginload();
            fereastra.show();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void Loginload() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Login.fxml"));
        AnchorPane container;
        try {
            container = (AnchorPane) loader.load();
            Scene scene = new Scene(container);
            fereastra.setScene(scene);
            fereastra.sizeToScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ctrUnu = loader.getController();
        ctrUnu.setComp(this);
    }

    public void Reservationload() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Reservation.fxml"));
        AnchorPane container;
        try {
            container = (AnchorPane) loader.load();
            Scene scene = new Scene(container);
            fereastra.setScene(scene);
            fereastra.sizeToScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ctrDoi = loader.getController();
        ctrDoi.setComp(this);
    }
     public void Serviceload() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Service.fxml"));
        AnchorPane container;
        try {
            container = (AnchorPane) loader.load();
            Scene scene = new Scene(container);
            fereastra.setScene(scene);
            fereastra.sizeToScene();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ctrTrei = loader.getController();
        ctrTrei.setComp(this);
    }
}
