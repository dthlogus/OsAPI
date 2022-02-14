package com.darthlogus.os.services;

import com.darthlogus.os.domain.Cliente;
import com.darthlogus.os.domain.Pessoa;
import com.darthlogus.os.dtos.ClienteDTO;
import com.darthlogus.os.repositories.ClienteRepository;
import com.darthlogus.os.repositories.PessoaRepository;
import com.darthlogus.os.services.exceptions.DataIntegratyViolationException;
import com.darthlogus.os.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
	private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: "+id+", Tipo: "+Cliente.class.getName()));
    }

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
	
	public Cliente create(ClienteDTO clienteDto) {
		if(findByCPF(clienteDto) != null) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		return clienteRepository.save(new Cliente(null, clienteDto.getNome(), clienteDto.getCpf(), clienteDto.getTelefone()));
	}
	
	public Cliente udpate(Integer id, @Valid ClienteDTO clienteDTO) {
		Cliente antigoCliente = findById(id);
		
		if(findByCPF(clienteDTO) != null && findByCPF(clienteDTO).getId() != id) {
			throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
		}
		
		antigoCliente.setNome(clienteDTO.getNome());
		antigoCliente.setCpf(clienteDTO.getCpf());
		antigoCliente.setTelefone(clienteDTO.getTelefone());
		
		return clienteRepository.save(antigoCliente);
	}

	public void delete(Integer id) {
    	Cliente cliente = findById(id);
    	if (cliente.getList().size() > 0){
			throw new DataIntegratyViolationException("O Técnico não pode ser excluido, pois existem ordens de serviço atribuidas a ele.");
		}
		clienteRepository.deleteById(id);
	}
	
    private Pessoa findByCPF(ClienteDTO clienteDto) {
    	Pessoa cliente = pessoaRepository.findByCPF(clienteDto.getCpf());
    	if(cliente != null) {
    		return cliente;
    	}
    	return null;
    }
}
