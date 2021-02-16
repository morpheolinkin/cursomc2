package com.morpheus.cursomc2.repository;

import com.morpheus.cursomc2.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Cliente findByEmail(String email);
}
