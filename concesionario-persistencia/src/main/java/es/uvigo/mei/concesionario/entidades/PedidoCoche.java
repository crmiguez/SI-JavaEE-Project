package es.uvigo.mei.concesionario.entidades;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity

public class PedidoCoche implements Serializable {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaPedido;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

    @Enumerated(EnumType.STRING)
    private EstadoPedido estado;

    @ManyToOne
    private Cliente cliente;
    
    @ManyToOne
    private Modelo modelo;
    
    @ManyToOne
    private Concesionario concesionario;

    
    public PedidoCoche() {
        super();
    }

    public PedidoCoche(Date fechaPedido, Cliente cliente, Concesionario concesionario) {
        this.cliente = cliente;
        this.fechaPedido = fechaPedido;
        this.estado = EstadoPedido.PENDIENTE;
        this.fechaEntrega = null;
        this.concesionario = concesionario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    
    public Date getFechaEntrega() {
        return fechaPedido;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public void setEstado(EstadoPedido estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public Concesionario getConcesionario() {
        return concesionario;
    }

    public void setConcesionario(Concesionario concesionario) {
        this.concesionario = concesionario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.fechaPedido);
        hash = 29 * hash + Objects.hashCode(this.fechaEntrega);
        hash = 29 * hash + Objects.hashCode(this.estado);
        hash = 29 * hash + Objects.hashCode(this.cliente);
        hash = 29 * hash + Objects.hashCode(this.concesionario);
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
        final PedidoCoche other = (PedidoCoche) obj;
        if (!Objects.equals(this.fechaPedido, other.fechaPedido)) {
            return false;
        }
        if (!Objects.equals(this.fechaEntrega, other.fechaEntrega)) {
            return false;
        }
        if (this.estado != other.estado) {
            return false;
        }
        if (!Objects.equals(this.cliente, other.cliente)) {
            return false;
        }
        if (!Objects.equals(this.concesionario, other.concesionario)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "PedidoCoche{" + "id=" + id + ", fechaPedido=" + fechaPedido + ", fechaEntrega=" + fechaEntrega + ", estado=" + estado + ", cliente=" + cliente + ", concesionario=" + concesionario +'}';
    }

}
