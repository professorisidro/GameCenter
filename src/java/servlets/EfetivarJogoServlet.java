/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import dao.DataSource;
import dao.GameDAO;
import java.io.IOException;
import java.security.MessageDigest;
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
public class EfetivarJogoServlet extends HttpServlet {

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String title = request.getParameter("txtTitle");
        String platform = request.getParameter("txtPlatform");

        if (request.getSession().getAttribute("User") != null) {
            try {
                // ---- passo 1 - criar a chave
                MessageDigest digest;
                // carrego o algoritmo
                digest = MessageDigest.getInstance("SHA-256");
                // vou gerar a chave a partir dos bytes do titulo e da plataforma
                String str = title+platform;
                byte[] bytes = digest.digest(str.getBytes());
                // converter a chave para um valor mais "amigável"
                StringBuilder strFinal = new StringBuilder();
                for (byte b: bytes){
                    strFinal.append(Integer.toHexString(0xff & b));
                }
                String result = strFinal.toString(); // essa é a chave!!!!
                // passo 2 - gravar no banco
                DataSource dataSource = new DataSource();
                GameDAO dao = new GameDAO(dataSource);
                // preencher os dados do game
                Game novoGame = new Game();              
                novoGame.setTitle(title);          // veio do formulario
                novoGame.setPlatform(platform);    // veio do formulario
                novoGame.setKey(result);           // geramos no passo 1
                User user = (User)request.getSession().getAttribute("User");  // pegamos o usuario conectado
                novoGame.setUser(user); 
                dao.create(novoGame);              // gravamos no banco
                
                dataSource.getConnection().close();  // fechamos a conexao com o banco
                
                
                
                // passo 3 - exibir a chave para o usuario
                request.setAttribute("Result",result);

            } catch (Exception ex) {
                System.out.println("Erro ao efetivar jogo");
            }
        } else {
            request.setAttribute("Result", "ERROR");
        }

        System.out.println(
                "Title " + title);
        System.err.println(
                "Platform " + platform);
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/resultNewGame.jsp");

        dispatcher.forward(request, response);
    }

}
