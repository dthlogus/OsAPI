package com.darthlogus.os.services;

import com.darthlogus.os.domain.Tecnico;
import com.darthlogus.os.dtos.TecnicoDTO;
import com.darthlogus.os.repositories.TecnicoRepository;
import com.darthlogus.os.services.exceptions.DataIntegratyViolationException;
import com.darthlogus.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id+", Tipo: "+Tecnico.class.getName()));
    }

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}
	
	public Tecnico create(TecnicoDTO tecnicoDto) {
		if(findByCPF(tecnicoDto) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return tecnicoRepository.save(new Tecnico(null, tecnicoDto.getNome(), tecnicoDto.getCpf(), tecnicoDto.getTelefone()));
	}
	
	public Tecnico udpate(Integer id, @Valid TecnicoDTO tecnicoDTO) {
		Tecnico antigoTecnico = findById(id);
		
		if(findByCPF(tecnicoDTO) != null && findByCPF(tecnicoDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		antigoTecnico.setNome(tecnicoDTO.getNome());
		antigoTecnico.setCpf(tecnicoDTO.getCpf());
		antigoTecnico.setTelefone(tecnicoDTO.getTelefone());
		
		return tecnicoRepository.save(antigoTecnico);
	}
	
    private Tecnico findByCPF(TecnicoDTO tecnicoDto) {
    	Tecnico tecnico = tecnicoRepository.findByCPF(tecnicoDto.getCpf());
    	if(tecnico != null) {
    		return tecnico;
    	}
    	return null;
    }

}
