package com.darthlogus.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.darthlogus.os.domain.Cliente;
import com.darthlogus.os.domain.OS;
import com.darthlogus.os.domain.Tecnico;
import com.darthlogus.os.domain.enums.Prioridade;
import com.darthlogus.os.domain.enums.Status;
import com.darthlogus.os.repositories.ClienteRepository;
import com.darthlogus.os.repositories.OsRepository;
import com.darthlogus.os.repositories.TecnicoRepository;

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
        Tecnico t2 = new Tecnico(null, "Evandro Silva", "701.122.490-43", "(62)43929-4991");
        Cliente c1 = new Cliente(null, "Rogeiro Salvador", "237.489.400-25", "(62) 99357-5897");
        OS os1 = new OS(null, Prioridade.ALTA, "TESTE CREATE OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1));
        tecnicoRepository.save(t2);
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
