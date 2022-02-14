package com.darthlogus.os.controller;

import com.darthlogus.os.domain.Cliente;
import com.darthlogus.os.dtos.ClienteDTO;
import com.darthlogus.os.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        ClienteDTO clienteDTO = new ClienteDTO(clienteService.findById(id));
        return ResponseEntity.ok().body(clienteDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll(){
    	List<ClienteDTO> listDTO = clienteService.findAll()
    			.stream().map(tecnico -> new ClienteDTO(tecnico)).collect(Collectors.toList());
    	return ResponseEntity.ok().body(listDTO);
    }
    
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO clienteDTO){
    	Cliente cliente = clienteService.create(clienteDTO);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
    	
    	return ResponseEntity.created(uri).build();
    }
    
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id,@Valid @RequestBody ClienteDTO clienteDTO){
    	ClienteDTO novoClienteDTO = new ClienteDTO(clienteService.udpate(id, clienteDTO));
    	
    	return ResponseEntity.ok().body(novoClienteDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
