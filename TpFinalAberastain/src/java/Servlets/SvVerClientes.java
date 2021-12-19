package Servlets;

import Logica.Cliente;
import Logica.ControladoraLogica;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// SV para alta y consulta de los clientes

@WebServlet(name = "SvVerClientes", urlPatterns = {"/SvVerClientes"})
public class SvVerClientes extends HttpServlet {
    
    ControladoraLogica controladora = new ControladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List <Cliente> listaClientes = controladora.traerClientes();
        HttpSession miSesion = request.getSession();
        miSesion.setAttribute("listaClientes", listaClientes);
        response.sendRedirect("clientes.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
