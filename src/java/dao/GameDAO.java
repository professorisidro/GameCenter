/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Game;
import model.User;

/**
 *
 * @author profpa2
 */
public class GameDAO {
    private DataSource dataSource;
    
    public GameDAO(DataSource ds){
        dataSource = ds;
    }
    
    public void create(Game game){
        try{
            String SQL = "INSERT INTO tblgame values(null, ?, ?, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setString(1, game.getTitle());
            stm.setString(2, game.getPlatform());
            stm.setString(3, game.getKey());
            stm.setInt(4, game.getUser().getId());
            int res = stm.executeUpdate();
            if (res == 0){
                throw new RuntimeException("Nao consegui inserir o jogo ");
            }
            stm.close();
        }
        catch(Exception ex){
            System.out.println("Erro ao inserir novo jogo "+ex.getMessage());
        }
    }
    public ArrayList<Game> read(User user){
        try{
            String SQL = "SELECT * FROM tblgame WHERE idUser = ?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, user.getId());
            ResultSet rs = stm.executeQuery();
            ArrayList<Game> meusJogos = new ArrayList<Game>();
            while (rs.next()){ // tem informaçao no banco?
                Game game = new Game(); // crio um jogo e preencho com os valores das colunas
                game.setId(rs.getInt("id"));
                game.setTitle(rs.getString("titulo"));
                game.setPlatform(rs.getString("plataforma"));
                game.setKey(rs.getString("chave"));
                game.setUser(user);
                meusJogos.add(game); // adiciono na lista
            }
            rs.close();
            stm.close();
            return meusJogos;
        }
        catch(Exception ex){
            System.out.println("Erro ao recuperar jogos "+ex.getMessage());
        }
        return null;
    }
    
    public Game readByKey(String key){
        try{
            String SQL = "SELECT * FROM tblGame WHERE chave=?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setString(1, key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()){
                Game game = new Game();
                game.setId(rs.getInt("id"));
                game.setTitle(rs.getString("titulo"));
                game.setPlatform(rs.getString("plataforma"));
                game.setKey(rs.getString("chave"));
                return game;
            }
        }
        catch(Exception ex){
            System.out.println("Erro ao recuperar jogo pela chave");
        }
        return null;
            
    }
    
}
