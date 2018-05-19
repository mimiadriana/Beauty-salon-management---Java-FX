/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beautyspa;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Mihaela
 */
public class Userbase {
    private static Connection connection;

// Pristupa drajveru u JAR fajlu
private static Connection createConnection() {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/beauty", "root", "");
    } catch (Exception e) {
        return null;
    }
}

public static Connection get() {
    if (connection == null) {
        connection = createConnection();
    }
    return connection;
}
}
