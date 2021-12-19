<%-- 
    Document   : clientes_modificar
    Created on : 17 dic. 2021, 18:15:01
    Author     : Álvaro
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="Logica.Cliente"%>
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
            <a href="ventas.jsp" class="boton-menu">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu">SERVICIOS</a>
            <a href="paquetes.jsp" class="boton-menu">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvCliente" method="GET">
                <a href="SvCliente" class="boton-menu seleccionado">CLIENTES</a>
            </form> 
        </div>
        <div class="contenido-principal">
            <div class="contenedor-formulario">
                <form class="flex-columna" action="SvModificarCliente" method="GET">
                    <%  HttpSession sesion = request.getSession();
                        Cliente cli = (Cliente) sesion.getAttribute("cliente");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                        String fechaString = sdf.format(cli.getFecha_nac());%>
                    <h2>Formulario de modificación</h2>
                    <div>
                        <p class="flex-fila "><label>Nombre</label><input type="text" name="nombre" value="<%=cli.getNombre()%>"></p>
                        <p class="flex-fila "><label>Apellido</label><input type="text" name="apellido" value="<%=cli.getApellido()%>"><p>
                        <p class="flex-fila "><label>Dirección</label><input type="text" name="direccion" value="<%=cli.getDireccion()%>"></p>
                        <p class="flex-fila "><label>DNI</label><input type="text" name="dni" value="<%=cli.getDni()%>"></p>
                        <p class="flex-fila "><label>Fecha de nacimiento</label><input type="date" name="fechaNacimiento" value="<%=fechaString%>"></p>
                        <p class="flex-fila "><label>Nacionalidad</label><input type="text" name="nacionalidad" value="<%=cli.getNacionalidad()%>"></p>
                        <p class="flex-fila "><label>Celular</label><input type="text" name="celular" value="<%=cli.getCelular()%>"></p>
                        <p class="flex-fila "><label>Email</label><input type="text" name="email" value="<%=cli.getEmail()%>"></p>
                    </div>
                    <input type="hidden" name="id" value="<%=cli.getId_cliente()%>">
                    <button class="boton-submit" type="submit">CARGAR</button>
                </form>
            </div>
        </div>
    </div>

    <script src="./assets/script.js"></script>
</body>
</html>