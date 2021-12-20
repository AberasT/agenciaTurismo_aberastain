package Logica;

import Persistencia.ControladoraPersistencia;
import java.util.Date;
import java.util.List;

//// SEPARADORES PARA CADA CLASE

public class ControladoraLogica {
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    public ControladoraLogica() {
    }

    public void crearEmpleado(String nombre, String apellido, String direccion, String dni, Date fechaNacimiento, String nacionalidad, String celular, String email, String cargo, double sueldo, String nombreUsuario, String contrasenia) {
        Empleado empleado = new Empleado();
        Usuario usuario = new Usuario();
        
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setFecha_nac(fechaNacimiento);
        empleado.setNacionalidad(nacionalidad);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        empleado.setHabilitado(true);
        
        usuario.setNombre_usuario(nombreUsuario);
        usuario.setContrasenia(contrasenia);
        usuario.setEmpleado(empleado);
        usuario.setHabilitado(true);
        
        controlPersis.crearEmpleado(empleado,usuario);
    }
    
    public List<Empleado> traerEmpleados() {
        return controlPersis.traerEmpleados();
    }
    
    public void eliminarEmpleado(int id) {
        Empleado emp = buscarEmpleado(id);
        emp.setHabilitado(false);
        controlPersis.modificarEmpleado(emp);
    }
    
    public List<Cliente> traerClientes() {
        return controlPersis.traerClientes();
    }

    public void crearCliente(String nombre, String apellido, String direccion, String dni, Date fechaNacimiento, String nacionalidad, String celular, String email) {
        Cliente cliente = new Cliente();
        
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setFecha_nac(fechaNacimiento);
        cliente.setNacionalidad(nacionalidad);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setHabilitado(true);

        controlPersis.crearCliente(cliente);
    }

    public Empleado buscarEmpleado(int id) {
        return controlPersis.buscarEmpleado(id);
    }

    public Usuario buscarUsuarioPorEmpleado(Empleado emp) {
        Usuario usuBuscado = null;
        List<Usuario> listaUsuarios = controlPersis.traerUsuarios();
        for (Usuario usu : listaUsuarios) {
            if (emp.getId_empleado() == usu.getEmpleado().getId_empleado()) usuBuscado = usu;
        }
        return usuBuscado;
    }

    public void modificarEmpleado(int id, String nombre, String apellido, String direccion, String dni, Date fechaNacimiento, String nacionalidad, String celular, String email, String cargo, double sueldo, String nombreUsuario, String contrasenia) {
        Empleado empleado = controlPersis.buscarEmpleado(id);
        Usuario usuario = buscarUsuarioPorEmpleado(empleado);
        
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setDireccion(direccion);
        empleado.setDni(dni);
        empleado.setFecha_nac(fechaNacimiento);
        empleado.setNacionalidad(nacionalidad);
        empleado.setCelular(celular);
        empleado.setEmail(email);
        empleado.setCargo(cargo);
        empleado.setSueldo(sueldo);
        empleado.setHabilitado(true);
        
        usuario.setNombre_usuario(nombreUsuario);
        usuario.setContrasenia(contrasenia);
        usuario.setEmpleado(empleado);
        usuario.setHabilitado(true);
        controlPersis.modificarEmpleado(empleado,usuario);
    }

    public Cliente buscarCliente(int id) {
        return controlPersis.buscarCliente(id);
    }

    public void modificarCliente(int id, String nombre, String apellido, String direccion, String dni, Date fechaNacimiento, String nacionalidad, String celular, String email) {
        Cliente cliente = controlPersis.buscarCliente(id);
        
        cliente.setNombre(nombre);
        cliente.setApellido(apellido);
        cliente.setDireccion(direccion);
        cliente.setDni(dni);
        cliente.setFecha_nac(fechaNacimiento);
        cliente.setNacionalidad(nacionalidad);
        cliente.setCelular(celular);
        cliente.setEmail(email);
        cliente.setHabilitado(true);

        controlPersis.modificarCliente(cliente);
    }

    public void eliminarCliente(int id) {
        Cliente cli = buscarCliente(id);
        cli.setHabilitado(false);
        controlPersis.modificarCliente(cli);
    }
    
    // SERVICIOS

