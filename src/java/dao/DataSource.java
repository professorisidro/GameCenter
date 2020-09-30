/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;




/**
 *
 * @author profpa2
 */
public class DataSource {
    private String hostname;
    private String database;
    private String username;
    private String password;
    private Connection connection;
    
    public DataSource(){
        try{
            hostname="localhost";
            database="gamecenter";
            username="usergc";
            password="12345678";
            // montamos a URL baseada nos parametros
            String url = "jdbc:mysql://"+hostname+":3306/"+database+"?useTimezone=true&serverTimezone=UTC";
            // registramos o driver
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            // so falta conectar
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Conectado ao banco!");
        }
        catch(Exception ex){
            System.out.println("Erro na conexao");
            ex.printStackTrace();
        }
    }
    
    public Connection getConnection(){
        return connection;
    }
    
}
