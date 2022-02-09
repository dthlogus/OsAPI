package com.darthlogus.os.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.darthlogus.os.domain.Tecnico;
import com.darthlogus.os.dtos.TecnicoDTO;
import com.darthlogus.os.services.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        TecnicoDTO tecnicoDTO = new TecnicoDTO(tecnicoService.findById(id));
        return ResponseEntity.ok().body(tecnicoDTO);
    }
    
    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll(){
    	List<TecnicoDTO> listDTO = tecnicoService.findAll()
    			.stream().map(tecnico -> new TecnicoDTO(tecnico)).collect(Collectors.toList());
    	return ResponseEntity.ok().body(listDTO);
    }
    
    @PostMapping
    public ResponseEntity<TecnicoDTO> create(@RequestBody TecnicoDTO tecnicoDto){
    	Tecnico tecnico = tecnicoService.create(tecnicoDto);
    	
    	URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
    	
    	return ResponseEntity.created(uri).build();
    }
}
