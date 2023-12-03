package ifsuldeminas.locadora.controller;

import ifsuldeminas.locadora.model.entity.cliente.Cliente;
import ifsuldeminas.locadora.model.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;

    public ClienteController(
            ClienteRepository clienteRepository
    ){
        this.clienteRepository = clienteRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> get(
            @PathVariable Long id){

        Optional<Cliente> optional = clienteRepository.findById(id);
        if (optional.isPresent())
            return new ResponseEntity(optional.get(), HttpStatus.OK);
        return new ResponseEntity("Cliente não encontrado.", HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public List<Cliente> getAll(){
        return clienteRepository.findAll();
    }

    @PostMapping
    public Cliente add(
            @RequestBody @Valid Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(
            @PathVariable Long id,
            @RequestBody @Valid Cliente cliente){

        Optional<Cliente> optional = clienteRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity("Cliente não encontrado.", HttpStatus.NOT_FOUND);

        Cliente clienteRegistrado = optional.get();
        clienteRegistrado.setCidade(cliente.getCidade());
        clienteRegistrado.setCpf(cliente.getCpf());
        clienteRegistrado.setEndereco(cliente.getEndereco());
        clienteRegistrado.setNome(cliente.getNome());
        clienteRegistrado.setTelefone(cliente.getTelefone());
        Cliente clienteAtualizado = clienteRepository.save(clienteRegistrado);

        return new ResponseEntity(clienteAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @PathVariable Long id){

        Optional<Cliente> optional = clienteRepository.findById(id);
        if (!optional.isPresent())
            return new ResponseEntity("Cliente não encontrado.", HttpStatus.NOT_FOUND);

        clienteRepository.deleteById(id);
        return new ResponseEntity("Cliente deletado.", HttpStatus.OK);
    }
}