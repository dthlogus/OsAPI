package com.darthlogus.os.repositories;

import com.darthlogus.os.domain.OS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OsRepository extends JpaRepository<OS, Integer> {
}
