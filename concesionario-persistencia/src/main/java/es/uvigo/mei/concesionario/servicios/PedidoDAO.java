package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Modelo;
import es.uvigo.mei.concesionario.entidades.Cliente;
import es.uvigo.mei.concesionario.entidades.PedidoCoche;

public class PedidoDAO {
	private EntityManager em;

	public PedidoDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public PedidoCoche crear(PedidoCoche nuevoPedido) throws RollbackException {
		PedidoCoche pedidoCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoPedido);
			pedidoCreado = nuevoPedido;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return pedidoCreado;
	}

	public PedidoCoche actualizar(PedidoCoche pedido) {
		PedidoCoche pedidoModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			pedidoModificado = em.merge(pedido);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return pedidoModificado;
	}

	public void eliminar(PedidoCoche pedido) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(pedido);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public PedidoCoche buscarPorId(Long id) {
		return em.find(PedidoCoche.class, id);
	}

	public List<PedidoCoche> buscarTodos() {
		TypedQuery<PedidoCoche> q = em.createQuery("SELECT p FROM PedidoCoche AS p", PedidoCoche.class);
		return q.getResultList();
	}

	public List<PedidoCoche> buscarPorCliente(Cliente cliente) {
		TypedQuery<PedidoCoche> q = em.createQuery("SELECT p FROM Pedido AS p WHERE p.cliente.dni = :dniCliente", PedidoCoche.class);
		q.setParameter("dniCliente", cliente.getDNI());
		return q.getResultList();
	}
	
	public List<PedidoCoche> buscarPorModelo(Modelo modelo) {
		TypedQuery<PedidoCoche> q = em.createQuery("SELECT p FROM Pedido AS p WHERE p.modelo.id = :idModelo", PedidoCoche.class);
		q.setParameter("idModelo", modelo.getId());
		return q.getResultList();
	}
}