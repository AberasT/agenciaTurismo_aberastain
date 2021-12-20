package Servlets;

import Logica.ControladoraLogica;
import Logica.Venta;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvRecaudaciones", urlPatterns = {"/SvRecaudaciones"})
public class SvRecaudaciones extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();
    List <Venta> listaVentas;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession miSesion = request.getSession();
        listaVentas = controladora.traerVentas();
        Date fechaInf = null, fechaSup = null;
        
        miSesion.setAttribute("fechaInf", fechaInf);
        miSesion.setAttribute("fechaSup", fechaSup);
        miSesion.setAttribute("listaVentas", listaVentas);
        response.sendRedirect("index.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession miSesion = request.getSession();
        listaVentas = controladora.traerVentas();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        Date fechaInf = null, fechaSup = null;
        
        try {
            if (!request.getParameter("fechaInf").equals("")) fechaInf = sdf.parse(request.getParameter("fechaInf"));
        } catch (ParseException ex) {
            Logger.getLogger(SvRecaudaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            if (!request.getParameter("fechaSup").equals("")) fechaSup = sdf.parse(request.getParameter("fechaSup"));
        } catch (ParseException ex) {
            Logger.getLogger(SvRecaudaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        miSesion.setAttribute("fechaInf", fechaInf);
        miSesion.setAttribute("fechaSup", fechaSup);
        miSesion.setAttribute("listaVentas", listaVentas);
        response.sendRedirect("index.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
