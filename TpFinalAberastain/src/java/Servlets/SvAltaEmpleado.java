package Servlets;

import Logica.ControladoraLogica;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SvAltaEmpleado", urlPatterns = {"/SvAltaEmpleado"})
public class SvAltaEmpleado extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String dni = request.getParameter("dni");
            Date fechaNacimiento = null;
        try {
            fechaNacimiento = formato.parse(request.getParameter("fechaNacimiento"));
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
            String nacionalidad = request.getParameter("nacionalidad");
            String celular = request.getParameter("celular");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");
            double sueldo = parseDouble(request.getParameter("sueldo"));
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasenia = request.getParameter("contrasenia");
            controladora.crearEmpleado(nombre,apellido,direccion,dni,fechaNacimiento,nacionalidad,celular,email,cargo,sueldo,nombreUsuario,contrasenia);

            response.sendRedirect("alta_ok.jsp");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
