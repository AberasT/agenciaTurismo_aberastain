package Servlets;

import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Servicio;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVerServicios", urlPatterns = {"/SvVerServicios"})
public class SvVerServicios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladoraLogica controladora = new ControladoraLogica();
        List <Servicio> listaServicios = controladora.traerServicios();
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaServicios", listaServicios);
        response.sendRedirect("servicios.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
