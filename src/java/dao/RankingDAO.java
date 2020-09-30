/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Ranking;

/**
 *
 * @author profpa2
 */
public class RankingDAO {
    private DataSource dataSource;
    
    public RankingDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }
    
    public void create(Ranking ranking){
        try{
            String SQL = "INSERT INTO tblPontuacao VALUES (null, ?, ?, ?)";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setString(1,ranking.getEmail());
            stm.setFloat(2, ranking.getScore());
            stm.setInt(3, ranking.getGame().getId());
            
            int res = stm.executeUpdate();
            if (res == 0){
                throw new RuntimeException("Impossivel gravar ranking");
            }
        }
        catch(Exception ex){
            System.out.println("Erro ao cadastrar pontuacao");
        }
    }    
    
    
    public List<Ranking> readRanking(int idGame, String order, int limite){
        try{
            String SQL = "SELECT * FROM tblPontuacao WHERE idGame = ? ORDER BY score "+order+" LIMIT ?";
            PreparedStatement stm = dataSource.getConnection().prepareStatement(SQL);
            stm.setInt(1, idGame);
            stm.setInt(2, limite);
            ResultSet rs = stm.executeQuery();
            ArrayList<Ranking> lista = new ArrayList<Ranking>();
            while (rs.next()){
                Ranking ranking = new Ranking();
                ranking.setNumSeq(rs.getInt("numSeq"));
                ranking.setEmail(rs.getString("email"));
                ranking.setScore(rs.getFloat("score"));
                lista.add(ranking);
            }
            return lista;                    
        }
        catch(Exception ex){
            System.out.println("Não foi possivel recuperar o RAnking");
        }
        return null;
    }
}
