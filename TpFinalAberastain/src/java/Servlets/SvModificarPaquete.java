package Servlets;

import Logica.ControladoraLogica;
import Logica.Paquete;
import Logica.Servicio;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarPaquete", urlPatterns = {"/SvModificarPaquete"})
public class SvModificarPaquete extends HttpServlet {

    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Servicio> listaServiciosIncluidos = new ArrayList();
        List<Servicio> listaServicios = controladora.traerServicios();
        
        int id = parseInt(request.getParameter("id"));
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
            response.sendRedirect("paquetes_modificar.jsp");
        } else {
            controladora.modificarPaquete(id,listaServiciosIncluidos,costoTotal);
            request.getSession().setAttribute("listaPaquetes", controladora.traerPaquetes());
            response.sendRedirect("paquetes.jsp");
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        int id = parseInt(request.getParameter("id"));
        
        Paquete paquete = controladora.buscarPaquete(id);
        
        List <Servicio> listaServicios = controladora.traerServicios();
        
        sesion.setAttribute("listaServicios", listaServicios);
        sesion.setAttribute("paquete", paquete);
        
        response.sendRedirect("paquetes_modificar.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
