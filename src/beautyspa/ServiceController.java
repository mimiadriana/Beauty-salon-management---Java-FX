package beautyspa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ServiceController {

    private Connection conn = null;
    BeautySpa comp;

    public void setComp(BeautySpa comp) {
        this.comp = comp;
    }

    int index = -1;

    java.sql.Statement stmt;

    @FXML
    private TextField description;

    @FXML
    private TextField pricetext;

    @FXML
    private TableView<Service> serv1;

    @FXML
    private TableColumn<Service, Integer> id1;

    @FXML
    private TableColumn<Service, String> service1;

    @FXML
    private TableColumn<Service, String> price1;

    private String apostrof(String s) {
        return "'" + s + "'";
    }

    @FXML
    void delete(ActionEvent event) {
        if (index >= 0) {  //  E selectat un angajat
            Service a = serv1.getItems().get(index);
            int idAng = a.id.get();
            try {
                String cda = "DELETE FROM detail_services WHERE id = " + idAng;
                System.out.println("cda: " + cda); //  Pt. testare
                stmt.executeUpdate(cda);
                //  Repopulez tabelul
                populez();
                //  Golesc controalele
                description.setText(null);
                pricetext.setText(null);

            } catch (SQLException ex) {
                Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void insert(ActionEvent event) {
        try {
            //  Inserez un nou articol in tabelul Angajati
            String cda = "INSERT INTO detail_services "
                    + "VALUES (null, "
                    + apostrof(description.getText()) + ", " + apostrof(pricetext.getText())
                    + ")";
            // System.out.println(cda); //  Pt. testare
            stmt.executeUpdate(cda);

            ResultSet rs = null;
            rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
            int idNou = -1;
            if (rs.next()) {
                idNou = rs.getInt(1);
            }
            //  Creez un obiect Angajat
            Service a = new Service(idNou, description.getText(), pricetext.getText());

            populez();

            //  Golesc controalele
            description.setText(null);
            pricetext.setText(null);

        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void modify(ActionEvent event) {
        // Corectez datele in baza de date
        if (index >= 0) {  //  E selectat un angajat
            Service a = serv1.getItems().get(index);
            int idser = a.id.get();
            String cda = "UPDATE detail_services "
                    + "SET description = " + apostrof(description.getText())
                    + ", price = " + apostrof(pricetext.getText())
                    + "WHERE id=" + idser;
            //System.out.println("cda: " + cda); //  Pt. testare
            try {
                stmt.executeUpdate(cda);
            } catch (SQLException ex) {
                Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  Repopulez tabelul
            populez();
        }
    }

    @FXML
    void exit(ActionEvent event) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.exit();
    }

    @FXML
    void reservation(ActionEvent event) {
        comp.Reservationload();
    }

    @FXML
    void initialize() {
        //  Se definesc coloanele
        id1.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        service1.setCellValueFactory(cellData -> cellData.getValue().serv);
        price1.setCellValueFactory(cellData -> cellData.getValue().price);

        conn = Userbase.get();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //  Creez multimea de selectie continand datele din 
        //  tabelul Angajati
        populez();

        serv1.getSelectionModel().selectedIndexProperty().
                addListener((ov, valVeche, valNoua) -> {
                    // Se trateaza schimbarea starii. 
                    // valNoua da pozitia noii valori selectate în tabel
                    index = (int) valNoua;
                    // Pun in controalele TextField informatiile din linie
                    if (index >= 0) {
                        Service a = serv1.getItems().get(index);
                        description.setText(a.serv.get());
                        pricetext.setText(a.price.get());

                    }
                });
    }

    void populez() {
        try {
            serv1.getItems().clear();
            String cda = "select * from detail_services order by id asc";
            ResultSet rs = stmt.executeQuery(cda);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String description = rs.getString("Description");
                String price = rs.getString("Price");

                //  Creez un obiect din clasa Angajat
                Service serv = new Service(id, description, price);
                serv1.getItems().add(serv);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
