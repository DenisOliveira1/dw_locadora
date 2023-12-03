package ifsuldeminas.locadora.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import ifsuldeminas.locadora.model.entity.cliente.Cliente;
import ifsuldeminas.locadora.model.entity.filme.Filme;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import java.util.Date;
@Entity
public class Locacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataLocacao;
    @Min(value = 1)
    @Max(value = 7)
    private int duracaoLocacao;
    private boolean estaCancelada;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "filme_id")
    private Filme filme;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public int getDuracaoLocacao() {
        return duracaoLocacao;
    }

    public void setDuracaoLocacao(int duracaoLocacao) {
        this.duracaoLocacao = duracaoLocacao;
    }

    public boolean isEstaCancelada() {
        return estaCancelada;
    }

    public void setEstaCancelada(boolean estaCancelada) {
        this.estaCancelada = estaCancelada;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Filme getFilme() {
        return filme;
    }

    public void setFilme(Filme filme) {
        this.filme = filme;
    }
}
