package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.TipoModelo;

public class TipoModeloDAO {
	private EntityManager em;

	public TipoModeloDAO(EntityManager em) {
		super();
		System.out.println("Entra con " + em.getProperties().toString());
		this.em = em;
	}

	public TipoModelo crear(TipoModelo nuevoTipoModelo) throws RollbackException {
		TipoModelo tipoModeloCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoTipoModelo);
			tipoModeloCreado = nuevoTipoModelo;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return tipoModeloCreado;
	}

	public TipoModelo actualizar(TipoModelo tipoModelo) {
		TipoModelo tipoModeloModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			tipoModeloModificado = em.merge(tipoModelo);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return tipoModeloModificado;
	}

	public void eliminar(TipoModelo tipoModelo) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(tipoModelo);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public TipoModelo buscarID(String ID) {
		return em.find(TipoModelo.class, ID);
	}

	public List<TipoModelo> buscarTodos() {
		TypedQuery<TipoModelo> q = em.createQuery("SELECT tmdl FROM Modelo AS tmdl", TipoModelo.class);
		return q.getResultList();
	}

	public List<TipoModelo> buscarPorNombre(String patron) {
		TypedQuery<TipoModelo> q = em.createQuery("SELECT tmdl FROM Modelo AS tmdl WHERE tmdl.nombre LIKE :patron", TipoModelo.class);
		q.setParameter("patron", "%" + patron + "%");
		return q.getResultList();
	}
	
	public List<TipoModelo> buscarPorDescripcion(String descripcion) {
		TypedQuery<TipoModelo> q = em.createQuery("SELECT tmdl FROM Modelo AS con WHERE tmdl.descripcion LIKE :descripcion",
				TipoModelo.class);
		q.setParameter("descripcion", "%" + descripcion + "%");
		return q.getResultList();
	}

}