package es.uvigo.mei.concesionario.entidades;

import java.io.Serializable;
import java.util.Objects;

public class ModeloConcesionarioId implements Serializable {
	
    private Long modelo;
    private Long concesionario;

    
    public ModeloConcesionarioId() {
		super();
	}

	public  ModeloConcesionarioId(Long modelo, Long concesionario) {
		super();
		this.modelo = modelo;
		this.concesionario = concesionario;
	}

	@Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.modelo);
        hash = 97 * hash + Objects.hashCode(this.concesionario);
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
        final ModeloConcesionarioId other = (ModeloConcesionarioId) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        if (!Objects.equals(this.concesionario, other.concesionario)) {
            return false;
        }
        return true;
    }
}
