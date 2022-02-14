package com.darthlogus.os.services;

import com.darthlogus.os.domain.OS;
import com.darthlogus.os.domain.enums.Prioridade;
import com.darthlogus.os.domain.enums.Status;
import com.darthlogus.os.dtos.OSDTO;
import com.darthlogus.os.repositories.OsRepository;
import com.darthlogus.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OsRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public OS findById(Integer id){
        Optional<OS> opt = osRepository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: "+id+", Tipo: "+OS.class.getName()));
    }

    public List<OS> findAll(){
        return osRepository.findAll();
    }

    public OS create(@Valid OSDTO osDto) {
        return fromDTO(osDto);
    }

    public OS update(@Valid OSDTO osDto) {
        findById(osDto.getId());
        return fromDTO(osDto);
    }

    private OS fromDTO(OSDTO osDto){
        OS os = new OS();
        os.setId(osDto.getId());
        os.setObservacoes(osDto.getObservacoes());
        os.setPrioridade(Prioridade.toEnum(osDto.getPrioridade()));
        os.setStatus(Status.toEnum(osDto.getStatus()));
        os.setTecnico(tecnicoService.findById(osDto.getTecnico()));
        os.setCiente(clienteService.findById(osDto.getCliente()));
        if (os.getStatus().getCod().equals(2)){
            os.setDataFechamento(LocalDateTime.now());
        }
        return osRepository.save(os);
    }


}
