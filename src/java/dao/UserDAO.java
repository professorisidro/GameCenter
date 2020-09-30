/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import model.User;

/**
 *
 * @author profpa2
 */
public class UserDAO {
    private DataSource dataSource;
    
    public UserDAO(DataSource ds){
        this.dataSource = ds;
    }
    
    
    public User read(String email, String senha){
        try{
            String SQL = "select * from tbluser where email like ? and senha like ?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setString(1, email);
            stm.setString(2, senha);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
                return user;
            }
        }
        catch(Exception ex){
            System.out.println("Erro ao recuperar usuario");
        }
        return null;
    }
}
