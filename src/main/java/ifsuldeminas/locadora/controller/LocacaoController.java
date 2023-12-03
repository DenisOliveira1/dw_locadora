package ifsuldeminas.locadora.controller;

import ifsuldeminas.locadora.controller.dto.LocacaoAddDto;
import ifsuldeminas.locadora.model.entity.Locacao;
import ifsuldeminas.locadora.model.entity.cliente.Cliente;
import ifsuldeminas.locadora.model.entity.filme.Filme;
import ifsuldeminas.locadora.model.repository.ClienteRepository;
import ifsuldeminas.locadora.model.repository.FilmeRepository;
import ifsuldeminas.locadora.model.repository.LocacaoRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/locacoes")
public class LocacaoController {

    private LocacaoRepository locacaoRepository;
    private ClienteRepository clienteRepository;
    private FilmeRepository filmeRepository;

    public LocacaoController(
            LocacaoRepository locacaoRepository,
            ClienteRepository clienteRepository,
            FilmeRepository filmeRepository
    ){
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.filmeRepository = filmeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Locacao> get(
            @PathVariable Long id){

        Optional<Locacao> optional = locacaoRepository.findById(id);
        if (optional.isPresent())
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        return new ResponseEntity("Locação não encontrada.", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Locacao> getAll(){
        return locacaoRepository.findAll();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Locacao>> getAllByClientId(@PathVariable Long clienteId){

        Optional<Cliente> optional = clienteRepository.findById(clienteId);
        if (!optional.isPresent())
            return new ResponseEntity("Cliente não encontrado.", HttpStatus.NOT_FOUND);

        return new ResponseEntity(locacaoRepository.findAllByClienteId(clienteId), HttpStatus.OK);
    }

    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<Locacao>> getAllByFilmeId(@PathVariable Long filmeId){

        Optional<Filme> optional = filmeRepository.findById(filmeId);
        if (!optional.isPresent())
            return new ResponseEntity("Filme não encontrado.", HttpStatus.NOT_FOUND);

        return new ResponseEntity(locacaoRepository.findAllByFilmeId(filmeId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Locacao> add(
            @RequestBody @Valid LocacaoAddDto locacaoAddDto){

        Long clienteId = locacaoAddDto.getClienteId();
        Optional<Cliente> optionalCliente = clienteRepository.findById(clienteId);
        if (!optionalCliente.isPresent())
            return new ResponseEntity("Cliente não encontrado.", HttpStatus.NOT_FOUND);
        Cliente cliente = optionalCliente.get();

        Long filmeId = locacaoAddDto.getFilmeId();
        Optional<Filme> optionalFilme = filmeRepository.findById(filmeId);
        if (!optionalFilme.isPresent())
            return new ResponseEntity("Filme não encontrado.", HttpStatus.NOT_FOUND);
        Filme filme = optionalFilme.get();

        Locacao locacaoParaAdicionar = new Locacao();
        locacaoParaAdicionar.setDuracaoLocacao(locacaoAddDto.getDuracaoLocacao());
        locacaoParaAdicionar.setCliente(cliente);
        locacaoParaAdicionar.setFilme(filme);
        locacaoParaAdicionar.setEstaCancelada(false);
        locacaoParaAdicionar.setDataLocacao(new Date(System.currentTimeMillis()));
        Locacao locacaoAdicionada = locacaoRepository.save(locacaoParaAdicionar);

        return new ResponseEntity(locacaoAdicionada, HttpStatus.OK);
    }

    @PutMapping("/cancelar/{id}")
    public ResponseEntity<Locacao> cancel(
            @PathVariable Long id){

        Optional<Locacao> optional = locacaoRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity("Locação não encontrada.", HttpStatus.NOT_FOUND);
        Locacao locacaoRegistrada = optional.get();

        locacaoRegistrada.setEstaCancelada(true);
        Locacao locacaoAtualizada = locacaoRepository.save(locacaoRegistrada);

        return new ResponseEntity(locacaoAtualizada, HttpStatus.OK);
    }
}