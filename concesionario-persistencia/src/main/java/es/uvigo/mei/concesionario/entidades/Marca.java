package es.uvigo.mei.concesionario.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Marca implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String anhofundacion;
    
    private String paisorigen;
    
    
    

    public Marca() {
		super();
	}

	public Marca(String nombre, String anhofundacion, String paisorigen) {
        this.nombre = nombre;
        this.anhofundacion = anhofundacion;
        this.paisorigen = paisorigen;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getAnhoFundacion() {
        return anhofundacion;
    }

    public void setAnhoFundacion(String anhofundacion) {
        this.anhofundacion = anhofundacion;
    }
    
    public String getPaisOrigen() {
        return paisorigen;
    }

    public void setPaisOrigen(String paisorigen) {
        this.paisorigen = paisorigen;
    }

    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.anhofundacion);
        hash = 47 * hash + Objects.hashCode(this.paisorigen);
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
        final Marca other = (Marca) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.anhofundacion, other.anhofundacion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.paisorigen, other.paisorigen)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Marca{" + "id=" + id + ", nombre=" + nombre + ", anhofundacion=" + anhofundacion + 
        		", paisorigen="+ paisorigen +'}';
    }
}
