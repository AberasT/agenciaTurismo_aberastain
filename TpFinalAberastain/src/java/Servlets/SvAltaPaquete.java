package Servlets;

import Logica.ControladoraLogica;
import Logica.Servicio;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvAltaPaquete", urlPatterns = {"/SvAltaPaquete"})
public class SvAltaPaquete extends HttpServlet {

    ControladoraLogica controladora = new ControladoraLogica();
    List<Servicio> listaServicios;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession miSesion = request.getSession();
        listaServicios = controladora.traerServicios();
        miSesion.setAttribute("listaServicios", listaServicios);
        response.sendRedirect("paquetes_alta.jsp");
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        
        List<Servicio> listaServiciosIncluidos = new ArrayList();
        listaServicios = controladora.traerServicios();
        
        String codigo;
        double costoTotal = 0;
        
        for (Servicio ser : listaServicios) {
            if (ser.isHabilitado()) {
                codigo = Integer.toString(ser.getCodigo());
                if (request.getParameter(codigo) != null) {
                    listaServiciosIncluidos.add(ser);
                    costoTotal += ser.getCosto_servicio();
                }
            }
            
        }
        costoTotal *= 0.9;

        if (listaServiciosIncluidos.size() < 2) {
            response.sendRedirect("paquetes_alta.jsp");
        } else {
            controladora.crearPaquete(listaServiciosIncluidos,costoTotal);
            response.sendRedirect("alta_ok.jsp");
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
