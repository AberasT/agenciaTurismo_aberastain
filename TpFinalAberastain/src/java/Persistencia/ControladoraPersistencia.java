package Persistencia;

import Logica.Cliente;
import Logica.Empleado;
import Logica.Paquete;
import Logica.Servicio;
import Logica.Usuario;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//// SEPARADORES PARA CADA CLASE

public class ControladoraPersistencia {
    EmpleadoJpaController empleadoJpa = new EmpleadoJpaController();
    UsuarioJpaController usuarioJpa = new UsuarioJpaController();
    ClienteJpaController clienteJpa = new ClienteJpaController();
    ServicioJpaController servicioJpa = new ServicioJpaController();
    PaqueteJpaController paqueteJpa = new PaqueteJpaController();
    
    public void crearEmpleado(Empleado empleado, Usuario usuario) {
        empleadoJpa.create(empleado);
        usuarioJpa.create(usuario);
    }
    
    /*public EmpleadoJpaController() {
        emf = Persistence.createEntityManagerFactory("TpFinalAberastainPU");
    } */

    public List<Empleado> traerEmpleados() {
        return empleadoJpa.findEmpleadoEntities();
    }

    public List<Cliente> traerClientes() {
        return clienteJpa.findClienteEntities();
    }

    public void crearCliente(Cliente cliente) {
        clienteJpa.create(cliente);
    }

    public Empleado buscarEmpleado(int id) {
        return empleadoJpa.findEmpleado(id);
    }

    public Usuario buscarUsuarioPorEmpleado(int id) {
        return usuarioJpa.findUsuario(id);
    }

    public List<Usuario> traerUsuarios() {
        return usuarioJpa.findUsuarioEntities();
    }

    public void modificarEmpleado(Empleado empleado, Usuario usuario) {
        try {
            empleadoJpa.edit(empleado);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            usuarioJpa.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente buscarCliente(int id) {
        return clienteJpa.findCliente(id);
    }

    public void modificarCliente(Cliente cliente) {
        try {
            clienteJpa.edit(cliente);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modificarEmpleado(Empleado emp) {
        try {
            empleadoJpa.edit(emp);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // SERVICIOS

    public List<Servicio> traerServicios() {
        return servicioJpa.findServicioEntities();
    }

    public void crearServicio(Servicio ser) {
        servicioJpa.create(ser);
    }

    public Servicio buscarServicio(int id) {
        return servicioJpa.findServicio(id);
    }

    public void modificarServicio(Servicio servicio) {
        try {
            servicioJpa.edit(servicio);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // PAQUETES
    
    public void crearPaquete(Paquete paq) {
        paqueteJpa.create(paq);
    }

    public List<Paquete> traerPaquetes() {
        return paqueteJpa.findPaqueteEntities();
    }

    public Paquete buscarPaquete(int id) {
        return paqueteJpa.findPaquete(id);
    }

    public void modificarPaquete(Paquete paq) {
        try {
            paqueteJpa.edit(paq);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
