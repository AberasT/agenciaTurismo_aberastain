package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Paquete;
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

@WebServlet(name = "SvModificarVentaPaquete", urlPatterns = {"/SvModificarVentaPaquete"})
public class SvModificarVentaPaquete extends HttpServlet {

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
        Paquete paqIncluido = null;
        
        if (request.getParameter("check") != null) {
            paqIncluido = controladora.buscarPaquete(parseInt(request.getParameter("check")));
        }
        
        if (((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("") || paqIncluido == null) {
            response.sendRedirect("ventas_modificar_paquete.jsp");
        } else {
            medioPago = request.getParameter("medioPago");
            switch(medioPago) {
                case "efectivo" : 
                    costoTotal = paqIncluido.getCosto_paquete();
                    break;
                case "debito" : 
                    costoTotal = paqIncluido.getCosto_paquete()*1.03;
                    break;
                case "credito" : 
                    costoTotal = paqIncluido.getCosto_paquete()*1.09;
                    break;
                case "monederoVirtual" : 
                    costoTotal = paqIncluido.getCosto_paquete();
                    break;
                default :
                    costoTotal = paqIncluido.getCosto_paquete()*1.0245;
            }
            // Truncamiento a dos decimales
            costoTotal = costoTotal * Math.pow(10, 2);
            costoTotal = Math.floor(costoTotal);
            costoTotal = costoTotal / Math.pow(10, 2);
            Cliente cli = controladora.buscarCliente(parseInt(request.getParameter("clienteId")));
            Empleado emp = controladora.buscarEmpleado(parseInt(request.getParameter("empleadoId")));
            try {
                controladora.modificarVenta(id,paqIncluido,medioPago,sdf.parse(fechaString),cli,emp,costoTotal);
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
        
        List <Paquete> listaPaquetes = controladora.traerPaquetes();
        
        sesion.setAttribute("listaPaquetes", listaPaquetes);
        sesion.setAttribute("venta", venta);
        
        response.sendRedirect("ventas_modificar_paquetes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
