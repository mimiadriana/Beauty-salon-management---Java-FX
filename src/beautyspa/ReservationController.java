/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautyspa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Mihaela
 */
public class ReservationController {

    BeautySpa comp;
    java.sql.Statement stmt;
    private Connection conn = null;
    int index = -1;

    public void setComp(BeautySpa comp) {
        this.comp = comp;
    }

    private String apostrof(String s) {
        return "'" + s + "'";
    }

    @FXML
    private TableView<Reservation> reservation;

    @FXML
    private TableColumn<Reservation, Integer> Id;

    @FXML
    private TableColumn<Reservation, String> cust;
    @FXML
    private TableColumn<Reservation, String> serv;
    @FXML
    private TableColumn<Reservation, String> date;
    @FXML
    private TableColumn<Reservation, String> time;

    @FXML
    private DatePicker datetxt;

    @FXML
    private TextField timetxt;

    @FXML
    private TableView<Customers> customers;

    @FXML
    private TableColumn<Customers, Integer> id;

    @FXML
    private TableColumn<Customers, String> name;

    @FXML
    private TableColumn<Customers, String> phone;

    @FXML
    private TableColumn<Customers, String> comment;

    @FXML
    private ComboBox<String> servicecombo;
    private List<Integer> ids1 = new ArrayList<>();
    @FXML
    private ComboBox<String> customercombo;
    private List<Integer> ids = new ArrayList<>();

    @FXML
    private TextField nametxt;

    @FXML
    private TextField phonetxt;

    @FXML
    private TextField commtxt;
    Integer id_value;
    Integer id_value1;

