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
        
        List<Paquete> listaPaquetes = controladora.traerPaquetes();
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String fechaString = request.getParameter("fechaVenta");
        String medioPago;
        
        String codigo;
        int cantidadSeleccionados = 0;
        int id = parseInt(request.getParameter("id"));
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
            response.sendRedirect("ventas_modificar_paquete.jsp");
        } else {
            medioPago = request.getParameter("medioPago");
            Cliente cli = controladora.buscarCliente(parseInt(request.getParameter("clienteId")));
            Empleado emp = controladora.buscarEmpleado(parseInt(request.getParameter("empleadoId")));
            try {
                controladora.modificarVenta(id,paqIncluido,medioPago,sdf.parse(fechaString),cli,emp);
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
