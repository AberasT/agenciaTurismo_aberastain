package Servlets;

import Logica.ControladoraLogica;
import Logica.Servicio;
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

@WebServlet(name = "SvModificarServicio", urlPatterns = {"/SvModificarServicio"})
public class SvModificarServicio extends HttpServlet {
    
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
        String descripcion = request.getParameter("descripcion");
        String destino = request.getParameter("destino");
        double costo = parseDouble(request.getParameter("costo"));
        Date fechaSer = null;
        
        try {
            fechaSer = formato.parse(request.getParameter("fechaServicio"));
        } catch (ParseException ex) {
            Logger.getLogger(SvModificarServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        controladora.modificarServicio(id,nombre,descripcion,destino,fechaSer,costo);

        request.getSession().setAttribute("listaServicios", controladora.traerServicios());
        response.sendRedirect("servicios.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int id = parseInt(request.getParameter("id"));
        Servicio servicio = controladora.buscarServicio(id);
        
        sesion.setAttribute("servicio", servicio);
        response.sendRedirect("servicios_modificar.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
