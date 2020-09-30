/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DataSource;
import dao.GameDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Game;
import model.User;

/**
 *
 * @author profpa2
 */
public class ConsultarJogosServlet extends HttpServlet {

   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String paginaDestino="/index.html";
        
        if (request.getSession().getAttribute("User") != null){
            try{
                User user = (User)request.getSession().getAttribute("User");
                DataSource dataSource = new DataSource();
                GameDAO gameDao = new GameDAO(dataSource);
                ArrayList<Game> jogos = gameDao.read(user);
                if (jogos != null){
                    paginaDestino = "/consultarjogos.jsp";
                    request.setAttribute("Jogos", jogos);
                }
                dataSource.getConnection().close();                
            }
            catch(Exception ex){
                System.out.println("Deu algum erro "+ex.getMessage());
            }
        }
        else{
            paginaDestino = "/index.html";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request,response);
    }
}
