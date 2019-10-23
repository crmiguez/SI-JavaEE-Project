package es.uvigo.mei.concesionario.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ModeloConcesionarioId.class)
public class ModeloConcesionario implements Serializable {
	@Id
    @ManyToOne
    private Modelo modelo;

    @Id
    @ManyToOne
    private Concesionario concesionario;
    
    private Integer stock;

    public ModeloConcesionario() {
		super();
	}
    
    public ModeloConcesionario(Modelo modelo, Concesionario concesionario, Integer stock) {
        this.modelo = modelo;
        this.concesionario = concesionario;
        this.stock = stock;
    }
    
    public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.modelo);
        hash = 53 * hash + Objects.hashCode(this.concesionario);
        hash = 53 * hash + Objects.hashCode(this.stock);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ModeloConcesionario other = (ModeloConcesionario) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.concesionario, other.concesionario)) {
            return false;
        }
        if (!Objects.equals(this.stock, other.stock)) {
            return false;
        }
        return true;
    }

}
