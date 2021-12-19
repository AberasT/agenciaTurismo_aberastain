package Servlets;

import Logica.ControladoraLogica;
import Logica.Empleado;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvVerEmpleados", urlPatterns = {"/SvVerEmpleados"})
public class SvVerEmpleados extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ControladoraLogica controladora = new ControladoraLogica();
        List <Empleado> listaEmpleados = controladora.traerEmpleados();
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaEmpleados", listaEmpleados);
        response.sendRedirect("empleados.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
