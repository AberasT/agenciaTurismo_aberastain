package Servlets;

import Logica.ControladoraLogica;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvEliminarVenta", urlPatterns = {"/SvEliminarVenta"})
public class SvEliminarVenta extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = parseInt(request.getParameter("id"));
        
        controladora.eliminarVenta(id);
        request.getSession().setAttribute("listaVentas", controladora.traerVentas());
        response.sendRedirect("ventas.jsp");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
