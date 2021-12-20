<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Venta"%>
<%@page import="Logica.Servicio"%>
<%@page import="Logica.Paquete"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
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
    <%
    HttpSession miSesion = request.getSession();
    String user = (String) miSesion.getAttribute("user");
    if (user == null) {
        response.sendRedirect("sesion.jsp");
    } else { %>
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <form action="SvRecaudaciones" method="GET">
                <a href="SvRecaudaciones" class="boton-menu">INICIO</a>
            </form>
            <form action="SvVerVentas" method="GET">
                <a href="SvVerVentas" class="boton-menu seleccionado">VENTAS</a>
            </form>
            <form action="SvVerServicios" method="GET">
                <a href="SvVerServicios" class="boton-menu">SERVICIOS</a>
            </form>
            <form action="SvVerPaquetes" method="GET">
                <a href="SvVerPaquetes" class="boton-menu">PAQUETES</a>
            </form>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvVerClientes" method="GET">
                <a href="SvVerClientes" class="boton-menu">CLIENTES</a>
            </form>
            <form action="SvLoginUsuario" method="GET">
                <a href="SvLoginUsuario" class="boton-menu">SESIÓN</a>
            </form>
        </div>
        <div id="contenido-principal" >
            <div>
                <div class="limiter">
                    <div class="container-table100">
                        <form class="wrap-table100" action="SvModificarVentaPaquete" method="GET">
                            <div class="table100 ver1">
                                <div class="table100-firstcol">
                                    <table>
                                        <thead>
                                                <th class="column1">Paquetes disponibles</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%  List <Cliente> listaClientes = (List) miSesion.getAttribute("listaClientes");
                                                List <Empleado> listaEmpleados = (List) miSesion.getAttribute("listaEmpleados");
                                                Venta venta = (Venta) miSesion.getAttribute("venta");
                                                List <Paquete> listaPaquetes = (List) miSesion.getAttribute("listaPaquetes");
                                                
                                                for (Paquete paq : listaPaquetes) {
                                                    if (paq.isHabilitado()) {
                                            %>
                                            <tr class="row100 body">
                                                <td class="column1"><%=paq.getCodigo_paquete()%></td>
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
                                                    <th class="listaElementos">Servicios incluidos</th>
                                                    <th class="column12">Costo</th>
                                                </tr>
                                            </thead>
                                                <tbody>
                                                <%  String serviciosString = "", checked;
                                                    double costo;
                                                    List<Servicio> listaServiciosIncluidos;
                                                    int id;
                                                    for (Paquete paq : listaPaquetes) { 
                                                        if (paq.isHabilitado()) {%>
                                                    <tr class="row100 body">
                                                    <%  listaServiciosIncluidos = paq.getLista_servicios_incluidos();
                                                        checked = "";
                                                        id = paq.getCodigo_paquete();
                                                        serviciosString = "";
                                                        for (Servicio ser : listaServiciosIncluidos) {
                                                                serviciosString += ser.getNombre() + ", ";
                                                            }
                                                        if (paq.getCodigo_paquete() == venta.getPaquete().getCodigo_paquete()) checked = "checked";
                                                        serviciosString = serviciosString.substring(0,serviciosString.length()-2);
                                                        costo = paq.getCosto_paquete(); %>
                                                        <td class="column2">
                                                            <input type="radio" class="checkbox" name="check" value="<%=id%>" <%=checked%>>
                                                        </td>
                                                        <td class="listaElementos"><%=serviciosString%></td>
                                                        <td class="column12">$<%=costo%></td>
                                                    </tr>
                                                    <%}}%>
                                                </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="color-negro flex-columna contenedor-selectores">
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
                                <p class="p-info">Efectivo: Sin comisión</p>    
                                <p class="p-info">Tarjeta de Débito: 3%</p>     
                                <p class="p-info">Tarjeta de Crédito: 9%</p>
                                <p class="p-info">Monedero Virtual: Sin comisión</p>     
                                <p class="p-info">Transferencia: 2.45%</p>
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
                                    <%  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); %>
                                <label class="color-negro">Seleccionar fecha<input type="date" name="fechaVenta" value="<%=sdf.format(venta.getFecha_venta())%>"></label>
                                <input type="hidden" name="id" value="<%=venta.getNum_venta()%>">
                                <button class="boton-submit" type="submit">MODIFICAR</button>
                                <h3 class="color-negro">Recuerde seleccionar todos los campos</h3>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
    <% } %>
</body>
</html>