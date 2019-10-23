package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Cliente;

public class ClienteDAO {
	private EntityManager em;

	public ClienteDAO(EntityManager em) {
		super();
		System.out.println("Entra con " + em.getProperties().toString());
		this.em = em;
	}

	public Cliente crear(Cliente nuevoCliente) throws RollbackException {
		Cliente clienteCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoCliente);
			clienteCreado = nuevoCliente;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return clienteCreado;
	}

	public Cliente actualizar(Cliente cliente) {
		Cliente clienteModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			clienteModificado = em.merge(cliente);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return clienteModificado;
	}

	public void eliminar(Cliente cliente) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(cliente);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public Cliente buscarPorDNI(String DNI) {
		return em.find(Cliente.class, DNI);
	}

	public List<Cliente> buscarTodos() {
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente AS c", Cliente.class);
		return q.getResultList();
	}

	public List<Cliente> buscarPorNombre(String patron) {
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente AS c WHERE c.nombre LIKE :patron", Cliente.class);
		q.setParameter("patron", "%" + patron + "%");
		return q.getResultList();
	}

	public List<Cliente> buscarPorLocalidad(String localidad) {
		TypedQuery<Cliente> q = em.createQuery("SELECT c FROM Cliente AS c WHERE c.direccion.localidad LIKE :localidad",
				Cliente.class);
		q.setParameter("localidad", "%" + localidad + "%");
		return q.getResultList();
	}

}
