package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Paquete;
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

@WebServlet(name = "SvAltaVentasPaquetes", urlPatterns = {"/SvAltaVentasPaquetes"})
public class SvAltaVentasPaquetes extends HttpServlet {

    ControladoraLogica controladora = new ControladoraLogica();
    List<Paquete> listaPaquetes;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession miSesion = request.getSession();
                
        List<Cliente> listaClientes = controladora.traerClientes();
        List<Empleado> listaEmpleados = controladora.traerEmpleados();
        listaPaquetes = controladora.traerPaquetes();
        
        miSesion.setAttribute("listaClientes", listaClientes);
        miSesion.setAttribute("listaEmpleados", listaEmpleados);
        miSesion.setAttribute("listaPaquetes", listaPaquetes);
        
        response.sendRedirect("ventas_alta_paquete.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;
        
        Paquete paqIncluido = null;
        double costoTotal;
        
        if (request.getParameter("check") != null) {
            paqIncluido = controladora.buscarPaquete(parseInt(request.getParameter("check")));
        }
        
        if (((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("") || paqIncluido == null) {
            response.sendRedirect("ventas_alta_paquete.jsp");
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
                controladora.crearVenta(paqIncluido,medioPago,sdf.parse(fechaString),cli,emp,costoTotal);
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
