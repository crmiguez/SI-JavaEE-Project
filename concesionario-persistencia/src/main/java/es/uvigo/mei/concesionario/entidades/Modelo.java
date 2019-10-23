package es.uvigo.mei.concesionario.entidades;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Modelo implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;
    
    @ManyToOne
    private TipoModelo tipomodelo;
    
    @ManyToOne
    private Marca marca;
    
    public Modelo() {
		super();
	}

	public Modelo(String nombre, String descripcion, TipoModelo tipomodelo, Marca marca) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipomodelo = tipomodelo;
        this.marca = marca;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public TipoModelo getTipoModelo() {
        return tipomodelo;
    }

    public void setTipoModelo(TipoModelo tipomodelo) {
        this.tipomodelo = tipomodelo;
    }
    
    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.nombre);
        hash = 47 * hash + Objects.hashCode(this.descripcion);
        hash = 47 * hash + Objects.hashCode(this.tipomodelo);
        hash = 47 * hash + Objects.hashCode(this.marca);
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
        final Modelo other = (Modelo) obj;
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.tipomodelo, other.tipomodelo)) {
            return false;
        }
        if (!Objects.equals(this.marca, other.marca)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo{" + "id=" + id + ", nombre=" + nombre + ", tipomodelo=" + tipomodelo + ", marca="+ marca +'}';
    }
    

}
