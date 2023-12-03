package ifsuldeminas.locadora.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class LocacaoAddDto {
    @Min(value = 1)
    @Max(value = 7)
    private int duracaoLocacao;
    private Long clienteId;
    private Long filmeId;

    public int getDuracaoLocacao() {
        return duracaoLocacao;
    }

    public void setDuracaoLocacao(int duracaoLocacao) {
        this.duracaoLocacao = duracaoLocacao;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getFilmeId() {
        return filmeId;
    }

    public void setFilmeId(Long filmeId) {
        this.filmeId = filmeId;
    }
}
