package Logica;

import Logica.Servicio;
import Logica.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-12-14T18:22:47")
@StaticMetamodel(Paquete.class)
public class Paquete_ { 

    public static volatile ListAttribute<Paquete, Venta> lista_ventas;
    public static volatile SingularAttribute<Paquete, Double> costo_paquete;
    public static volatile SingularAttribute<Paquete, Integer> codigo_paquete;
    public static volatile ListAttribute<Paquete, Servicio> lista_servicios_incluidos;

}