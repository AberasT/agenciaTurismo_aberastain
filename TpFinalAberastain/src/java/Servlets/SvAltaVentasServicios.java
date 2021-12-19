package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import Logica.Empleado;
import Logica.Paquete;
import Logica.Servicio;
import java.io.IOException;
import java.io.PrintWriter;
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

        listaServicios = controladora.traerServicios();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;
        
        String codigo;
        int cantidadSeleccionados = 0;
        Servicio serIncluido = new Servicio();
        
        for (Servicio ser : listaServicios) {
            if (ser.isHabilitado() && (cantidadSeleccionados < 2)) {
                codigo = Integer.toString(ser.getCodigo());
                if (request.getParameter(codigo) != null) {
                    cantidadSeleccionados++;
                    serIncluido = ser;
                }
            }
        }
        if (cantidadSeleccionados != 1 || ((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("")) {
            response.sendRedirect("ventas_alta_servicio.jsp");
        } else {
            medioPago = request.getParameter("medioPago");
            Cliente cli = controladora.buscarCliente(parseInt(request.getParameter("clienteId")));
            Empleado emp = controladora.buscarEmpleado(parseInt(request.getParameter("empleadoId")));
            try {
                controladora.crearVenta(serIncluido,medioPago,sdf.parse(fechaString),cli,emp);
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
