/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DataSource;
import dao.GameDAO;
import dao.RankingDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Game;
import model.Ranking;

/**
 *
 * @author profpa2
 */
public class AddRankingServlet extends HttpServlet {

    

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pagina="/resultado.jsp";
        try{
            String key = request.getParameter("key");
            String email = request.getParameter("email");
            float  score = Float.parseFloat(request.getParameter("score"));
            System.out.println("Recebi key   = "+key);
            System.out.println("Recebi email = "+email);
            System.out.println("Recebi score = "+score);                       
            DataSource dataSource = new DataSource();
            // passo 1 - recuperar as infos do jogo a partir da key
            GameDAO gameDAO = new GameDAO(dataSource);
            Game game = gameDAO.readByKey(key);
            if (game != null){
                // passo 2 - montar um objeto Ranking e gravar
                Ranking ranking = new Ranking();
                ranking.setEmail(email);
                ranking.setGame(game);
                ranking.setScore(score);
                // passo 3 - gravar o ranking no banco
                RankingDAO rankingDAO = new RankingDAO(dataSource);
                rankingDAO.create(ranking);
                request.setAttribute("resultadoSTR","SUCESSO");
            }
            else{
                request.setAttribute("resultadoSTR","ERRO");
            }
            dataSource.getConnection().close();
        }
        catch(Exception ex){
           System.out.println("Erro ao gravar RANKING...");
           request.setAttribute("resultadoSTR","ERRO");
        }        
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }

   
}
