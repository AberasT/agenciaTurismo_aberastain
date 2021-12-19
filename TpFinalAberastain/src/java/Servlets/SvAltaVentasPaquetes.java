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

        listaPaquetes = controladora.traerPaquetes();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;
        
        String codigo;
        int cantidadSeleccionados = 0;
        Paquete paqIncluido = new Paquete();
        
        for (Paquete paq : listaPaquetes) {
            if (paq.isHabilitado() && (cantidadSeleccionados < 2)) {
                codigo = Integer.toString(paq.getCodigo_paquete());
                if (request.getParameter(codigo) != null) {
                    cantidadSeleccionados++;
                    paqIncluido = paq;
                }
            }
        }
        if (cantidadSeleccionados != 1 || ((request.getParameter("clienteId")==null) || (request.getParameter("empleadoId") == null)) || fechaString.equals("")) {
            response.sendRedirect("ventas_alta_paquete.jsp");
        } else {
            medioPago = request.getParameter("medioPago");
            Cliente cli = controladora.buscarCliente(parseInt(request.getParameter("clienteId")));
            Empleado emp = controladora.buscarEmpleado(parseInt(request.getParameter("empleadoId")));
            try {
                controladora.crearVenta(paqIncluido,medioPago,sdf.parse(fechaString),cli,emp);
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
