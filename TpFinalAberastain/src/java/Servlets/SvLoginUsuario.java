package Servlets;

import Logica.ControladoraLogica;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvLoginUsuario", urlPatterns = {"/SvLoginUsuario"})
public class SvLoginUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = request.getParameter("user");
        String contra = request.getParameter("contra");
        ControladoraLogica controladora = new ControladoraLogica();
        boolean autorizado = controladora.verificarUsuario(user,contra);
        
        if (autorizado) {
            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("user", user);
            sesion.setAttribute("contra", contra);
            
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
