package ifsuldeminas.locadora.model.entity.filme;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import ifsuldeminas.locadora.model.entity.Locacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.Set;

@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @NotBlank
    private String titulo;
    @NotNull
    @NotBlank
    private String diretor;
    @NotNull
    @NotBlank
    private String genero;
    @Min(value = 0)
    @Max(value = 10)
    private int estoque;
    @DecimalMin(value = "1.0")
    @DecimalMax(value = "5.0")
    private double valor;
    @DecimalMin(value = "0.5")
    @DecimalMax(value = "2.0")
    private double multaDiaria;
    @JsonIgnore
    @OneToMany(mappedBy = "filme", fetch = FetchType.LAZY)
    private Set<Locacao> locacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public double getMultaDiaria() {
        return multaDiaria;
    }

    public void setMultaDiaria(double multaDiaria) {
        this.multaDiaria = multaDiaria;
    }

    public Set<Locacao> getLocacoes() {
        return locacoes;
    }

    public void setLocacoes(Set<Locacao> locacoes) {
        this.locacoes = locacoes;
    }
}
