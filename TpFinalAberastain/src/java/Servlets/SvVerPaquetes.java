package Servlets;

import Logica.ControladoraLogica;
import Logica.Paquete;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVerPaquetes", urlPatterns = {"/SvVerPaquetes"})
public class SvVerPaquetes extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladoraLogica controladora = new ControladoraLogica();
        List <Paquete> listaPaquetes = controladora.traerPaquetes();
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaPaquetes", listaPaquetes);
        response.sendRedirect("paquetes.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
