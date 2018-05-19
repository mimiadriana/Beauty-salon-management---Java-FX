/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautyspa;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Mihaela
 */
public class LoginController implements Initializable {

    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    BeautySpa comp;

    String codAcces = "";

    public void setComp(BeautySpa comp) {
        this.comp = comp;
    }

    @FXML
    void zero(ActionEvent event) {
        codAcces += '0';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }

    @FXML
    void unu(ActionEvent event) {
        codAcces += '1';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
     @FXML
    void doi(ActionEvent event) {
        codAcces += '2';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
 @FXML
    void trei(ActionEvent event) {
        codAcces += '3';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
    @FXML
    void patru(ActionEvent event) {
        codAcces += '4';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
    @FXML
    void cinci(ActionEvent event) {
        codAcces += '5';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
    @FXML
    void sase(ActionEvent event) {
        codAcces += '6';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }@FXML
    void sapte(ActionEvent event) {
        codAcces += '7';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }@FXML
    void opt(ActionEvent event) {
        codAcces += '8';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }@FXML
    void noua(ActionEvent event) {
        codAcces += '9';
        System.out.println("cda: " + codAcces); //  Pt. testare
        if (codAcces.length() == 4) {
            // Am ajuns la 4 caractere. Testez daca este corect
            login();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        conn = Userbase.get();

    }

    public void login() {
        // String userName = nameFld.getText().trim();
        String password = codAcces;

        String sql = "SELECT * from login WHERE  password = ?";
        try {
            ps = conn.prepareStatement(sql);
            //  ps.setString(1, userName);
            ps.setString(1, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                comp.Serviceload();
            }
            else
            {codAcces="";
           new Alert(Alert.AlertType.ERROR, "Code is incorrect! Reenter your code!").showAndWait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exit(ActionEvent event) {
        Platform.exit();

    }

}
