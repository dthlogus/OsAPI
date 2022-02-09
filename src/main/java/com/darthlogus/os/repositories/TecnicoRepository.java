package com.darthlogus.os.repositories;

import com.darthlogus.os.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

	@Query("SELECT t FROM Tecnico t WHERE t.cpf = :cpf")
	Tecnico findByCPF(@Param("cpf") String cpf);
}
