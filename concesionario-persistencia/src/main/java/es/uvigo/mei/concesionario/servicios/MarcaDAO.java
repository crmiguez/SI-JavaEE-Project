package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Marca;

public class MarcaDAO {
	private EntityManager em;

	public MarcaDAO(EntityManager em) {
		super();
		System.out.println("Entra con " + em.getProperties().toString());
		this.em = em;
	}

	public Marca crear(Marca nuevaMarca) throws RollbackException {
		Marca marcaCreada = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevaMarca);
			marcaCreada = nuevaMarca;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return marcaCreada;
	}

	public Marca actualizar(Marca marca) {
		Marca marcaModificada = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			marcaModificada = em.merge(marca);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return marcaModificada;
	}

	public void eliminar(Marca marca) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(marca);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public Marca buscarID(String ID) {
		return em.find(Marca.class, ID);
	}

	public List<Marca> buscarTodos() {
		TypedQuery<Marca> q = em.createQuery("SELECT mrc FROM Marca AS mrc", Marca.class);
		return q.getResultList();
	}

	public List<Marca> buscarPorNombre(String patron) {
		TypedQuery<Marca> q = em.createQuery("SELECT mrc FROM Marca AS mrc WHERE mrc.nombre LIKE :patron", Marca.class);
		q.setParameter("patron", "%" + patron + "%");
		return q.getResultList();
	}
	
	public List<Marca> buscarPorAnhoFundacion(String anho) {
		TypedQuery<Marca> q = em.createQuery("SELECT mrc FROM Marca AS mrc WHERE mrc.anhofundacion LIKE :anho", Marca.class);
		q.setParameter("anho", "%" + anho + "%");
		return q.getResultList();
	}
	
	public List<Marca> buscarPorPaisOrigen(String pais) {
		TypedQuery<Marca> q = em.createQuery("SELECT mrc FROM Marca AS mrc WHERE mrc.paisorigen LIKE :pais", Marca.class);
		q.setParameter("pais", "%" + pais + "%");
		return q.getResultList();
	}
	
	

}