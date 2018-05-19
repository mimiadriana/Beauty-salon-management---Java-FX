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
public class Reservation {
    SimpleIntegerProperty id;
     
     SimpleStringProperty customer;
      SimpleStringProperty service;
   SimpleStringProperty date;
   SimpleStringProperty time;
  
   public Reservation(int id,  String  customer,String  service, String date, String time) {
     this.id = new SimpleIntegerProperty(id);
    
      this.customer = new  SimpleStringProperty(customer);
       this.service = new  SimpleStringProperty(service);
     this.date = new SimpleStringProperty(date);
     this.time = new SimpleStringProperty(time);
}
}