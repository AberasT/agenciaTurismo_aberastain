package Servlets;

import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Usuario;
import java.io.IOException;
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarEmpleado", urlPatterns = {"/SvModificarEmpleado"})
public class SvModificarEmpleado extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        int id = parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String dni = request.getParameter("dni");
        Date fechaNacimiento = null;
        
        try {
            fechaNacimiento = formato.parse(request.getParameter("fechaNacimiento"));
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nacionalidad = request.getParameter("nacionalidad");
        String celular = request.getParameter("celular");
        String email = request.getParameter("email");
        String cargo = request.getParameter("cargo");
        double sueldo = parseDouble(request.getParameter("sueldo"));
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");
        controladora.modificarEmpleado(id,nombre,apellido,direccion,dni,fechaNacimiento,nacionalidad,celular,email,cargo,sueldo,nombreUsuario,contrasenia);

        request.getSession().setAttribute("listaEmpleados", controladora.traerEmpleados());
        response.sendRedirect("empleados.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int id = parseInt(request.getParameter("id"));
        Empleado empleado = controladora.buscarEmpleado(id);
        Usuario usuario = controladora.buscarUsuarioPorEmpleado(empleado);
        
        sesion.setAttribute("empleado", empleado);
        sesion.setAttribute("usuario", usuario);
        response.sendRedirect("empleados_modificar.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
