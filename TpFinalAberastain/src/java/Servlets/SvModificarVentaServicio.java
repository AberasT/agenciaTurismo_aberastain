package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Servicio;
import Logica.Venta;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvModificarVentaServicio", urlPatterns = {"/SvModificarVentaServicio"})
public class SvModificarVentaServicio extends HttpServlet {

    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;
        double costoTotal;
        int id = parseInt(request.getParameter("id"));
        Servicio serIncluido = null;
        
        if (request.getParameter("check") != null) {
            serIncluido = controladora.buscarServicio(parseInt(request.getParameter("check")));
        }
        if (((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("") || serIncluido == null) {
            response.sendRedirect("ventas_modificar_servicios.jsp");
        } else {
            medioPago = request.getParameter("medioPago");
            switch(medioPago) {
                case "efectivo" : 
                    costoTotal = serIncluido.getCosto_servicio();
                    break;
                case "debito" : 
                    costoTotal = serIncluido.getCosto_servicio()*1.03;
                    break;
                case "credito" : 
                    costoTotal = serIncluido.getCosto_servicio()*1.09;
                    break;
                case "monederoVirtual" : 
                    costoTotal = serIncluido.getCosto_servicio();
                    break;
                default :
                    costoTotal = serIncluido.getCosto_servicio()*1.0245;
            }
            // Truncamiento a dos decimales
            costoTotal = costoTotal * Math.pow(10, 2);
            costoTotal = Math.floor(costoTotal);
            costoTotal = costoTotal / Math.pow(10, 2);
            Cliente cli = controladora.buscarCliente(parseInt(request.getParameter("clienteId")));
            Empleado emp = controladora.buscarEmpleado(parseInt(request.getParameter("empleadoId")));
            try {
                controladora.modificarVenta(id,serIncluido,medioPago,sdf.parse(fechaString),cli,emp,costoTotal);
            } catch (ParseException ex) {
                Logger.getLogger(SvAltaVentasPaquetes.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("listaVentas", controladora.traerVentas());
            response.sendRedirect("ventas.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession();
        
        int id = parseInt(request.getParameter("id"));
        
        Venta venta = controladora.buscarVenta(id);
        
        List<Servicio> listaServicios = controladora.traerServicios();
        
        sesion.setAttribute("listaServicios", listaServicios);
        sesion.setAttribute("venta", venta);
        
        response.sendRedirect("ventas_modificar_servicios.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
