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
    HttpSession sesion = request.getSession();
    String user = (String) sesion.getAttribute("user");
    if (user == null) {
        response.sendRedirect("sesion.jsp");
    } else { %>
    <div id="barra-info" class="flex-fila">
        <h2>AGENCIA DE TURISMO</h2>
        <a class="flex-fila" href="https://github.com/AberasT" target="_blank">github.com/AberasT<img id="imagen-gh" src="img/github_white.png"></a>
    </div>
    <div id="contenedor-general">
        <div id="barra-menu" class="flex-columna">
            <a href="index.jsp" class="boton-menu">INICIO</a>
            <a href="usuario.jsp" class="boton-menu">USUARIO</a>
            <a href="ventas.jsp" class="boton-menu">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu seleccionado">SERVICIOS</a>
            <a href="paquetes.jsp" class="boton-menu">PAQUETES</a>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvCliente" method="GET">
                <a href="SvCliente" class="boton-menu">CLIENTES</a>
            </form> 
        </div>
        <div class="contenido-principal">
            <div class="contenedor-formulario">
                <form class="flex-columna" action="SvAltaServicio" method="POST">
                    <h2>Formulario de alta</h2>
                    <div>
                        <p class="flex-fila "><label>Nombre</label><input type="text" name="nombre"></p>
                        <p class="flex-fila "><label>Descripción</label><input type="text" name="descripcion"></p>
                        <p class="flex-fila "><label>Destino</label><input type="text" name="destino"></p>
                        <p class="flex-fila "><label>Fecha de servicio</label><input type="date" name="fechaServicio"></p>
                        <p class="flex-fila "><label>Costo</label><input type="number" name="costo"></p>
                    </div>
                    <button class="boton-submit" type="submit">CARGAR SERVICIO</button>
                </form>
            </div>
        </div>
    </div>
    <script src="./assets/script.js"></script>
    <% }%>
</body>
</html>