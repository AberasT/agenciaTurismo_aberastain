package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Paquete;
import Logica.Servicio;
import java.util.ArrayList;
import java.util.List;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ServicioJpaController implements Serializable {

    public ServicioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public ServicioJpaController() {
        emf = Persistence.createEntityManagerFactory("TpFinalAberastainPU");
    }
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicio servicio) {
        if (servicio.getLista_paquetes() == null) {
            servicio.setLista_paquetes(new ArrayList<Paquete>());
        }
        if (servicio.getLista_ventas() == null) {
            servicio.setLista_ventas(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Paquete> attachedLista_paquetes = new ArrayList<Paquete>();
            for (Paquete lista_paquetesPaqueteToAttach : servicio.getLista_paquetes()) {
                lista_paquetesPaqueteToAttach = em.getReference(lista_paquetesPaqueteToAttach.getClass(), lista_paquetesPaqueteToAttach.getCodigo_paquete());
                attachedLista_paquetes.add(lista_paquetesPaqueteToAttach);
            }
            servicio.setLista_paquetes(attachedLista_paquetes);
            List<Venta> attachedLista_ventas = new ArrayList<Venta>();
            for (Venta lista_ventasVentaToAttach : servicio.getLista_ventas()) {
                lista_ventasVentaToAttach = em.getReference(lista_ventasVentaToAttach.getClass(), lista_ventasVentaToAttach.getNum_venta());
                attachedLista_ventas.add(lista_ventasVentaToAttach);
            }
            servicio.setLista_ventas(attachedLista_ventas);
            em.persist(servicio);
            for (Paquete lista_paquetesPaquete : servicio.getLista_paquetes()) {
                lista_paquetesPaquete.getLista_servicios_incluidos().add(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            for (Venta lista_ventasVenta : servicio.getLista_ventas()) {
                lista_ventasVenta.getServicio().add(servicio);
                lista_ventasVenta = em.merge(lista_ventasVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicio servicio) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio persistentServicio = em.find(Servicio.class, servicio.getCodigo());
            List<Paquete> lista_paquetesOld = persistentServicio.getLista_paquetes();
            List<Paquete> lista_paquetesNew = servicio.getLista_paquetes();
            List<Venta> lista_ventasOld = persistentServicio.getLista_ventas();
            List<Venta> lista_ventasNew = servicio.getLista_ventas();
            List<Paquete> attachedLista_paquetesNew = new ArrayList<Paquete>();
            for (Paquete lista_paquetesNewPaqueteToAttach : lista_paquetesNew) {
                lista_paquetesNewPaqueteToAttach = em.getReference(lista_paquetesNewPaqueteToAttach.getClass(), lista_paquetesNewPaqueteToAttach.getCodigo_paquete());
                attachedLista_paquetesNew.add(lista_paquetesNewPaqueteToAttach);
            }
            lista_paquetesNew = attachedLista_paquetesNew;
            servicio.setLista_paquetes(lista_paquetesNew);
            List<Venta> attachedLista_ventasNew = new ArrayList<Venta>();
            for (Venta lista_ventasNewVentaToAttach : lista_ventasNew) {
                lista_ventasNewVentaToAttach = em.getReference(lista_ventasNewVentaToAttach.getClass(), lista_ventasNewVentaToAttach.getNum_venta());
                attachedLista_ventasNew.add(lista_ventasNewVentaToAttach);
            }
            lista_ventasNew = attachedLista_ventasNew;
            servicio.setLista_ventas(lista_ventasNew);
            servicio = em.merge(servicio);
            for (Paquete lista_paquetesOldPaquete : lista_paquetesOld) {
                if (!lista_paquetesNew.contains(lista_paquetesOldPaquete)) {
                    lista_paquetesOldPaquete.getLista_servicios_incluidos().remove(servicio);
                    lista_paquetesOldPaquete = em.merge(lista_paquetesOldPaquete);
                }
            }
            for (Paquete lista_paquetesNewPaquete : lista_paquetesNew) {
                if (!lista_paquetesOld.contains(lista_paquetesNewPaquete)) {
                    lista_paquetesNewPaquete.getLista_servicios_incluidos().add(servicio);
                    lista_paquetesNewPaquete = em.merge(lista_paquetesNewPaquete);
                }
            }
            for (Venta lista_ventasOldVenta : lista_ventasOld) {
                if (!lista_ventasNew.contains(lista_ventasOldVenta)) {
                    lista_ventasOldVenta.getServicio().remove(servicio);
                    lista_ventasOldVenta = em.merge(lista_ventasOldVenta);
                }
            }
            for (Venta lista_ventasNewVenta : lista_ventasNew) {
                if (!lista_ventasOld.contains(lista_ventasNewVenta)) {
                    lista_ventasNewVenta.getServicio().add(servicio);
                    lista_ventasNewVenta = em.merge(lista_ventasNewVenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = servicio.getCodigo();
                if (findServicio(id) == null) {
                    throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicio servicio;
            try {
                servicio = em.getReference(Servicio.class, id);
                servicio.getCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicio with id " + id + " no longer exists.", enfe);
            }
            List<Paquete> lista_paquetes = servicio.getLista_paquetes();
            for (Paquete lista_paquetesPaquete : lista_paquetes) {
                lista_paquetesPaquete.getLista_servicios_incluidos().remove(servicio);
                lista_paquetesPaquete = em.merge(lista_paquetesPaquete);
            }
            List<Venta> lista_ventas = servicio.getLista_ventas();
            for (Venta lista_ventasVenta : lista_ventas) {
                lista_ventasVenta.getServicio().remove(servicio);
                lista_ventasVenta = em.merge(lista_ventasVenta);
            }
            em.remove(servicio);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicio> findServicioEntities() {
        return findServicioEntities(true, -1, -1);
    }

    public List<Servicio> findServicioEntities(int maxResults, int firstResult) {
        return findServicioEntities(false, maxResults, firstResult);
    }

    private List<Servicio> findServicioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicio.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Servicio findServicio(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicio.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicio> rt = cq.from(Servicio.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
