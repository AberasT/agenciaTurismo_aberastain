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

@WebServlet(name = "SvAltaServicio", urlPatterns = {"/SvAltaServicio"})
public class SvAltaServicio extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        String destino = request.getParameter("destino");
        Date fechaSer = null;
        try {
            fechaSer = formato.parse(request.getParameter("fechaServicio"));
        } catch (ParseException ex) {
            Logger.getLogger(SvAltaServicio.class.getName()).log(Level.SEVERE, null, ex);
        }
        double costo = parseDouble(request.getParameter("costo"));

        controladora.crearServicio(nombre,descripcion,destino,fechaSer,costo);

        response.sendRedirect("alta_ok.jsp");

    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
