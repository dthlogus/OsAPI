package com.darthlogus.os.controller;

import com.darthlogus.os.dtos.OSDTO;
import com.darthlogus.os.services.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(value = "/os")
public class OsController {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OSDTO> findById(@PathVariable Integer id){
        OSDTO osDto = new OSDTO(osService.findById(id));
        return ResponseEntity.ok().body(osDto);
    }

    @GetMapping
    public ResponseEntity<List<OSDTO>> findAll(){
        List<OSDTO> listDto = osService.findAll().stream().map(os -> new OSDTO(os)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<OSDTO> create(@Valid @RequestBody OSDTO osDto){
        osDto = new OSDTO(osService.create(osDto));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(osDto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping()
    public ResponseEntity<OSDTO> update(@Valid @RequestBody OSDTO osDto) {
        osDto = new OSDTO(osService.update(osDto));
        return ResponseEntity.ok().body(osDto);
    }



}
