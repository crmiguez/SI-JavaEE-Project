package es.uvigo.mei.concesionario.servicios;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Concesionario;

public class ConcesionarioDAO {
	private EntityManager em;

	public ConcesionarioDAO(EntityManager em) {
		super();
		System.out.println("Entra con " + em.getProperties().toString());
		this.em = em;
	}

	public Concesionario crear(Concesionario nuevoConcesionario) throws RollbackException {
		Concesionario concesionarioCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoConcesionario);
			concesionarioCreado = nuevoConcesionario;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return concesionarioCreado;
	}

	public Concesionario actualizar(Concesionario concesionario) {
		Concesionario concesionarioModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			concesionarioModificado = em.merge(concesionario);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return concesionarioModificado;
	}

	public void eliminar(Concesionario concesionario) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(concesionario);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public Concesionario buscarID(String ID) {
		return em.find(Concesionario.class, ID);
	}

	public List<Concesionario> buscarTodos() {
		TypedQuery<Concesionario> q = em.createQuery("SELECT con FROM Concesionario AS con", Concesionario.class);
		return q.getResultList();
	}

	public List<Concesionario> buscarPorNombre(String patron) {
		TypedQuery<Concesionario> q = em.createQuery("SELECT con FROM Concesionario AS con WHERE con.nombre LIKE :patron", Concesionario.class);
		q.setParameter("patron", "%" + patron + "%");
		return q.getResultList();
	}
	
	public List<Concesionario> buscarPorDescripcion(String descripcion) {
		TypedQuery<Concesionario> q = em.createQuery("SELECT con FROM Concesionario AS con WHERE con.descripcion LIKE :descripcion",
				Concesionario.class);
		q.setParameter("descripcion", "%" + descripcion + "%");
		return q.getResultList();
	}

	public List<Concesionario> buscarPorDireccion(String direccion) {
		TypedQuery<Concesionario> q = em.createQuery("SELECT con FROM Concesionario AS con WHERE con.direccion LIKE :direccion",
				Concesionario.class);
		q.setParameter("direccion", "%" + direccion + "%");
		return q.getResultList();
	}

}