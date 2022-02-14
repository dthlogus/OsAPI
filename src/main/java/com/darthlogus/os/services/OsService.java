package com.darthlogus.os.services;

import com.darthlogus.os.domain.OS;
import com.darthlogus.os.repositories.OsRepository;
import com.darthlogus.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OsRepository osRepository;

    public OS findById(Integer id){
        Optional<OS> opt = osRepository.findById(id);
        return opt.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: "+id+", Tipo: "+OS.class.getName()));
    }

    public List<OS> findAll(){
        return osRepository.findAll();
    }
}
