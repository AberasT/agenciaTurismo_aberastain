package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Servicio;
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

@WebServlet(name = "SvAltaVentasServicios", urlPatterns = {"/SvAltaVentasServicios"})
public class SvAltaVentasServicios extends HttpServlet {

    ControladoraLogica controladora = new ControladoraLogica();
    List<Servicio> listaServicios;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession miSesion = request.getSession();
                
        List<Cliente> listaClientes = controladora.traerClientes();
        List<Empleado> listaEmpleados = controladora.traerEmpleados();
        listaServicios = controladora.traerServicios();
        
        miSesion.setAttribute("listaClientes", listaClientes);
        miSesion.setAttribute("listaEmpleados", listaEmpleados);
        miSesion.setAttribute("listaServicios", listaServicios);
        
        response.sendRedirect("ventas_alta_servicio.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;

        Servicio serIncluido = null;
        double costoTotal;
        
        if (request.getParameter("check") != null) {
            serIncluido = controladora.buscarServicio(parseInt(request.getParameter("check")));
        }
        
        if (((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("") || serIncluido == null) {
            response.sendRedirect("ventas_alta_servicio.jsp");
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
                controladora.crearVenta(serIncluido,medioPago,sdf.parse(fechaString),cli,emp,costoTotal);
            } catch (ParseException ex) {
                Logger.getLogger(SvAltaVentasPaquetes.class.getName()).log(Level.SEVERE, null, ex);
            }
            response.sendRedirect("alta_ok.jsp");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
