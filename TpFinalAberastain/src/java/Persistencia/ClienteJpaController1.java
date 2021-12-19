/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Logica.Cliente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Aaber
 */
public class ClienteJpaController1 implements Serializable {

    public ClienteJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cliente cliente) {
        if (cliente.getLista_compras() == null) {
            cliente.setLista_compras(new ArrayList<Venta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Venta> attachedLista_compras = new ArrayList<Venta>();
            for (Venta lista_comprasVentaToAttach : cliente.getLista_compras()) {
                lista_comprasVentaToAttach = em.getReference(lista_comprasVentaToAttach.getClass(), lista_comprasVentaToAttach.getNum_venta());
                attachedLista_compras.add(lista_comprasVentaToAttach);
            }
            cliente.setLista_compras(attachedLista_compras);
            em.persist(cliente);
            for (Venta lista_comprasVenta : cliente.getLista_compras()) {
                lista_comprasVenta.getCliente().add(cliente);
                lista_comprasVenta = em.merge(lista_comprasVenta);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cliente cliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cliente persistentCliente = em.find(Cliente.class, cliente.getId_cliente());
            List<Venta> lista_comprasOld = persistentCliente.getLista_compras();
            List<Venta> lista_comprasNew = cliente.getLista_compras();
            List<Venta> attachedLista_comprasNew = new ArrayList<Venta>();
            for (Venta lista_comprasNewVentaToAttach : lista_comprasNew) {
                lista_comprasNewVentaToAttach = em.getReference(lista_comprasNewVentaToAttach.getClass(), lista_comprasNewVentaToAttach.getNum_venta());
                attachedLista_comprasNew.add(lista_comprasNewVentaToAttach);
            }
            lista_comprasNew = attachedLista_comprasNew;
            cliente.setLista_compras(lista_comprasNew);
            cliente = em.merge(cliente);
            for (Venta lista_comprasOldVenta : lista_comprasOld) {
                if (!lista_comprasNew.contains(lista_comprasOldVenta)) {
                    lista_comprasOldVenta.getCliente().remove(cliente);
                    lista_comprasOldVenta = em.merge(lista_comprasOldVenta);
                }
            }
            for (Venta lista_comprasNewVenta : lista_comprasNew) {
                if (!lista_comprasOld.contains(lista_comprasNewVenta)) {
                    lista_comprasNewVenta.getCliente().add(cliente);
                    lista_comprasNewVenta = em.merge(lista_comprasNewVenta);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = cliente.getId_cliente();
                if (findCliente(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
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
            Cliente cliente;
            try {
                cliente = em.getReference(Cliente.class, id);
                cliente.getId_cliente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.", enfe);
            }
            List<Venta> lista_compras = cliente.getLista_compras();
            for (Venta lista_comprasVenta : lista_compras) {
                lista_comprasVenta.getCliente().remove(cliente);
                lista_comprasVenta = em.merge(lista_comprasVenta);
            }
            em.remove(cliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cliente> findClienteEntities() {
        return findClienteEntities(true, -1, -1);
    }

    public List<Cliente> findClienteEntities(int maxResults, int firstResult) {
        return findClienteEntities(false, maxResults, firstResult);
    }

    private List<Cliente> findClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cliente.class));
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

    public Cliente findCliente(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cliente> rt = cq.from(Cliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
