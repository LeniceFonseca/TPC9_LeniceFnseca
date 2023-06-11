package com.example.tp3_lenicefonseca;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    static Connection connection;


    public static Connection getConnection(){



        String url = "jdbc:mysql://localhost:3306/pedradadigital";
        String user = "root";
        String password = "Lenice@Fonseca1";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Database is successfully connected.");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;

    }

}
