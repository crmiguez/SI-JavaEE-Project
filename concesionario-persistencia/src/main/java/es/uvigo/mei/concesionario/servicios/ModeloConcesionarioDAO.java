package es.uvigo.mei.concesionario.servicios;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import es.uvigo.mei.concesionario.entidades.Concesionario;
import es.uvigo.mei.concesionario.entidades.Modelo;
import es.uvigo.mei.concesionario.entidades.ModeloConcesionario;
import es.uvigo.mei.concesionario.entidades.ModeloConcesionarioId;
import net.bytebuddy.implementation.bytecode.Throw;

public class ModeloConcesionarioDAO {
	private EntityManager em;

	public ModeloConcesionarioDAO(EntityManager em) {
		super();
		this.em = em;
	}

	public ModeloConcesionario crear(ModeloConcesionario nuevoModeloConcesionario) throws RollbackException {
		ModeloConcesionario modeloConcesionarioCreado = null;
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(nuevoModeloConcesionario);
			modeloConcesionarioCreado = nuevoModeloConcesionario;
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return modeloConcesionarioCreado;
	}

	public ModeloConcesionario actualizar(ModeloConcesionario modeloConcesionario) {
		ModeloConcesionario modeloConcesionarioModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			modeloConcesionarioModificado = em.merge(modeloConcesionario);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return modeloConcesionarioModificado;
	}

	public void eliminar(ModeloConcesionario modeloConcesionario) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(modeloConcesionario);
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}

	public List<ModeloConcesionario> buscarTodos() {
		TypedQuery<ModeloConcesionario> q = em.createQuery("SELECT a FROM Almacen AS a", ModeloConcesionario.class);
		return q.getResultList();
	}

	public List<ModeloConcesionario> buscarStockModelos(Concesionario concesionario) {
		TypedQuery<ModeloConcesionario> q = em.createQuery(
				"SELECT mc FROM ModeloConcesionario AS mc WHERE mc.concesionario.id = :idConcesionario", ModeloConcesionario.class);
		q.setParameter("idConcesionario", concesionario.getId());
		return q.getResultList();
	}

	public ModeloConcesionario actualizarStockModelo(Modelo modelo, Concesionario concesionario, int nuevoStock) {
		ModeloConcesionario stockModeloModificado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ModeloConcesionario stockModelo = em.find(ModeloConcesionario.class,
					new ModeloConcesionarioId(modelo.getId(), concesionario.getId()));
			if (stockModelo != null) {
				stockModelo.setStock(nuevoStock);
				stockModeloModificado = em.merge(stockModelo);
			}
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return stockModeloModificado;
	}

	public ModeloConcesionario crearStockModelo(Modelo modelo, Concesionario concesionario, int stock) {
		ModeloConcesionario stockModeloCreado = null;

		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ModeloConcesionario stockModelo = em.find(ModeloConcesionario.class,
					new ModeloConcesionarioId(modelo.getId(), concesionario.getId()));
			if (stockModelo == null) {
				// No exite stock del producto en el almacen
				stockModelo = new ModeloConcesionario(modelo, concesionario, stock);
				em.persist(stockModelo);
				stockModeloCreado = stockModelo;
			} else {
				actualizarStockModelo(modelo, concesionario, stock);
			}
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
		return stockModeloCreado;
	}

	public void eliminarStockModelo(Modelo modelo, Concesionario concesionario) {
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			ModeloConcesionario stockModelo = em.find(ModeloConcesionario.class,
					new ModeloConcesionarioId(modelo.getId(), concesionario.getId()));
			if (stockModelo != null) {
				em.remove(stockModelo);
			}
			tx.commit();
		} catch (Exception ex) {
			if ((tx != null) && (tx.isActive())) {
				tx.rollback();
				throw new RollbackException(ex);
			}
		}
	}
}