package Servlets;

import Logica.ControladoraLogica;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


///// EXPLICACION DE TRAER LA ID EN EL POST

@WebServlet(name = "SvEliminarEmpleado", urlPatterns = {"/SvEliminarEmpleado"})
public class SvEliminarEmpleado extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        
        int id = parseInt(request.getParameter("id"));

        controladora.eliminarEmpleado(id);

        request.getSession().setAttribute("listaEmpleados", controladora.traerEmpleados());
        response.sendRedirect("empleados.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