    public List<Servicio> traerServicios() {
        return controlPersis.traerServicios();
    }

    public void crearServicio(String nombre, String descripcion, String destino, Date fechaSer, double costo) {
        Servicio ser = new Servicio();
        
        ser.setNombre(nombre);
        ser.setDescripcion_breve(descripcion);
        ser.setDestino_servicio(destino);
        ser.setFecha_servicio(fechaSer);
        ser.setCosto_servicio(costo);
        ser.setHabilitado(true);

        controlPersis.crearServicio(ser);
    }

    public void modificarServicio(int id, String nombre, String descripcion, String destino, Date fechaSer, double costo) {
        Servicio servicio = controlPersis.buscarServicio(id);
        
        servicio.setNombre(nombre);
        servicio.setDescripcion_breve(descripcion);
        servicio.setDestino_servicio(destino);
        servicio.setFecha_servicio(fechaSer);
        servicio.setCosto_servicio(costo);
        servicio.setHabilitado(true);

        controlPersis.modificarServicio(servicio);
    }

    public Servicio buscarServicio(int id) {
        return controlPersis.buscarServicio(id);
    }

    public void eliminarServicio(int id) {
        Servicio ser = buscarServicio(id);
        ser.setHabilitado(false);
        controlPersis.modificarServicio(ser);
    }
    
    // PAQUETES

    public void crearPaquete(List<Servicio> listaServiciosIncluidos, double costoTotal) {
        Paquete paq = new Paquete();
        
        paq.setLista_servicios_incluidos(listaServiciosIncluidos);
        paq.setCosto_paquete(costoTotal);
        paq.setHabilitado(true);
        
        controlPersis.crearPaquete(paq);
    }

    public List<Paquete> traerPaquetes() {
        return controlPersis.traerPaquetes();
    }

    public Paquete buscarPaquete(int id) {
        return controlPersis.buscarPaquete(id);
    }

    public void modificarPaquete(int id, List<Servicio> listaServiciosIncluidos, double costoTotal) {
        Paquete paq = controlPersis.buscarPaquete(id);
        
        paq.setLista_servicios_incluidos(listaServiciosIncluidos);
        paq.setCosto_paquete(costoTotal);
        paq.setHabilitado(true);
        
        controlPersis.modificarPaquete(paq);
    }

    public void eliminarPaquete(int id) {
        Paquete paq = buscarPaquete(id);
        paq.setHabilitado(false);
        controlPersis.modificarPaquete(paq);
    }
    
    //VENTAS

    public List<Venta> traerVentas() {
        return controlPersis.traerVentas();
    }

    public void crearVenta(Paquete paqIncluido, String medioPago, Date fecha_venta, Cliente cli, Empleado emp) {
        Venta ven = new Venta();
        ven.setPaquete(paqIncluido);
        ven.setMedio_pago(medioPago);
        ven.setFecha_venta(fecha_venta);
        ven.setCliente(cli);
        ven.setUsuario(buscarUsuarioPorEmpleado(emp));
        
        controlPersis.crearVenta(ven);
    }

    public void crearVenta(Servicio serIncluido, String medioPago, Date fecha_venta, Cliente cli, Empleado emp) {
        Venta ven = new Venta();
        ven.setServicio(serIncluido);
        ven.setMedio_pago(medioPago);
        ven.setFecha_venta(fecha_venta);
        ven.setCliente(cli);
        ven.setUsuario(buscarUsuarioPorEmpleado(emp));
        
        controlPersis.crearVenta(ven);
    }

    public void eliminarVenta(int id) {
        controlPersis.eliminarVenta(id);
    }

    public Venta buscarVenta(int id) {
        return controlPersis.buscarVenta(id);
    }

    public void modificarVenta(int id, Paquete paqIncluido, String medioPago, Date fecha_venta, Cliente cli, Empleado emp) {
        Venta ven = buscarVenta(id);
        ven.setPaquete(paqIncluido);
        ven.setMedio_pago(medioPago);
        ven.setFecha_venta(fecha_venta);
        ven.setCliente(cli);
        ven.setUsuario(buscarUsuarioPorEmpleado(emp));
        
        controlPersis.modificarVenta(ven);
    }

}
