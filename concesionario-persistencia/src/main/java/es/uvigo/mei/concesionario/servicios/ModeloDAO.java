package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Modelo;

public class ModeloDAO {
	private EntityManager em;

	public ModeloDAO(EntityManager em) {
		super();
		System.out.println("Entra con " + em.getProperties().toString());
		this.em = em;
	}

	public Modelo crear(Modelo nuevoModelo) throws RollbackException {
		Modelo modeloCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoModelo);
			modeloCreado = nuevoModelo;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return modeloCreado;
	}

	public Modelo actualizar(Modelo modelo) {
		Modelo modeloModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			modeloModificado = em.merge(modelo);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return modeloModificado;
	}

	public void eliminar(Modelo modelo) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(modelo);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public Modelo buscarID(String ID) {
		return em.find(Modelo.class, ID);
	}

	public List<Modelo> buscarTodos() {
		TypedQuery<Modelo> q = em.createQuery("SELECT mdl FROM Modelo AS mdl", Modelo.class);
		return q.getResultList();
	}

	public List<Modelo> buscarPorNombre(String patron) {
		TypedQuery<Modelo> q = em.createQuery("SELECT mdl FROM Modelo AS mdl WHERE mdl.nombre LIKE :patron", Modelo.class);
		q.setParameter("patron", "%" + patron + "%");
		return q.getResultList();
	}
	
	public List<Modelo> buscarPorDescripcion(String descripcion) {
		TypedQuery<Modelo> q = em.createQuery("SELECT mdl FROM Modelo AS con WHERE mdl.descripcion LIKE :descripcion",
				Modelo.class);
		q.setParameter("descripcion", "%" + descripcion + "%");
		return q.getResultList();
	}

}