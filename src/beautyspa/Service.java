/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautyspa;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Mihaela
 */
public class Service {

    SimpleIntegerProperty id;

    SimpleStringProperty serv;
    SimpleStringProperty price;

    public Service(int id, String serv, String price) {
        this.id = new SimpleIntegerProperty(id);

        this.serv = new SimpleStringProperty(serv);
        this.price = new SimpleStringProperty(price);

    }
}