    @FXML
    void delete(ActionEvent event) {
        if (index >= 0) {
            try {
                //  E selectat un angajat
                Reservation a = reservation.getItems().get(index);
                int idAng = a.id.get();
                System.out.println(idAng);
                String sql = "DELETE FROM reservation WHERE id=?";

                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, idAng);
                System.out.println(sql);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("A reservation was deleted successfully!");
                }

            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            populez();
        }
    }

    @FXML
    void delete1(ActionEvent event) {
        if (index >= 0) {  //  E selectat un angajat
            Customers a = customers.getItems().get(index);
            int idAng = a.id.get();
            try {
                String cda = "DELETE FROM customers WHERE id = " + idAng;
                System.out.println("cda: " + cda); //  Pt. testare
                stmt.executeUpdate(cda);
                //  Repopulez tabelul

                //  Golesc controalele
                nametxt.setText(null);
                phonetxt.setText(null);
                commtxt.setText(null);
                System.out.println(cda);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        populez1();
    }

    @FXML
    void insert(ActionEvent event) {
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datetxt.getValue());
        try {
            String sql = "INSERT INTO reservation (idcustomer, idservice, date, time) VALUES (?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id_value);
            statement.setInt(2, id_value1);
            statement.setDate(3, gettedDatePickerDate);
            statement.setString(4, timetxt.getText());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }

            System.out.println("cda: " + sql); // Pt. testare

        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

        populez();

    }

    @FXML
    void insert1(ActionEvent event) {
        try {
            //  Inserez un nou articol in tabelul Angajati
            String cda = "INSERT INTO customers "
                    + "VALUES (null, "
                    + apostrof(nametxt.getText()) + ", " + apostrof(phonetxt.getText())
                    + ", " + apostrof(commtxt.getText())
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
            Customers a = new Customers(idNou, nametxt.getText(), phonetxt.getText(), commtxt.getText());

            populez1();

            //  Golesc controalele
            nametxt.setText(null);
            phonetxt.setText(null);
            commtxt.setText(null);

        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void modify(ActionEvent event) throws SQLException {
        if (index >= 0) {  //  E selectat un angajat
            Reservation a = reservation.getItems().get(index);
            int idcust = a.id.get();

            System.out.println(idcust);

            java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datetxt.getValue());
            try {
                String sql = "Update reservation SET idcustomer=?, idservice=?, date=?, time=?  where id = ?";

                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, id_value);
                statement.setInt(2, id_value1);
                statement.setDate(3, gettedDatePickerDate);
                statement.setString(4, timetxt.getText());
                statement.setInt(5, idcust);
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("A new reservation was modified successfully!");
                }

                System.out.println("cda: " + sql); // Pt. testare

                System.out.println(idcust);

                populez();
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    void modify1(ActionEvent event) {
        if (index >= 0) {  //  E selectat un angajat

            Customers a = customers.getItems().get(index);
            int idcust = a.id.get();
            System.out.println(idcust);
            String cda = "UPDATE customers "
                    + "SET name= " + apostrof(nametxt.getText())
                    + ", phone = " + apostrof(phonetxt.getText())
                    + ", comment = " + apostrof(commtxt.getText())
                    + "WHERE id=" + idcust;
            System.out.println("cda: " + cda); //  Pt. testare
            try {
                stmt.executeUpdate(cda);
            } catch (SQLException ex) {
                Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
            }
            //  Repopulez tabelul
            populez1();
        }
    }

    @FXML
    void today(ActionEvent event) {
        try {
            reservation.getItems().clear();
            String cda = "select  reservation.ID, detail_services.Description, customers.Name, reservation.Date, reservation.Time  from reservation INNER JOIN customers ON reservation.idcustomer=customers.id INNER JOIN detail_services ON reservation.idservice=detail_services.id where date = CAST(CURRENT_TIMESTAMP AS DATE) order by    id asc";
            ResultSet rs;

            rs = stmt.executeQuery(cda);

            while (rs.next()) {
                int id = rs.getInt("ID");
                String service = rs.getString("Description");
                String customer = rs.getString("Name");
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                //  Creez un obiect din clasa Angajat
                Reservation res = new Reservation(id, service, customer, date, time);
                reservation.getItems().add(res);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void all(ActionEvent event) {
        populez();
    }

    @FXML
    void close(ActionEvent event) {
        try {
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Platform.exit();
    }

    @FXML
    void services(ActionEvent event) {
        comp.Serviceload();
    }

    @FXML
    void initialize() {
        //  Se definesc coloanele
        Id.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        serv.setCellValueFactory(cellData -> cellData.getValue().service);
        cust.setCellValueFactory(cellData -> cellData.getValue().customer);
        date.setCellValueFactory(cellData -> cellData.getValue().date);
        time.setCellValueFactory(cellData -> cellData.getValue().time);

        id.setCellValueFactory(cellData -> cellData.getValue().id.asObject());
        name.setCellValueFactory(cellData -> cellData.getValue().name);
        phone.setCellValueFactory(cellData -> cellData.getValue().phone);
        comment.setCellValueFactory(cellData -> cellData.getValue().comment);

        conn = Userbase.get();
        try {
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceController.class.getName()).log(Level.SEVERE, null, ex);
        }
        customercombo.setOnAction(e -> {
            int index = customercombo.getSelectionModel().getSelectedIndex();
            //here is the id_value
            id_value = ids.get(index);
            System.out.println("The id of " + customercombo.getItems().get(index) + " is : " + id_value);
            //

        });
        servicecombo.setOnAction(e -> {
            int index = servicecombo.getSelectionModel().getSelectedIndex();
            //here is the id_value
            id_value1 = ids1.get(index);
            System.out.println("The id of " + servicecombo.getItems().get(index) + " is : " + id_value1);
            //

        });
        populez();
        populez1();
        populezcombocust();
        populezcomboservice();
        customers.getSelectionModel().selectedIndexProperty().
                addListener((ov, valVeche, valNoua) -> {
                    // Se trateaza schimbarea starii. 
                    // valNoua da pozitia noii valori selectate în tabel
                    index = (int) valNoua;
                    // Pun in controalele TextField informatiile din linie
                    if (index >= 0) {
                        Customers a = customers.getItems().get(index);
                        nametxt.setText(a.name.get());
                        phonetxt.setText(a.phone.get());
                        commtxt.setText(a.comment.get());

                    }

                });
        reservation.getSelectionModel().selectedIndexProperty().
                addListener((ov, valVeche, valNoua) -> {
                    // Se trateaza schimbarea starii. 
                    // valNoua da pozitia noii valori selectate în tabel
                    index = (int) valNoua;
                    if (index >= 0) {
                        Reservation a = reservation.getItems().get(index);
                        timetxt.setText(a.time.get());
                    }

                });
    }

    void populez() {
        try {
            reservation.getItems().clear();
            String cda = "select  reservation.ID, customers.name, detail_services.description, reservation.Date, reservation.Time  from reservation INNER JOIN customers ON reservation.idcustomer=customers.id INNER JOIN detail_services ON reservation.idservice=detail_services.id where date >= CAST(CURRENT_TIMESTAMP AS DATE) order by    id asc";
            ResultSet rs = stmt.executeQuery(cda);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String service = rs.getString("name");
                String customer = rs.getString("description");
                String date = rs.getString("Date");
                String time = rs.getString("Time");
                //  Creez un obiect din clasa Angajat
                Reservation res = new Reservation(id, service, customer, date, time);
                reservation.getItems().add(res);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void populez1() {
        try {
            customers.getItems().clear();
            String cda = "select  * from customers  order by    id asc";
            ResultSet rs = stmt.executeQuery(cda);
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String phone = rs.getString("Phone");
                String comment = rs.getString("Comment");

                //  Creez un obiect din clasa Angajat
                Customers cust = new Customers(id, name, phone, comment);
                customers.getItems().add(cust);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void populezcombocust() {
        this.customercombo.getItems().clear();
        this.ids.clear();

        try {

            String cda = "select  id, name from customers  ";
            ResultSet rs = stmt.executeQuery(cda);
            while (rs.next()) {

                int index = 0;
                while (rs.next()) {
                    this.customercombo.getItems().add(index, rs.getString("name"));
                    this.ids.add(index, Integer.valueOf(rs.getInt("id")));
                    index++;
                }
            }//end while

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void populezcomboservice() {
        this.servicecombo.getItems().clear();
        this.ids1.clear();

        try {

            String cda = "select  id, description from detail_services  ";
            ResultSet rs = stmt.executeQuery(cda);
            while (rs.next()) {

                int index = 0;
                while (rs.next()) {
                    this.servicecombo.getItems().add(index, rs.getString("description"));
                    this.ids1.add(index, Integer.valueOf(rs.getInt("id")));
                    index++;
                }
            }//end while

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
