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
public class Customers {
    SimpleIntegerProperty id;

    SimpleStringProperty name;
    SimpleStringProperty phone;
  SimpleStringProperty comment;
    public Customers(int id, String name, String phone, String comment) {
        this.id = new SimpleIntegerProperty(id);

        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleStringProperty(phone);
   this.comment = new SimpleStringProperty(comment);
    }
}

