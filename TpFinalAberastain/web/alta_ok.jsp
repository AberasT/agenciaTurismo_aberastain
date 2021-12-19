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
            <a href="index.jsp" class="boton-menu seleccionado">INICIO</a>
            <a href="usuario.jsp" class="boton-menu">USUARIO</a>
            <a href="ventas.jsp" class="boton-menu">VENTAS</a>
            <a href="servicios.jsp" class="boton-menu">SERVICIOS</a>
            <form action="SvVerPaquetes" method="GET">
                <a href="SvVerPaquetes" class="boton-menu">PAQUETES</a>
            </form>
            <form action="SvVerEmpleados" method="GET">
                <a href="SvVerEmpleados" class="boton-menu">EMPLEADOS</a>
            </form>
            <form action="SvCliente" method="GET">
                <a href="SvCliente" class="boton-menu">CLIENTES</a>
            </form> 
        </div>
        <div id="contenido-principal"">
            <div id="menu-seccion" class="flex-fila">

            </div>
            <div>

            </div>
        </div>
    </div>
    <script>alert("El alta se realizó correctamente");</script>
    <script src="./assets/script.js"></script>
</body>
</html>