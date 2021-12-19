package Servlets;

import Logica.ControladoraLogica;
import Logica.Venta;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVerVentas", urlPatterns = {"/SvVerVentas"})
public class SvVerVentas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladoraLogica controladora = new ControladoraLogica();
        HttpSession miSesion = request.getSession();
        
        List <Venta> listaVentas = controladora.traerVentas();
        
        miSesion.setAttribute("listaVentas", listaVentas);
        response.sendRedirect("ventas.jsp");
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
