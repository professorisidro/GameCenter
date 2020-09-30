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
import java.util.ArrayList;
import java.util.List;
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
public class GetRankingServlet extends HttpServlet {

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
        String key = request.getParameter("key");
        String order = "DESC";
        String format = "TEXT";
        int limit = 10;
        String pagina = "/resultado.jsp";

        if (request.getParameter("order") != null) {
            order = request.getParameter("order");
        }
        if (request.getParameter("format") != null) {
            format = request.getParameter("format");
        }
        if (request.getParameter("limit") != null) {
            limit = Integer.parseInt(request.getParameter("limit"));
        }

        try {
            DataSource dataSource = new DataSource();
            GameDAO gameDao = new GameDAO(dataSource);
            Game game = gameDao.readByKey(key);
            if (game != null) {
                RankingDAO rankingDAO = new RankingDAO(dataSource);
                List<Ranking> lista;
                lista = rankingDAO.readRanking(game.getId(), order, limit);
                String res = "";
                if (format.equals("TEXT")){
                    for(Ranking r: lista){
                        res = res + r.getEmail()+"\t"+r.getScore()+"\n";
                    }
                }
                else if (format.equals("JSON")){
                    res="[";
                    for (Ranking r: lista){
                        res+="{\"email\":\""+r.getEmail()+"\",\"score\":"+r.getScore()+"},";
                    }
                    res+="]";
                }
                else if (format.equals("XML")){
                    res = "XML TO DO";
                }
                else{
                    res="UNKNOWN";
                }
                request.setAttribute("resultadoSTR", res);
            }
            else{
                request.setAttribute("resultadoSTR","ERRO");
            }   
            dataSource.getConnection().close();
        } catch (Exception ex) {
            System.out.println("Erro! Nao consegui recuperar ranking");
            request.setAttribute("resultadoSTR","ERRO");
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
        dispatcher.forward(request, response);
    }
}
