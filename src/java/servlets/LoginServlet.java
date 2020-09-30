/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DataSource;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author profpa2
 */
public class LoginServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email;
        String senha;
        email = request.getParameter("txtEmail");
        senha = request.getParameter("txtSenha");
        String paginaDestino;
        // conectei no banco
        DataSource dataSource = new DataSource();
        // vou recuperar um usuario
        UserDAO userDAO = new UserDAO(dataSource);
        // efetivamente recupero do banco
        User user = userDAO.read(email, senha);
        try{
            dataSource.getConnection().close();
        }
        catch(Exception ex){
            System.out.println("erro ao desconectar");
        }
        
        if (user != null ){  // o usuario existe
            request.getSession().setAttribute("User",user);
            System.out.println("usuario na sessao");
            paginaDestino="/home.jsp";
        }
        else{
            paginaDestino = "/erro.jsp";
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
        
       
    }


}
