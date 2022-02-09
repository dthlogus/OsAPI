package com.darthlogus.os.services;

import com.darthlogus.os.domain.Cliente;
import com.darthlogus.os.domain.OS;
import com.darthlogus.os.domain.Tecnico;
import com.darthlogus.os.domain.enums.Prioridade;
import com.darthlogus.os.domain.enums.Status;
import com.darthlogus.os.repositories.ClienteRepository;
import com.darthlogus.os.repositories.OsRepository;
import com.darthlogus.os.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private OsRepository osRepository;

    public void instanciaDB() {
        Tecnico t1 = new Tecnico(null, "Matheus Ribeiro", "446.495.520-79", "(62)99333-6247");
        Cliente c1 = new Cliente(null, "Rogeiro Salvador", "237.489.400-25", "(62) 99357-5897");
        OS os1 = new OS(null, Prioridade.ALTA, "TESTE CREATE OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
