package ifsuldeminas.locadora.controller;

import ifsuldeminas.locadora.model.entity.cliente.Cliente;
import ifsuldeminas.locadora.model.entity.filme.Filme;
import ifsuldeminas.locadora.model.repository.FilmeRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeRepository filmeRepository;

    public FilmeController(
            FilmeRepository filmeRepository
    ){
        this.filmeRepository = filmeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Filme> get(
            @PathVariable Long id){

        Optional<Filme> optional = filmeRepository.findById(id);
        if (optional.isPresent())
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        return new ResponseEntity("Filme não encontrado.", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Filme> getAll(){
        return filmeRepository.findAll();
    }

    @PostMapping
    public Filme add(
            @RequestBody @Valid Filme filme){
        return filmeRepository.save(filme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Filme> update(
            @PathVariable Long id,
            @RequestBody @Valid Filme filme){

        Optional<Filme> optional = filmeRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity("Filme não encontrado.", HttpStatus.NOT_FOUND);

        Filme filmeRegistrado = optional.get();
        filmeRegistrado.setDiretor(filme.getDiretor());
        filmeRegistrado.setEstoque(filme.getEstoque());
        filmeRegistrado.setGenero(filme.getGenero());
        filmeRegistrado.setMultaDiaria(filme.getMultaDiaria());
        filmeRegistrado.setTitulo(filme.getTitulo());
        filmeRegistrado.setValor(filme.getValor());
        Filme filmeAtualizado = filmeRepository.save(filmeRegistrado);

        return new ResponseEntity(filmeAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id){

        Optional<Filme> optional = filmeRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity("Filme não encontrado.", HttpStatus.NOT_FOUND);

        filmeRepository.deleteById(id);
        return new ResponseEntity("Filme deletado.", HttpStatus.OK);
    }
}