package com.darthlogus.os.controller;

import com.darthlogus.os.dtos.TecnicoDTO;
import com.darthlogus.os.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
