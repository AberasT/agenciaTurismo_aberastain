<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Servicio"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.Venta"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <link href="./css/style.css" rel="stylesheet">
    <link href="./css/main.css" rel="stylesheet">
    <title>Agencia de Turismo</title>
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300&display=swap" rel="stylesheet">
</head>
<body>
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <a href="index.jsp" class="boton-menu">INICIO</a>
            <a href="usuario.jsp" class="boton-menu">USUARIO</a>
            <a href="ventas.jsp" class="boton-menu seleccionado">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu">SERVICIOS</a>
            <a href="paquetes.jsp" class="boton-menu">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form> 
        </div>
        <div id="contenido-principal" >
            <div>
                <div class="limiter">
                    <div class="container-table100">
                        <form class="wrap-table100" action="SvModificarVentaServicio" method="GET">
                            <div class="table100 ver1">
                                <div class="table100-firstcol">
                                    <table>
                                        <thead>
                                                <th class="column1">Servicios disponibles</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  HttpSession miSesion = request.getSession();
                                                List <Cliente> listaClientes = (List) miSesion.getAttribute("listaClientes");
                                                List <Empleado> listaEmpleados = (List) miSesion.getAttribute("listaEmpleados");
                                                Venta venta = (Venta) miSesion.getAttribute("venta");
                                                List <Servicio> listaServicios = (List) miSesion.getAttribute("listaServicios");
                                                
                                                for (Servicio ser : listaServicios) {
                                                    if (ser.isHabilitado()) {
                                            %>
                                            <tr class="row100 body">
                                                <td class="column1"><%=ser.getCodigo()%></td>
                                            </tr>
                                            <% }} %>
                                        </tbody>
                                    </table>
                                </div>
                                <div class="wrap-table100-nextcols js-pscroll">
                                    <div class="table100-nextcols">
                                        <table>
                                            <thead>
                                                <tr class="row100 head">
                                                    <th class="column2">Incluir</th>
                                                    <th class="column10">Nombre</th>
                                                    <th class="column7">Fecha de servicio</th>
                                                    <th id="descripcion">Descripción</th>
                                                    <th class="column10">Destino</th>
                                                    <th class="column12">Costo</th>
                                                </tr>
                                            </thead>
                                                <tbody>
                                                <%  String nombre, descripcion, destino, fechaString, checked;
                                                    int id;
                                                    double costo;
                                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                                                    for (Servicio ser : listaServicios) { 
                                                        if (ser.isHabilitado()) {%>
                                                    <tr class="row100 body">
                                                    <%  id = ser.getCodigo();
                                                        fechaString = sdf.format(ser.getFecha_servicio());
                                                        descripcion = ser.getDescripcion_breve();
                                                        destino = ser.getDestino_servicio();
                                                        nombre = ser.getNombre();
                                                        costo = ser.getCosto_servicio();
                                                        checked = "";
                                                        if (ser.getCodigo() == venta.getServicio().getCodigo()) checked = "checked";%>
                                                        <td class="column2">
                                                            <input type="checkbox" class="checkbox" name="<%=id%>" value="<%=id%>" <%=checked%>>
                                                        </td>
                                                        <td class="column10"><%=nombre%></td>
                                                        <td class="column7"><%=fechaString%></td>
                                                        <td id="descripcion"><%=descripcion%></td>
                                                        <td class="column10"><%=destino%></td>
                                                        <td class="column12">$<%=costo%></td>
                                                    </tr>
                                                    <%}}%>
                                                </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="flex-columna contenedor-selectores">
                                <label class="color-negro">Seleccionar medio de pago
                                    <%  String selected = ""; %>
                                    <select name="medioPago" class="selector"">
                                    <%  if (venta.getMedio_pago().equals("efectivo")) selected = "selected"; else selected = ""; %>
                                        <option value="efectivo" <%=selected%>>EFECTIVO</option>
                                    <%  if (venta.getMedio_pago().equals("debito")) selected = "selected"; else selected = ""; %>
                                        <option value="debito" <%=selected%>>TARJETA DE DÉBITO</option>
                                    <%  if (venta.getMedio_pago().equals("credito")) selected = "selected"; else selected = ""; %>
                                        <option value="credito" <%=selected%>>TARJETA DE CRÉDITO</option>
                                    <%  if (venta.getMedio_pago().equals("monederoVirtual")) selected = "selected"; else selected = ""; %>
                                        <option value="monederoVirtual" <%=selected%>>MONEDERO VIRTUAL</option>
                                    <%  if (venta.getMedio_pago().equals("transferencia")) selected = "selected"; else selected = ""; %>
                                        <option value="transferencia" <%=selected%>>TRANSFERENCIA</option>
                                    </select>
                                </label>
                                    <%  String nombreIdCli, nombreIdEmp; %>
                                <label class="color-negro">Seleccionar cliente
                                    <select name="clienteId" class="selector">
                                    <<%  for (Cliente cli : listaClientes) {
                                            if (cli.isHabilitado()) {
                                                nombreIdCli = cli.getNombre() +" "+ cli.getApellido();
                                                nombreIdCli += " - ID " + cli.getId_cliente();
                                                if (cli.getId_cliente() == venta.getCliente().getId_cliente()) selected = "selected";
                                                else selected = "";
                                        %>
                                        <option value="<%=cli.getId_cliente()%>" <%=selected%>><%=nombreIdCli%></option>
                                    <% }} %>
                                    </select>
                                </label>
                                <label class="color-negro">Seleccionar empleado
                                    <select name="empleadoId" class="selector">
                                    <%  for (Empleado emp : listaEmpleados) {
                                            if (emp.isHabilitado()) {
                                                nombreIdEmp = emp.getNombre() +" "+ emp.getApellido();
                                                nombreIdEmp += " - ID " + emp.getId_empleado();
                                                if (emp.getId_empleado() == venta.getUsuario().getEmpleado().getId_empleado()) selected = "selected";
                                                else selected = "";
                                        %>
                                        <option value="<%=emp.getId_empleado()%>" <%=selected%>><%=nombreIdEmp%></option>
                                    <% }} %>
                                    </select>
                                </label>
                                <label class="color-negro">Seleccionar fecha<input type="date" name="fechaVenta" value="<%=sdf.format(venta.getFecha_venta())%>"></label>
                                <input type="hidden" name="id" value="<%=venta.getNum_venta()%>">
                                <button class="boton-submit" type="submit">MODIFICAR</button>
                                <h3 class="color-negro">Debe seleccionarse un único paquete</h3>
                                <h3 class="color-negro">Recuerde seleccionar todos los campos</h3>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
</body>
</html>